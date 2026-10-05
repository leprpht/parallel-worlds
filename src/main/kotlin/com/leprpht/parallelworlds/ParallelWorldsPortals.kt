package com.leprpht.parallelworlds

import com.leprpht.parallelworlds.portal.ParallelWorldDefinition
import com.leprpht.parallelworlds.portal.ParallelWorldDefinitions
import com.leprpht.parallelworlds.portal.PortalDesign
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.NetherPortalBlock
import org.slf4j.LoggerFactory

object ParallelWorldsPortals {

    private val logger = LoggerFactory.getLogger("parallelworlds")

    private val OVERWORLD_DIMENSION: ResourceKey<Level> = Level.OVERWORLD

    fun register() {
        UseBlockCallback.EVENT.register { player, world, hand, hitResult ->
            if (world.isClientSide) {
                return@register InteractionResult.PASS
            }

            if (hand != InteractionHand.MAIN_HAND) {
                return@register InteractionResult.PASS
            }

            if (player.mainHandItem.item != Items.FLINT_AND_STEEL) {
                return@register InteractionResult.PASS
            }

            val serverWorld = world as? ServerLevel ?: return@register InteractionResult.PASS

            val frame =
                findFrameNear(
                    serverWorld,
                    hitResult.blockPos,
                ) ?: return@register InteractionResult.PASS

            val definition =
                destinationDefinition(
                    serverWorld,
                    frame.design,
                ) ?: return@register InteractionResult.PASS

            if (!activatePortal(serverWorld, frame)) {
                return@register InteractionResult.PASS
            }

            logger.info(
                "Activated {} portal at {} in {} -> {}",
                definition.id,
                frame.bottomLeft,
                serverWorld.dimension().identifier(),
                definition.dimension,
            )

            damageFlintAndSteel(player)

            InteractionResult.SUCCESS
        }
    }

    fun findDestination(
        world: ServerLevel,
        portalPos: BlockPos,
    ): PortalDestination? {
        logger.info(
            "Custom portal lookup: dimension={}, position={}",
            world.dimension().identifier(),
            portalPos,
        )

        val portalState = world.getBlockState(portalPos)

        if (!portalState.`is`(Blocks.NETHER_PORTAL)) {
            logger.warn(
                "Custom portal lookup failed: block at {} is {}",
                portalPos,
                portalState.block,
            )
            return null
        }

        val portalAxis = portalState.getValue(NetherPortalBlock.AXIS)

        logger.info(
            "Portal block found at {} with AXIS={}",
            portalPos,
            portalAxis,
        )

        val sourceFrame =
            findFrameNearPortal(
                world,
                portalPos,
                portalAxis,
            )

        if (sourceFrame == null) {
            logger.warn(
                "Custom portal lookup failed: could not find frame around portal at {}",
                portalPos,
            )
            return null
        }

        logger.info(
            "Portal frame found: bottomLeft={}, direction={}, axis={}, design={}",
            sourceFrame.bottomLeft,
            sourceFrame.widthDirection,
            sourceFrame.widthAxis,
            ParallelWorldDefinitions.byDesign(sourceFrame.design)?.id ?: "UNKNOWN",
        )

        val sourceDesign = sourceFrame.design

        val definition = ParallelWorldDefinitions.byDesign(sourceDesign)

        if (definition == null) {
            logger.warn("Custom portal lookup failed: frame design is not registered")
            return null
        }

        logger.info(
            "Portal definition found: id={}, dimension={}",
            definition.id,
            definition.dimension,
        )

        val destinationWorld =
            getDestinationWorld(
                world,
                sourceDesign,
            )

        if (destinationWorld == null) {
            logger.warn(
                "Custom portal lookup failed: destination dimension {} is not loaded",
                definition.dimension,
            )
            return null
        }

        logger.info(
            "Destination world found: {}",
            destinationWorld.dimension().identifier(),
        )

        val existingPortal =
            findExistingPortal(
                destinationWorld,
                sourceFrame.bottomLeft.x,
                sourceFrame.bottomLeft.z,
                sourceFrame.widthAxis,
                sourceDesign,
            )

        val destinationFrame =
            existingPortal
                ?: createDestinationPortal(
                    destinationWorld,
                    sourceFrame,
                    sourceDesign,
                )

        if (destinationFrame == null) {
            logger.warn("Custom portal lookup failed: could not find/create destination frame")
            return null
        }

        logger.info(
            "Destination portal frame: bottomLeft={}, direction={}",
            destinationFrame.bottomLeft,
            destinationFrame.widthDirection,
        )

        return PortalDestination(
            world = destinationWorld,
            position = portalCenter(destinationFrame),
        )
    }

