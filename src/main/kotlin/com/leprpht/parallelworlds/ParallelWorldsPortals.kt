package com.leprpht.parallelworlds

import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.NetherPortalBlock

object ParallelWorldsPortals {

    private const val PORTAL_SEARCH_RADIUS = 16
    private const val PORTAL_SEARCH_VERTICAL_RADIUS = 128
    private const val GROUND_SEARCH_RADIUS = 16

    private val LARGE_BIOMES_DIMENSION: ResourceKey<Level> =
        ResourceKey.create(
            Registries.DIMENSION,
            Identifier.fromNamespaceAndPath(
                "parallelworlds",
                "large_biomes",
            ),
        )

    fun register() {
        UseBlockCallback.EVENT.register { player, world, hand, hitResult ->
            if (world.isClientSide) {
                return@register InteractionResult.PASS
            }

            if (hand != InteractionHand.MAIN_HAND) {
                return@register InteractionResult.PASS
            }

            if (!player.getItemInHand(hand).`is`(Items.FLINT_AND_STEEL)) {
                return@register InteractionResult.PASS
            }

            val level = world as? ServerLevel ?: return@register InteractionResult.PASS

            if (!level.getBlockState(hitResult.blockPos).`is`(Blocks.CRYING_OBSIDIAN)) {
                return@register InteractionResult.PASS
            }

            val frame =
                findFrameNear(
                    level,
                    hitResult.blockPos,
                ) ?: return@register InteractionResult.PASS

            fillPortal(level, frame)

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

        val destinationWorld =
            when (world.dimension()) {
                Level.OVERWORLD -> world.server.getLevel(LARGE_BIOMES_DIMENSION)

                LARGE_BIOMES_DIMENSION -> world.server.overworld()

                else -> null
            } ?: return null

        val existingPortal =
            findExistingPortal(
                destinationWorld,
                sourceFrame.bottomLeft.x,
                sourceFrame.bottomLeft.z,
                sourceFrame.widthAxis,
            )

        val destinationFrame =
            existingPortal
                ?: createDestinationPortal(
                    destinationWorld,
                    sourceFrame,
                )
                ?: return null

        return PortalDestination(
            world = destinationWorld,
            position = portalCenter(destinationFrame),
        )
    }

    private fun findExistingPortal(
        world: ServerLevel,
        sourceX: Int,
        sourceZ: Int,
        preferredAxis: Direction.Axis,
    ): PortalFrame? {
        var closest: PortalFrame? = null
        var closestDistance = Int.MAX_VALUE

        for (x in sourceX - PORTAL_SEARCH_RADIUS..sourceX + PORTAL_SEARCH_RADIUS) {
            for (z in sourceZ - PORTAL_SEARCH_RADIUS..sourceZ + PORTAL_SEARCH_RADIUS) {
                val horizontalDistance = kotlin.math.abs(x - sourceX) + kotlin.math.abs(z - sourceZ)

                if (horizontalDistance > PORTAL_SEARCH_RADIUS) {
                    continue
                }

                val minY =
                    maxOf(
                        world.getMinY(),
                        -PORTAL_SEARCH_VERTICAL_RADIUS,
                    )

                val maxY =
                    minOf(
                        world.getMaxY() - 5,
                        PORTAL_SEARCH_VERTICAL_RADIUS,
                    )

                for (y in minY..maxY) {
                    val pos = BlockPos(x, y, z)

                    val state = world.getBlockState(pos)

                    if (!state.`is`(Blocks.NETHER_PORTAL)) {
                        continue
                    }

                    val axis = state.getValue(NetherPortalBlock.AXIS)

                    val frame =
                        findFrameNearPortal(
                            world,
                            pos,
                            axis,
                        ) ?: continue

                    val orientationPenalty = if (axis == preferredAxis) 0 else 1_000

                    val distance = horizontalDistance * 10 + orientationPenalty

                    if (distance < closestDistance) {
                        closest = frame
                        closestDistance = distance
                    }
                }
            }
        }

        return closest
    }

    private fun createDestinationPortal(
        world: ServerLevel,
        sourceFrame: PortalFrame,
    ): PortalFrame? {
        val sourceX = sourceFrame.bottomLeft.x
        val sourceZ = sourceFrame.bottomLeft.z
        for (radius in 0..GROUND_SEARCH_RADIUS) {
            for (xOffset in -radius..radius) {
                for (zOffset in -radius..radius) {
                    if (
                        radius != 0 &&
                            kotlin.math.max(
                                kotlin.math.abs(xOffset),
                                kotlin.math.abs(zOffset),
                            ) != radius
                    ) {
                        continue
                    }

                    val x = sourceX + xOffset
                    val z = sourceZ + zOffset

                    val bottomLeft =
                        findGroundPosition(
                            world,
                            x,
                            z,
                            sourceFrame.widthAxis,
                        ) ?: continue

                    val frame =
                        createFrame(
                            world,
                            bottomLeft,
                            sourceFrame.widthAxis,
                        ) ?: continue

                    return frame
                }
            }
        }

        return null
    }

    private fun findGroundPosition(
        world: ServerLevel,
        x: Int,
        z: Int,
        widthAxis: Direction.Axis,
    ): BlockPos? {
        for (y in world.getMaxY() - 6 downTo world.getMinY() + 1) {
            val bottomLeft = BlockPos(x, y, z)

            if (
                !isSuitablePortalLocation(
                    world,
                    bottomLeft,
                    widthAxis,
                )
            ) {
                continue
            }

            return bottomLeft
        }

        return null
    }

    private fun isSuitablePortalLocation(
        world: ServerLevel,
        bottomLeft: BlockPos,
        widthAxis: Direction.Axis,
    ): Boolean {
        val widthDirection = widthDirection(widthAxis)
        val groundY = bottomLeft.y - 1

        for (x in 0..3) {
            val ground =
                BlockPos(
                    bottomLeft.x + if (widthAxis == Direction.Axis.X) x else 0,
                    groundY,
                    bottomLeft.z + if (widthAxis == Direction.Axis.Z) x else 0,
                )

            if (!world.getBlockState(ground).isSolid) {
                return false
            }
        }

        /*
         * The entire 4x5 frame area must be replaceable.
         *
         * We only allow air or existing Parallel Worlds portal
         * blocks. Nothing else gets destroyed.
         */
        for (x in 0..3) {
            for (y in 0..4) {
                val pos =
                    bottomLeft
                        .relative(
                            widthDirection,
                            x,
                        )
                        .above(y)

                val state = world.getBlockState(pos)

                if (
                    !world.isEmptyBlock(pos) &&
                        !state.`is`(Blocks.NETHER_PORTAL) &&
                        !state.`is`(Blocks.CRYING_OBSIDIAN)
                ) {
                    return false
                }
            }
        }

        return true
    }

    private fun createFrame(
        world: ServerLevel,
        bottomLeft: BlockPos,
        widthAxis: Direction.Axis,
    ): PortalFrame? {
        if (
            !isSuitablePortalLocation(
                world,
                bottomLeft,
                widthAxis,
            )
        ) {
            return null
        }

        val widthDirection = widthDirection(widthAxis)

        for (x in 0..3) {
            val bottom =
                bottomLeft.relative(
                    widthDirection,
                    x,
                )

            val top = bottom.above(4)

            world.setBlock(
                bottom,
                Blocks.CRYING_OBSIDIAN.defaultBlockState(),
                3,
            )

            world.setBlock(
                top,
                Blocks.CRYING_OBSIDIAN.defaultBlockState(),
                3,
            )
        }

        for (y in 0..4) {
            val left = bottomLeft.above(y)

            val right = bottomLeft.relative(widthDirection, 3).above(y)

            world.setBlock(
                left,
                Blocks.CRYING_OBSIDIAN.defaultBlockState(),
                3,
            )

            world.setBlock(
                right,
                Blocks.CRYING_OBSIDIAN.defaultBlockState(),
                3,
            )
        }

        val frame =
            PortalFrame(
                bottomLeft = bottomLeft,
                widthAxis = widthAxis,
                portalAxis = widthAxis,
            )

        fillPortal(world, frame)

        return frame
    }

    private fun portalCenter(frame: PortalFrame): BlockPos {
        val widthDirection = widthDirection(frame.widthAxis)

        return frame.bottomLeft.relative(widthDirection, 1).above(1)
    }

    private fun fillPortal(
        world: ServerLevel,
        frame: PortalFrame,
    ) {
        val widthDirection = widthDirection(frame.widthAxis)

        val portalState =
            Blocks.NETHER_PORTAL.defaultBlockState()
                .setValue(
                    NetherPortalBlock.AXIS,
                    frame.portalAxis,
                )

        for (x in 1..2) {
            for (y in 1..3) {
                val pos = frame.bottomLeft.relative(widthDirection, x).above(y)

                world.setBlock(
                    pos,
                    portalState,
                    3,
                )
            }
        }
    }

    private fun findFrameNear(
        world: ServerLevel,
        clicked: BlockPos,
    ): PortalFrame? {
        for (x in -4..4) {
            for (y in -4..4) {
                for (z in -4..4) {
                    val candidate =
                        clicked.offset(
                            x,
                            y,
                            z,
                        )

                    findFrameAt(
                            world,
                            candidate,
                            Direction.Axis.X,
                        )
                        ?.let {
                            return it
                        }

                    findFrameAt(
                            world,
                            candidate,
                            Direction.Axis.Z,
                        )
                        ?.let {
                            return it
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
    ): PortalFrame? {
        val widthDirection = widthDirection(widthAxis)

        for (x in 0..3) {
            val bottom =
                bottomLeft.relative(
                    widthDirection,
                    x,
                )

            if (!isCryingObsidian(world, bottom)) {
                return null
            }

            val top = bottom.above(4)

            if (!isCryingObsidian(world, top)) {
                return null
            }
        }

        for (y in 0..4) {
            val left = bottomLeft.above(y)

            val right = bottomLeft.relative(widthDirection, 3).above(y)

            if (!isCryingObsidian(world, left)) {
                return null
            }

            if (!isCryingObsidian(world, right)) {
                return null
            }
        }

        for (x in 1..2) {
            for (y in 1..3) {
                val pos = bottomLeft.relative(widthDirection, x).above(y)

                val state = world.getBlockState(pos)

                if (!world.isEmptyBlock(pos) && !state.`is`(Blocks.NETHER_PORTAL)) {
                    return null
                }
            }
        }

        return PortalFrame(
            bottomLeft = bottomLeft,
            widthAxis = widthAxis,
            portalAxis = widthAxis,
        )
    }

    private fun findFrameNearPortal(
        world: ServerLevel,
        portalPos: BlockPos,
        portalAxis: Direction.Axis,
    ): PortalFrame? {
        val widthAxis = portalAxis

        var bottom = portalPos

        while (world.getBlockState(bottom.below()).`is`(Blocks.NETHER_PORTAL)) {
            bottom = bottom.below()
        }

        val direction =
            when (widthAxis) {
                Direction.Axis.X -> Direction.WEST
                Direction.Axis.Z -> Direction.NORTH
                else -> return null
            }

        while (world.getBlockState(bottom.relative(direction)).`is`(Blocks.NETHER_PORTAL)) {
            bottom = bottom.relative(direction)
        }

        val frameBottomLeft = bottom.relative(direction).below()

        return findFrameAt(
            world,
            frameBottomLeft,
            widthAxis,
        )
    }

    private fun widthDirection(axis: Direction.Axis): Direction {
        return when (axis) {
            Direction.Axis.X -> Direction.EAST
            Direction.Axis.Z -> Direction.SOUTH
            else -> throw IllegalArgumentException("Portal width cannot use vertical axis")
        }
    }

    private fun isCryingObsidian(
        world: ServerLevel,
        pos: BlockPos,
    ): Boolean {
        return world.getBlockState(pos).`is`(Blocks.CRYING_OBSIDIAN)
    }

    data class PortalDestination(
        val world: ServerLevel,
        val position: BlockPos,
    )

    private data class PortalFrame(
        val bottomLeft: BlockPos,
        val widthAxis: Direction.Axis,
        val portalAxis: Direction.Axis,
    )
}
