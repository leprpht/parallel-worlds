package com.leprpht.parallelworlds

import com.leprpht.parallelworlds.portal.ParallelWorldDefinitions
import com.leprpht.parallelworlds.portal.PortalDesign
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.NetherPortalBlock

object ParallelWorldsPortals {

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

            val clickedPos = hitResult.blockPos

            val frame =
                findFrameNear(
                    serverWorld,
                    clickedPos,
                ) ?: return@register InteractionResult.PASS

            if (serverWorld.dimension() != OVERWORLD_DIMENSION) {
                return@register InteractionResult.PASS
            }

            if (ParallelWorldDefinitions.byDesign(frame.design) == null) {
                return@register InteractionResult.PASS
            }

            if (!activatePortal(serverWorld, frame)) {
                return@register InteractionResult.PASS
            }

            player.mainHandItem.hurtAndBreak(
                1,
                player,
                hand,
            )

            InteractionResult.SUCCESS
        }
    }

    fun findDestination(
        world: ServerLevel,
        portalPos: BlockPos,
    ): PortalDestination? {
        val portalState = world.getBlockState(portalPos)

        if (!portalState.`is`(Blocks.NETHER_PORTAL)) {
            return null
        }

        val portalAxis = portalState.getValue(NetherPortalBlock.AXIS)

        val sourceFrame =
            findFrameNearPortal(
                world,
                portalPos,
                portalAxis,
            ) ?: return null

        val sourceDesign = sourceFrame.design

        val destinationWorld =
            getDestinationWorld(
                world,
                sourceDesign,
            ) ?: return null

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
                ?: return null

        return PortalDestination(
            world = destinationWorld,
            position = portalCenter(destinationFrame),
        )
    }

    private fun getDestinationWorld(
        world: ServerLevel,
        design: PortalDesign,
    ): ServerLevel? {
        if (world.dimension() == OVERWORLD_DIMENSION) {
            val definition = ParallelWorldDefinitions.byDesign(design) ?: return null

            val dimensionKey =
                ResourceKey.create(
                    net.minecraft.core.registries.Registries.DIMENSION,
                    definition.dimension,
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
        val widthDirection = widthDirection(frame.widthAxis) ?: return false

        val portalState =
            Blocks.NETHER_PORTAL.defaultBlockState()
                .setValue(
                    NetherPortalBlock.AXIS,
                    frame.portalAxis,
                )

        for (width in 1..2) {
            for (height in 1..3) {
                val pos = frame.bottomLeft.relative(widthDirection, width).above(height)

                world.setBlock(
                    pos,
                    portalState,
                    3,
                )
            }
        }

        return true
    }

    private fun findFrameNear(
        world: ServerLevel,
        clickedPos: BlockPos,
    ): PortalFrame? {
        val designs = ParallelWorldDefinitions.all().map { it.portalDesign }.distinct()

        for (xOffset in -4..4) {
            for (yOffset in -4..4) {
                for (zOffset in -4..4) {
                    val candidate =
                        clickedPos.offset(
                            xOffset,
                            yOffset,
                            zOffset,
                        )

                    val xFrame =
                        findFrameAt(
                            world,
                            candidate,
                            Direction.Axis.X,
                            designs,
                        )

                    if (xFrame != null) {
                        return xFrame
                    }

                    val zFrame =
                        findFrameAt(
                            world,
                            candidate,
                            Direction.Axis.Z,
                            designs,
                        )

                    if (zFrame != null) {
                        return zFrame
                    }
                }
            }
        }

        return null
    }

    private fun findFrameAt(
        world: ServerLevel,
        bottomLeft: BlockPos,
        widthAxis: Direction.Axis,
        designs: List<PortalDesign>,
    ): PortalFrame? {
        val widthDirection = widthDirection(widthAxis) ?: return null

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

            val portalAxis =
                when (widthAxis) {
                    Direction.Axis.X -> Direction.Axis.Z
                    Direction.Axis.Z -> Direction.Axis.X
                    else -> continue
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
                widthAxis = widthAxis,
                portalAxis = portalAxis,
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
                BlockPosition(
                    bottomLeft,
                    0,
                    0,
                    design.bottomLeft,
                ),
                BlockPosition(
                    bottomLeft,
                    1,
                    0,
                    design.bottomInnerLeft,
                ),
                BlockPosition(
                    bottomLeft,
                    2,
                    0,
                    design.bottomInnerRight,
                ),
                BlockPosition(
                    bottomLeft,
                    3,
                    0,
                    design.bottomRight,
                ),
                BlockPosition(
                    bottomLeft,
                    0,
                    1,
                    design.lowerLeft,
                ),
                BlockPosition(
                    bottomLeft,
                    3,
                    1,
                    design.lowerRight,
                ),
                BlockPosition(
                    bottomLeft,
                    0,
                    2,
                    design.middleLeft,
                ),
                BlockPosition(
                    bottomLeft,
                    3,
                    2,
                    design.middleRight,
                ),
                BlockPosition(
                    bottomLeft,
                    0,
                    3,
                    design.upperLeft,
                ),
                BlockPosition(
                    bottomLeft,
                    3,
                    3,
                    design.upperRight,
                ),
                BlockPosition(
                    bottomLeft,
                    0,
                    4,
                    design.topLeft,
                ),
                BlockPosition(
                    bottomLeft,
                    1,
                    4,
                    design.topInnerLeft,
                ),
                BlockPosition(
                    bottomLeft,
                    2,
                    4,
                    design.topInnerRight,
                ),
                BlockPosition(
                    bottomLeft,
                    3,
                    4,
                    design.topRight,
                ),
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
        var bottomPortal = portalPos

        while (world.getBlockState(bottomPortal.below()).`is`(Blocks.NETHER_PORTAL)) {
            bottomPortal = bottomPortal.below()
        }

        val widthAxis =
            when (portalAxis) {
                Direction.Axis.X -> Direction.Axis.Z
                Direction.Axis.Z -> Direction.Axis.X
                else -> return null
            }

        val widthDirection = widthDirection(widthAxis) ?: return null

        val possibleBottomLefts =
            listOf(
                bottomPortal.relative(widthDirection.opposite),
                bottomPortal,
            )

        val designs = ParallelWorldDefinitions.all().map { it.portalDesign }.distinct()

        for (candidate in possibleBottomLefts) {
            val frame =
                findFrameAt(
                    world,
                    candidate,
                    widthAxis,
                    designs,
                )

            if (frame != null) {
                return frame
            }
        }

        return null
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

                    if (portalAxis == Direction.Axis.Y) {
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

                    val axisPenalty =
                        if (frame.widthAxis == widthAxis) {
                            0
                        } else {
                            1000
                        }

                    val distance =
                        kotlin.math.abs(frame.bottomLeft.x - sourceX) +
                            kotlin.math.abs(frame.bottomLeft.z - sourceZ) +
                            axisPenalty

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
        val widthDirection = widthDirection(sourceFrame.widthAxis) ?: return null

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
                            widthAxis = sourceFrame.widthAxis,
                            portalAxis = sourceFrame.portalAxis,
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

                val state = world.getBlockState(pos)

                if (!state.isAir && !state.`is`(Blocks.NETHER_PORTAL)) {
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
        val widthDirection = widthDirection(frame.widthAxis) ?: return

        setBlock(
            world,
            frame.bottomLeft,
            design.bottomLeft,
        )

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

        fillPortal(
            world,
            frame,
        )
    }

    private fun fillPortal(
        world: ServerLevel,
        frame: PortalFrame,
    ) {
        val portalState =
            Blocks.NETHER_PORTAL.defaultBlockState()
                .setValue(
                    NetherPortalBlock.AXIS,
                    frame.portalAxis,
                )

        val widthDirection = widthDirection(frame.widthAxis) ?: return

        for (width in 1..2) {
            for (height in 1..3) {
                val pos = frame.bottomLeft.relative(widthDirection, width).above(height)

                world.setBlock(
                    pos,
                    portalState,
                    3,
                )
            }
        }
    }

    private fun portalCenter(frame: PortalFrame): BlockPos {
        val widthDirection = widthDirection(frame.widthAxis) ?: Direction.EAST

        return frame.bottomLeft.relative(widthDirection).above(2)
    }

    private fun widthDirection(axis: Direction.Axis): Direction? {
        return when (axis) {
            Direction.Axis.X -> Direction.EAST
            Direction.Axis.Z -> Direction.SOUTH
            else -> null
        }
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
        val widthAxis: Direction.Axis,
        val portalAxis: Direction.Axis,
        val design: PortalDesign,
    )

    private data class BlockPosition(
        val origin: BlockPos,
        val widthOffset: Int,
        val heightOffset: Int,
        val block: Block,
    )
}