    private fun destinationDefinition(
        world: ServerLevel,
        design: PortalDesign,
    ): ParallelWorldDefinition? {
        if (world.dimension() == OVERWORLD_DIMENSION) {
            return ParallelWorldDefinitions.byDesign(design)
        }

        val currentDefinition =
            ParallelWorldDefinitions.byDimension(world.dimension().identifier()) ?: return null

        return currentDefinition.takeIf { it.portalDesign == design }
    }

    private fun getDestinationWorld(
        world: ServerLevel,
        design: PortalDesign,
    ): ServerLevel? {
        if (world.dimension() == OVERWORLD_DIMENSION) {
            val definition = ParallelWorldDefinitions.byDesign(design) ?: return null

            val dimensionKey =
                ResourceKey.create(
                    Registries.DIMENSION,
                    definition.dimension,
                )

            logger.info(
                "Looking for destination dimension {}",
                dimensionKey.identifier(),
            )

            return world.server.getLevel(dimensionKey)
        }

        val currentDefinition = ParallelWorldDefinitions.byDimension(world.dimension().identifier())

        if (currentDefinition != null) {
            return world.server.overworld()
        }

        return null
    }

    private fun activatePortal(
        world: ServerLevel,
        frame: PortalFrame,
    ): Boolean {
        val portalState =
            Blocks.NETHER_PORTAL.defaultBlockState()
                .setValue(
                    NetherPortalBlock.AXIS,
                    frame.widthAxis,
                )

        for (width in 1..2) {
            for (height in 1..3) {
                val pos = frame.bottomLeft.relative(frame.widthDirection, width).above(height)

                world.setBlock(
                    pos,
                    portalState,
                    3,
                )
            }
        }

        return true
    }

    private fun damageFlintAndSteel(player: Player) {
        player.mainHandItem.hurtAndBreak(
            1,
            player,
            InteractionHand.MAIN_HAND,
        )
    }

    private fun findFrameNear(
        world: ServerLevel,
        clickedPos: BlockPos,
    ): PortalFrame? {
        val designs = ParallelWorldDefinitions.all().map { it.portalDesign }.distinct()

        val directions =
            listOf(
                Direction.EAST,
                Direction.WEST,
                Direction.SOUTH,
                Direction.NORTH,
            )

        for (xOffset in -4..4) {
            for (yOffset in -4..4) {
                for (zOffset in -4..4) {
                    val candidate =
                        clickedPos.offset(
                            xOffset,
                            yOffset,
                            zOffset,
                        )

                    for (direction in directions) {
                        val frame =
                            findFrameAt(
                                world,
                                candidate,
                                direction,
                                designs,
                            )

                        if (frame != null) {
                            return frame
                        }
                    }
                }
            }
        }

        return null
    }

    private fun findFrameAt(
        world: ServerLevel,
        bottomLeft: BlockPos,
        widthDirection: Direction,
        designs: List<PortalDesign>,
    ): PortalFrame? {
        if (widthDirection.axis == Direction.Axis.Y) {
            return null
        }

        for (design in designs) {
            if (
                !matchesDesign(
                    world,
                    bottomLeft,
                    widthDirection,
                    design,
                )
            ) {
                continue
            }

            if (
                !hasValidPortalInterior(
                    world,
                    bottomLeft,
                    widthDirection,
                )
            ) {
                continue
            }

            return PortalFrame(
                bottomLeft = bottomLeft,
                widthDirection = widthDirection,
                widthAxis = widthDirection.axis,
                design = design,
            )
        }

        return null
    }

    private fun matchesDesign(
        world: ServerLevel,
        bottomLeft: BlockPos,
        widthDirection: Direction,
        design: PortalDesign,
    ): Boolean {
        val positions =
            listOf(
                BlockPosition(bottomLeft, 0, 0, design.bottomLeft),
                BlockPosition(bottomLeft, 1, 0, design.bottomInnerLeft),
                BlockPosition(bottomLeft, 2, 0, design.bottomInnerRight),
                BlockPosition(bottomLeft, 3, 0, design.bottomRight),
                BlockPosition(bottomLeft, 0, 1, design.lowerLeft),
                BlockPosition(bottomLeft, 3, 1, design.lowerRight),
                BlockPosition(bottomLeft, 0, 2, design.middleLeft),
                BlockPosition(bottomLeft, 3, 2, design.middleRight),
                BlockPosition(bottomLeft, 0, 3, design.upperLeft),
                BlockPosition(bottomLeft, 3, 3, design.upperRight),
                BlockPosition(bottomLeft, 0, 4, design.topLeft),
                BlockPosition(bottomLeft, 1, 4, design.topInnerLeft),
                BlockPosition(bottomLeft, 2, 4, design.topInnerRight),
                BlockPosition(bottomLeft, 3, 4, design.topRight),
            )

        return positions.all { expected ->
            val pos =
                expected.origin
                    .relative(
                        widthDirection,
                        expected.widthOffset,
                    )
                    .above(expected.heightOffset)

            world.getBlockState(pos).block == expected.block
        }
    }

    private fun hasValidPortalInterior(
        world: ServerLevel,
        bottomLeft: BlockPos,
        widthDirection: Direction,
    ): Boolean {
        for (width in 1..2) {
            for (height in 1..3) {
                val pos = bottomLeft.relative(widthDirection, width).above(height)

                val state = world.getBlockState(pos)

                if (!state.isAir && !state.`is`(Blocks.NETHER_PORTAL)) {
                    return false
                }
            }
        }

        return true
    }

    private fun findFrameNearPortal(
        world: ServerLevel,
        portalPos: BlockPos,
        portalAxis: Direction.Axis,
    ): PortalFrame? {
        val directions =
            when (portalAxis) {
                Direction.Axis.X ->
                    listOf(
                        Direction.EAST,
                        Direction.WEST,
                    )

                Direction.Axis.Z ->
                    listOf(
                        Direction.SOUTH,
                        Direction.NORTH,
                    )

                Direction.Axis.Y -> return null
            }

        val designs = ParallelWorldDefinitions.all().map { it.portalDesign }.distinct()

        /*
         * Do not assume a fixed relationship between the portal block
         * and the frame's bottom-left block. Search the nearby area
         * for the complete frame instead.
         *
         * The search is deliberately small because a Nether portal
         * interior is only 2x3 blocks.
         */
        for (xOffset in -4..4) {
            for (yOffset in -5..1) {
                for (zOffset in -4..4) {
                    val candidate =
                        portalPos.offset(
                            xOffset,
                            yOffset,
                            zOffset,
                        )

                    for (direction in directions) {
                        val frame =
                            findFrameAt(
                                world,
                                candidate,
                                direction,
                                designs,
                            )

                        if (frame != null) {
                            /*
                             * The portal must actually be inside this
                             * frame. This prevents a nearby unrelated
                             * frame from being selected.
                             */
                            if (
                                isPortalInsideFrame(
                                    world,
                                    portalPos,
                                    frame,
                                )
                            ) {
                                return frame
                            }
                        }
                    }
                }
            }
        }

        return null
    }

    private fun isPortalInsideFrame(
        world: ServerLevel,
        portalPos: BlockPos,
        frame: PortalFrame,
    ): Boolean {
        val portalAxis = world.getBlockState(portalPos).getValue(NetherPortalBlock.AXIS)

        if (portalAxis != frame.widthAxis) {
            return false
        }

        for (width in 1..2) {
            for (height in 1..3) {
                val expectedPos =
                    frame.bottomLeft.relative(frame.widthDirection, width).above(height)

                if (world.getBlockState(expectedPos).`is`(Blocks.NETHER_PORTAL)) {
                    if (expectedPos == portalPos) {
                        return true
                    }
                }
            }
        }

        return false
    }

    private fun findExistingPortal(
        world: ServerLevel,
        sourceX: Int,
        sourceZ: Int,
        widthAxis: Direction.Axis,
        design: PortalDesign,
    ): PortalFrame? {
        val minX = sourceX - 16
        val maxX = sourceX + 16
        val minZ = sourceZ - 16
        val maxZ = sourceZ + 16

        var bestFrame: PortalFrame? = null
        var bestDistance = Int.MAX_VALUE

        for (x in minX..maxX) {
            for (z in minZ..maxZ) {
                if (kotlin.math.abs(x - sourceX) + kotlin.math.abs(z - sourceZ) > 16) {
                    continue
                }

                for (y in world.minY..world.maxY - 5) {
                    val pos = BlockPos(x, y, z)
                    val state = world.getBlockState(pos)

                    if (!state.`is`(Blocks.NETHER_PORTAL)) {
                        continue
                    }

                    val portalAxis = state.getValue(NetherPortalBlock.AXIS)

                    if (portalAxis != widthAxis) {
                        continue
                    }

                    val frame =
                        findFrameNearPortal(
                            world,
                            pos,
                            portalAxis,
                        ) ?: continue

                    if (frame.design != design) {
                        continue
                    }

                    val distance =
                        kotlin.math.abs(frame.bottomLeft.x - sourceX) +
                            kotlin.math.abs(frame.bottomLeft.z - sourceZ)

                    if (distance < bestDistance) {
                        bestDistance = distance
                        bestFrame = frame
                    }
                }
            }
        }

        return bestFrame
    }

    private fun createDestinationPortal(
        world: ServerLevel,
        sourceFrame: PortalFrame,
        design: PortalDesign,
    ): PortalFrame? {
        val widthDirection = sourceFrame.widthDirection

        for (radius in 0..16) {
            for (xOffset in -radius..radius) {
                val zDistance = radius - kotlin.math.abs(xOffset)

                val zOffsets =
                    if (zDistance == 0) {
                        listOf(0)
                    } else {
                        listOf(
                            zDistance,
                            -zDistance,
                        )
                    }

                for (zOffset in zOffsets) {
                    val groundX = sourceFrame.bottomLeft.x + xOffset

                    val groundZ = sourceFrame.bottomLeft.z + zOffset

                    val groundY =
                        findGroundY(
                            world,
                            groundX,
                            groundZ,
                        ) ?: continue

                    val bottomLeft =
                        BlockPos(
                            groundX,
                            groundY + 1,
                            groundZ,
                        )

                    if (
                        !isSuitablePortalLocation(
                            world,
                            bottomLeft,
                            widthDirection,
                        )
                    ) {
                        continue
                    }

                    val frame =
                        PortalFrame(
                            bottomLeft = bottomLeft,
                            widthDirection = widthDirection,
                            widthAxis = widthDirection.axis,
                            design = design,
                        )

                    createFrame(
                        world,
                        frame,
                        design,
                    )

                    return frame
                }
            }
        }

        return null
    }

    private fun findGroundY(
        world: ServerLevel,
        x: Int,
        z: Int,
    ): Int? {
        for (y in world.maxY - 1 downTo world.minY) {
            val pos = BlockPos(x, y, z)

            if (world.getBlockState(pos).isSolid) {
                return y
            }
        }

        return null
    }

    private fun isSuitablePortalLocation(
        world: ServerLevel,
        bottomLeft: BlockPos,
        widthDirection: Direction,
    ): Boolean {
        for (width in 0..3) {
            val ground = bottomLeft.relative(widthDirection, width).below()

            if (!world.getBlockState(ground).isSolid) {
                return false
            }
        }

        for (width in 0..3) {
            for (height in 0..4) {
                val pos = bottomLeft.relative(widthDirection, width).above(height)

                if (
                    !world.getBlockState(pos).isAir &&
                        !world.getBlockState(pos).`is`(Blocks.NETHER_PORTAL)
                ) {
                    return false
                }
            }
        }

        return true
    }

    private fun createFrame(
        world: ServerLevel,
        frame: PortalFrame,
        design: PortalDesign,
    ) {
        val widthDirection = frame.widthDirection

        setBlock(world, frame.bottomLeft, design.bottomLeft)

        setBlock(
            world,
            frame.bottomLeft.relative(widthDirection),
            design.bottomInnerLeft,
        )

        setBlock(
            world,
            frame.bottomLeft.relative(widthDirection, 2),
            design.bottomInnerRight,
        )

        setBlock(
            world,
            frame.bottomLeft.relative(widthDirection, 3),
            design.bottomRight,
        )

        setBlock(
            world,
            frame.bottomLeft.above(),
            design.lowerLeft,
        )

        setBlock(
            world,
            frame.bottomLeft.relative(widthDirection, 3).above(),
            design.lowerRight,
        )

        setBlock(
            world,
            frame.bottomLeft.above(2),
            design.middleLeft,
        )

        setBlock(
            world,
            frame.bottomLeft.relative(widthDirection, 3).above(2),
            design.middleRight,
        )

        setBlock(
            world,
            frame.bottomLeft.above(3),
            design.upperLeft,
        )

        setBlock(
            world,
            frame.bottomLeft.relative(widthDirection, 3).above(3),
            design.upperRight,
        )

        setBlock(
            world,
            frame.bottomLeft.above(4),
            design.topLeft,
        )

        setBlock(
            world,
            frame.bottomLeft.relative(widthDirection).above(4),
            design.topInnerLeft,
        )

        setBlock(
            world,
            frame.bottomLeft.relative(widthDirection, 2).above(4),
            design.topInnerRight,
        )

        setBlock(
            world,
            frame.bottomLeft.relative(widthDirection, 3).above(4),
            design.topRight,
        )

        fillPortal(world, frame)
    }

    private fun fillPortal(
        world: ServerLevel,
        frame: PortalFrame,
    ) {
        val portalState =
            Blocks.NETHER_PORTAL.defaultBlockState()
                .setValue(
                    NetherPortalBlock.AXIS,
                    frame.widthAxis,
                )

        for (width in 1..2) {
            for (height in 1..3) {
                val pos = frame.bottomLeft.relative(frame.widthDirection, width).above(height)

                world.setBlock(
                    pos,
                    portalState,
                    3,
                )
            }
        }
    }

    private fun portalCenter(frame: PortalFrame): BlockPos {
        return frame.bottomLeft.relative(frame.widthDirection).above(2)
    }

    private fun setBlock(
        world: ServerLevel,
        pos: BlockPos,
        block: Block,
    ) {
        world.setBlock(
            pos,
            block.defaultBlockState(),
            3,
        )
    }

    data class PortalDestination(
        val world: ServerLevel,
        val position: BlockPos,
    )

    private data class PortalFrame(
        val bottomLeft: BlockPos,
        val widthDirection: Direction,
        val widthAxis: Direction.Axis,
        val design: PortalDesign,
    )

    private data class BlockPosition(
        val origin: BlockPos,
        val widthOffset: Int,
        val heightOffset: Int,
        val block: Block,
    )
}
