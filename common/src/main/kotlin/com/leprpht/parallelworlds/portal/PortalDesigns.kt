package com.leprpht.parallelworlds.portal

import net.minecraft.world.level.block.Blocks

object PortalDesigns {
    val PLAINS =
        PortalDesign(
            topLeft = Blocks.POPPY,
            topInnerLeft = Blocks.OAK_LOG,
            topInnerRight = Blocks.OAK_LOG,
            topRight = Blocks.POPPY,
            upperLeft = Blocks.GRASS_BLOCK,
            upperRight = Blocks.GRASS_BLOCK,
            middleLeft = Blocks.DIRT,
            middleRight = Blocks.DIRT,
            lowerLeft = Blocks.STONE,
            lowerRight = Blocks.STONE,
            bottomLeft = Blocks.SMOOTH_STONE,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.SMOOTH_STONE,
        )

    val SUNFLOWER_PLAINS =
        PortalDesign(
            topLeft = Blocks.SUNFLOWER,
            topInnerLeft = Blocks.OAK_LOG,
            topInnerRight = Blocks.OAK_LOG,
            topRight = Blocks.SUNFLOWER,
            upperLeft = Blocks.GRASS_BLOCK,
            upperRight = Blocks.GRASS_BLOCK,
            middleLeft = Blocks.DIRT,
            middleRight = Blocks.DIRT,
            lowerLeft = Blocks.STONE,
            lowerRight = Blocks.STONE,
            bottomLeft = Blocks.SMOOTH_STONE,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.SMOOTH_STONE,
        )

    val FOREST =
        PortalDesign(
            topLeft = Blocks.OAK_LEAVES,
            topInnerLeft = Blocks.STRIPPED_BIRCH_LOG,
            topInnerRight = Blocks.STRIPPED_BIRCH_LOG,
            topRight = Blocks.OAK_LEAVES,
            upperLeft = Blocks.STRIPPED_BIRCH_LOG,
            upperRight = Blocks.STRIPPED_BIRCH_LOG,
            middleLeft = Blocks.BIRCH_PLANKS,
            middleRight = Blocks.BIRCH_PLANKS,
            lowerLeft = Blocks.STRIPPED_OAK_LOG,
            lowerRight = Blocks.STRIPPED_OAK_LOG,
            bottomLeft = Blocks.MOSS_BLOCK,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.MOSS_BLOCK,
        )

    val FLOWER_FOREST =
        PortalDesign(
            topLeft = Blocks.FLOWERING_AZALEA_LEAVES,
            topInnerLeft = Blocks.STRIPPED_BIRCH_LOG,
            topInnerRight = Blocks.STRIPPED_BIRCH_LOG,
            topRight = Blocks.FLOWERING_AZALEA_LEAVES,
            upperLeft = Blocks.STRIPPED_BIRCH_LOG,
            upperRight = Blocks.STRIPPED_BIRCH_LOG,
            middleLeft = Blocks.BIRCH_PLANKS,
            middleRight = Blocks.BIRCH_PLANKS,
            lowerLeft = Blocks.STRIPPED_OAK_LOG,
            lowerRight = Blocks.STRIPPED_OAK_LOG,
            bottomLeft = Blocks.MOSS_BLOCK,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.MOSS_BLOCK,
        )

    val BIRCH_FOREST =
        PortalDesign(
            topLeft = Blocks.BIRCH_LEAVES,
            topInnerLeft = Blocks.STRIPPED_BIRCH_LOG,
            topInnerRight = Blocks.STRIPPED_BIRCH_LOG,
            topRight = Blocks.BIRCH_LEAVES,
            upperLeft = Blocks.STRIPPED_BIRCH_LOG,
            upperRight = Blocks.STRIPPED_BIRCH_LOG,
            middleLeft = Blocks.BIRCH_PLANKS,
            middleRight = Blocks.BIRCH_PLANKS,
            lowerLeft = Blocks.BIRCH_LOG,
            lowerRight = Blocks.BIRCH_LOG,
            bottomLeft = Blocks.DYED_TERRACOTTA.white(),
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.DYED_TERRACOTTA.white(),
        )

    val OLD_GROWTH_BIRCH_FOREST =
        PortalDesign(
            topLeft = Blocks.BIRCH_LEAVES,
            topInnerLeft = Blocks.STRIPPED_BIRCH_LOG,
            topInnerRight = Blocks.STRIPPED_BIRCH_LOG,
            topRight = Blocks.BIRCH_LEAVES,
            upperLeft = Blocks.STRIPPED_BIRCH_LOG,
            upperRight = Blocks.STRIPPED_BIRCH_LOG,
            middleLeft = Blocks.BIRCH_PLANKS,
            middleRight = Blocks.BIRCH_PLANKS,
            lowerLeft = Blocks.BIRCH_LOG,
            lowerRight = Blocks.BIRCH_LOG,
            bottomLeft = Blocks.BIRCH_LOG,
            bottomInnerLeft = Blocks.MOSSY_COBBLESTONE,
            bottomInnerRight = Blocks.MOSSY_COBBLESTONE,
            bottomRight = Blocks.BIRCH_LOG,
        )

    val DARK_FOREST =
        PortalDesign(
            topLeft = Blocks.DARK_OAK_LEAVES,
            topInnerLeft = Blocks.STRIPPED_DARK_OAK_LOG,
            topInnerRight = Blocks.STRIPPED_DARK_OAK_LOG,
            topRight = Blocks.DARK_OAK_LEAVES,
            upperLeft = Blocks.STRIPPED_DARK_OAK_LOG,
            upperRight = Blocks.STRIPPED_DARK_OAK_LOG,
            middleLeft = Blocks.DARK_OAK_PLANKS,
            middleRight = Blocks.DARK_OAK_PLANKS,
            lowerLeft = Blocks.DARK_OAK_LOG,
            lowerRight = Blocks.DARK_OAK_LOG,
            bottomLeft = Blocks.DYED_TERRACOTTA.brown(),
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.DYED_TERRACOTTA.brown(),
        )

    val DAPPLED_FOREST =
        PortalDesign(
            topLeft = Blocks.ORANGE_POPLAR_LEAVES,
            topInnerLeft = Blocks.RED_POPLAR_LEAVES,
            topInnerRight = Blocks.RED_POPLAR_LEAVES,
            topRight = Blocks.ORANGE_POPLAR_LEAVES,
            upperLeft = Blocks.YELLOW_POPLAR_LEAVES,
            upperRight = Blocks.YELLOW_POPLAR_LEAVES,
            middleLeft = Blocks.STRIPPED_POPLAR_LOG,
            middleRight = Blocks.STRIPPED_POPLAR_LOG,
            lowerLeft = Blocks.POPLAR_PLANKS,
            lowerRight = Blocks.POPLAR_PLANKS,
            bottomLeft = Blocks.POPLAR_LOG,
            bottomInnerLeft = Blocks.MOSSY_COBBLESTONE,
            bottomInnerRight = Blocks.MOSSY_COBBLESTONE,
            bottomRight = Blocks.POPLAR_LOG,
        )

    val PALE_GARDEN =
        PortalDesign(
            topLeft = Blocks.STRIPPED_PALE_OAK_LOG,
            topInnerLeft = Blocks.PALE_OAK_LEAVES,
            topInnerRight = Blocks.PALE_OAK_LEAVES,
            topRight = Blocks.STRIPPED_PALE_OAK_LOG,
            upperLeft = Blocks.STRIPPED_PALE_OAK_LOG,
            upperRight = Blocks.STRIPPED_PALE_OAK_LOG,
            middleLeft = Blocks.PALE_OAK_PLANKS,
            middleRight = Blocks.PALE_OAK_PLANKS,
            lowerLeft = Blocks.PALE_OAK_LOG,
            lowerRight = Blocks.PALE_OAK_LOG,
            bottomLeft = Blocks.RESIN_BLOCK,
            bottomInnerLeft = Blocks.MOSSY_COBBLESTONE,
            bottomInnerRight = Blocks.MOSSY_COBBLESTONE,
            bottomRight = Blocks.RESIN_BLOCK,
        )

    val CHERRY_GROVE =
        PortalDesign(
            topLeft = Blocks.DYED_TERRACOTTA.pink(),
            topInnerLeft = Blocks.CHERRY_LEAVES,
            topInnerRight = Blocks.CHERRY_LEAVES,
            topRight = Blocks.DYED_TERRACOTTA.pink(),
            upperLeft = Blocks.CHERRY_LOG,
            upperRight = Blocks.CHERRY_LOG,
            middleLeft = Blocks.STRIPPED_CHERRY_LOG,
            middleRight = Blocks.STRIPPED_CHERRY_LOG,
            lowerLeft = Blocks.CHERRY_LOG,
            lowerRight = Blocks.CHERRY_LOG,
            bottomLeft = Blocks.DYED_TERRACOTTA.pink(),
            bottomInnerLeft = Blocks.CHERRY_PLANKS,
            bottomInnerRight = Blocks.CHERRY_PLANKS,
            bottomRight = Blocks.DYED_TERRACOTTA.pink(),
        )

    val TAIGA =
        PortalDesign(
            topLeft = Blocks.MOSS_BLOCK,
            topInnerLeft = Blocks.STRIPPED_SPRUCE_LOG,
            topInnerRight = Blocks.STRIPPED_SPRUCE_LOG,
            topRight = Blocks.MOSS_BLOCK,
            upperLeft = Blocks.SPRUCE_PLANKS,
            upperRight = Blocks.SPRUCE_PLANKS,
            middleLeft = Blocks.STRIPPED_SPRUCE_LOG,
            middleRight = Blocks.STRIPPED_SPRUCE_LOG,
            lowerLeft = Blocks.SPRUCE_LOG,
            lowerRight = Blocks.SPRUCE_LOG,
            bottomLeft = Blocks.TUFF,
            bottomInnerLeft = Blocks.MOSSY_COBBLESTONE,
            bottomInnerRight = Blocks.MOSSY_COBBLESTONE,
            bottomRight = Blocks.TUFF,
        )

    val SNOWY_TAIGA =
        PortalDesign(
            topLeft = Blocks.SNOW_BLOCK,
            topInnerLeft = Blocks.STRIPPED_SPRUCE_LOG,
            topInnerRight = Blocks.STRIPPED_SPRUCE_LOG,
            topRight = Blocks.SNOW_BLOCK,
            upperLeft = Blocks.SPRUCE_PLANKS,
            upperRight = Blocks.SPRUCE_PLANKS,
            middleLeft = Blocks.STRIPPED_SPRUCE_LOG,
            middleRight = Blocks.STRIPPED_SPRUCE_LOG,
            lowerLeft = Blocks.SPRUCE_LOG,
            lowerRight = Blocks.SPRUCE_LOG,
            bottomLeft = Blocks.SNOW_BLOCK,
            bottomInnerLeft = Blocks.SPRUCE_LOG,
            bottomInnerRight = Blocks.SPRUCE_LOG,
            bottomRight = Blocks.SNOW_BLOCK,
        )

    val OLD_GROWTH_SPRUCE_TAIGA =
        PortalDesign(
            topLeft = Blocks.PODZOL,
            topInnerLeft = Blocks.SPRUCE_LEAVES,
            topInnerRight = Blocks.SPRUCE_LEAVES,
            topRight = Blocks.PODZOL,
            upperLeft = Blocks.SPRUCE_PLANKS,
            upperRight = Blocks.SPRUCE_PLANKS,
            middleLeft = Blocks.STRIPPED_SPRUCE_LOG,
            middleRight = Blocks.STRIPPED_SPRUCE_LOG,
            lowerLeft = Blocks.SPRUCE_LOG,
            lowerRight = Blocks.SPRUCE_LOG,
            bottomLeft = Blocks.SPRUCE_LEAVES,
            bottomInnerLeft = Blocks.MOSSY_COBBLESTONE,
            bottomInnerRight = Blocks.MOSSY_COBBLESTONE,
            bottomRight = Blocks.SPRUCE_LEAVES,
        )

    val OLD_GROWTH_PINE_TAIGA =
        PortalDesign(
            topLeft = Blocks.PODZOL,
            topInnerLeft = Blocks.STRIPPED_SPRUCE_LOG,
            topInnerRight = Blocks.STRIPPED_SPRUCE_LOG,
            topRight = Blocks.PODZOL,
            upperLeft = Blocks.SPRUCE_PLANKS,
            upperRight = Blocks.SPRUCE_PLANKS,
            middleLeft = Blocks.STRIPPED_SPRUCE_LOG,
            middleRight = Blocks.STRIPPED_SPRUCE_LOG,
            lowerLeft = Blocks.SPRUCE_LOG,
            lowerRight = Blocks.SPRUCE_LOG,
            bottomLeft = Blocks.SPRUCE_LOG,
            bottomInnerLeft = Blocks.MOSSY_COBBLESTONE,
            bottomInnerRight = Blocks.MOSSY_COBBLESTONE,
            bottomRight = Blocks.SPRUCE_LOG,
        )

    val MEADOW =
        PortalDesign(
            topLeft = Blocks.POPPY,
            topInnerLeft = Blocks.FLOWERING_AZALEA_LEAVES,
            topInnerRight = Blocks.FLOWERING_AZALEA_LEAVES,
            topRight = Blocks.POPPY,
            upperLeft = Blocks.GRASS_BLOCK,
            upperRight = Blocks.GRASS_BLOCK,
            middleLeft = Blocks.STONE,
            middleRight = Blocks.STONE,
            lowerLeft = Blocks.MOSS_BLOCK,
            lowerRight = Blocks.MOSS_BLOCK,
            bottomLeft = Blocks.DYED_TERRACOTTA.red(),
            bottomInnerLeft = Blocks.MOSS_BLOCK,
            bottomInnerRight = Blocks.MOSS_BLOCK,
            bottomRight = Blocks.DYED_TERRACOTTA.red(),
        )

    val GROVE =
        PortalDesign(
            topLeft = Blocks.POWDER_SNOW,
            topInnerLeft = Blocks.SPRUCE_LEAVES,
            topInnerRight = Blocks.SPRUCE_LEAVES,
            topRight = Blocks.POWDER_SNOW,
            upperLeft = Blocks.SPRUCE_PLANKS,
            upperRight = Blocks.SPRUCE_PLANKS,
            middleLeft = Blocks.STRIPPED_SPRUCE_LOG,
            middleRight = Blocks.STRIPPED_SPRUCE_LOG,
            lowerLeft = Blocks.SPRUCE_LOG,
            lowerRight = Blocks.SPRUCE_LOG,
            bottomLeft = Blocks.POWDER_SNOW,
            bottomInnerLeft = Blocks.SPRUCE_LOG,
            bottomInnerRight = Blocks.SPRUCE_LOG,
            bottomRight = Blocks.POWDER_SNOW,
        )

    val SNOWY_SLOPES =
        PortalDesign(
            topLeft = Blocks.SNOW_BLOCK,
            topInnerLeft = Blocks.SNOW_BLOCK,
            topInnerRight = Blocks.SNOW_BLOCK,
            topRight = Blocks.SNOW_BLOCK,
            upperLeft = Blocks.PACKED_ICE,
            upperRight = Blocks.PACKED_ICE,
            middleLeft = Blocks.SNOW_BLOCK,
            middleRight = Blocks.SNOW_BLOCK,
            lowerLeft = Blocks.DIRT,
            lowerRight = Blocks.DIRT,
            bottomLeft = Blocks.GLAZED_TERRACOTTA.blue(),
            bottomInnerLeft = Blocks.SNOW_BLOCK,
            bottomInnerRight = Blocks.SNOW_BLOCK,
            bottomRight = Blocks.GLAZED_TERRACOTTA.blue(),
        )

    val JAGGED_PEAKS =
        PortalDesign(
            topLeft = Blocks.SNOW_BLOCK,
            topInnerLeft = Blocks.SNOW_BLOCK,
            topInnerRight = Blocks.SNOW_BLOCK,
            topRight = Blocks.SNOW_BLOCK,
            upperLeft = Blocks.PACKED_ICE,
            upperRight = Blocks.PACKED_ICE,
            middleLeft = Blocks.SNOW_BLOCK,
            middleRight = Blocks.SNOW_BLOCK,
            lowerLeft = Blocks.STONE,
            lowerRight = Blocks.STONE,
            bottomLeft = Blocks.CALCITE,
            bottomInnerLeft = Blocks.SNOW_BLOCK,
            bottomInnerRight = Blocks.SNOW_BLOCK,
            bottomRight = Blocks.CALCITE,
        )

    val FROZEN_PEAKS =
        PortalDesign(
            topLeft = Blocks.PACKED_ICE,
            topInnerLeft = Blocks.PACKED_ICE,
            topInnerRight = Blocks.PACKED_ICE,
            topRight = Blocks.PACKED_ICE,
            upperLeft = Blocks.SNOW_BLOCK,
            upperRight = Blocks.SNOW_BLOCK,
            middleLeft = Blocks.PACKED_ICE,
            middleRight = Blocks.PACKED_ICE,
            lowerLeft = Blocks.STONE,
            lowerRight = Blocks.STONE,
            bottomLeft = Blocks.CALCITE,
            bottomInnerLeft = Blocks.SNOW_BLOCK,
            bottomInnerRight = Blocks.SNOW_BLOCK,
            bottomRight = Blocks.CALCITE,
        )

    val STONY_PEAKS =
        PortalDesign(
            topLeft = Blocks.COAL_ORE,
            topInnerLeft = Blocks.STONE,
            topInnerRight = Blocks.STONE,
            topRight = Blocks.COAL_ORE,
            upperLeft = Blocks.STONE,
            upperRight = Blocks.STONE,
            middleLeft = Blocks.CALCITE,
            middleRight = Blocks.CALCITE,
            lowerLeft = Blocks.STONE,
            lowerRight = Blocks.STONE,
            bottomLeft = Blocks.COAL_ORE,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.COAL_ORE,
        )

    val WINDSWEPT_HILLS =
        PortalDesign(
            topLeft = Blocks.GRASS_BLOCK,
            topInnerLeft = Blocks.GRASS_BLOCK,
            topInnerRight = Blocks.GRASS_BLOCK,
            topRight = Blocks.GRASS_BLOCK,
            upperLeft = Blocks.STONE,
            upperRight = Blocks.STONE,
            middleLeft = Blocks.COAL_ORE,
            middleRight = Blocks.COAL_ORE,
            lowerLeft = Blocks.STONE,
            lowerRight = Blocks.STONE,
            bottomLeft = Blocks.DIRT,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.DIRT,
        )

    val WINDSWEPT_GRAVELLY_HILLS =
        PortalDesign(
            topLeft = Blocks.GRAVEL,
            topInnerLeft = Blocks.GRASS_BLOCK,
            topInnerRight = Blocks.GRASS_BLOCK,
            topRight = Blocks.GRAVEL,
            upperLeft = Blocks.STONE,
            upperRight = Blocks.STONE,
            middleLeft = Blocks.COAL_ORE,
            middleRight = Blocks.COAL_ORE,
            lowerLeft = Blocks.STONE,
            lowerRight = Blocks.STONE,
            bottomLeft = Blocks.GRAVEL,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.GRAVEL,
        )

    val WINDSWEPT_FOREST =
        PortalDesign(
            topLeft = Blocks.OAK_LOG,
            topInnerLeft = Blocks.GRASS_BLOCK,
            topInnerRight = Blocks.GRASS_BLOCK,
            topRight = Blocks.OAK_LOG,
            upperLeft = Blocks.STONE,
            upperRight = Blocks.STONE,
            middleLeft = Blocks.COAL_ORE,
            middleRight = Blocks.COAL_ORE,
            lowerLeft = Blocks.STONE,
            lowerRight = Blocks.STONE,
            bottomLeft = Blocks.DIRT,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.DIRT,
        )

    val WINDSWEPT_SAVANNA =
        PortalDesign(
            topLeft = Blocks.STRIPPED_ACACIA_LOG,
            topInnerLeft = Blocks.GRASS_BLOCK,
            topInnerRight = Blocks.GRASS_BLOCK,
            topRight = Blocks.STRIPPED_ACACIA_LOG,
            upperLeft = Blocks.STONE,
            upperRight = Blocks.STONE,
            middleLeft = Blocks.COAL_ORE,
            middleRight = Blocks.COAL_ORE,
            lowerLeft = Blocks.STONE,
            lowerRight = Blocks.STONE,
            bottomLeft = Blocks.DIRT,
            bottomInnerLeft = Blocks.ACACIA_LOG,
            bottomInnerRight = Blocks.ACACIA_LOG,
            bottomRight = Blocks.DIRT,
        )

    val ICE_SPIKES =
        PortalDesign(
            topLeft = Blocks.PACKED_ICE,
            topInnerLeft = Blocks.SNOW_BLOCK,
            topInnerRight = Blocks.SNOW_BLOCK,
            topRight = Blocks.PACKED_ICE,
            upperLeft = Blocks.PACKED_ICE,
            upperRight = Blocks.PACKED_ICE,
            middleLeft = Blocks.PACKED_ICE,
            middleRight = Blocks.PACKED_ICE,
            lowerLeft = Blocks.BLUE_ICE,
            lowerRight = Blocks.BLUE_ICE,
            bottomLeft = Blocks.BLUE_ICE,
            bottomInnerLeft = Blocks.SNOW_BLOCK,
            bottomInnerRight = Blocks.SNOW_BLOCK,
            bottomRight = Blocks.BLUE_ICE,
        )

    val SNOWY_PLAINS =
        PortalDesign(
            topLeft = Blocks.SNOW_BLOCK,
            topInnerLeft = Blocks.SNOW_BLOCK,
            topInnerRight = Blocks.SNOW_BLOCK,
            topRight = Blocks.SNOW_BLOCK,
            upperLeft = Blocks.SNOW_BLOCK,
            upperRight = Blocks.SNOW_BLOCK,
            middleLeft = Blocks.SNOW_BLOCK,
            middleRight = Blocks.SNOW_BLOCK,
            lowerLeft = Blocks.SNOW_BLOCK,
            lowerRight = Blocks.SNOW_BLOCK,
            bottomLeft = Blocks.SNOW_BLOCK,
            bottomInnerLeft = Blocks.SNOW_BLOCK,
            bottomInnerRight = Blocks.SNOW_BLOCK,
            bottomRight = Blocks.SNOW_BLOCK,
        )

    val DESERT =
        PortalDesign(
            topLeft = Blocks.SAND,
            topInnerLeft = Blocks.CUT_SANDSTONE,
            topInnerRight = Blocks.CUT_SANDSTONE,
            topRight = Blocks.SAND,
            upperLeft = Blocks.SMOOTH_SANDSTONE,
            upperRight = Blocks.SMOOTH_SANDSTONE,
            middleLeft = Blocks.CUT_SANDSTONE,
            middleRight = Blocks.CUT_SANDSTONE,
            lowerLeft = Blocks.CUT_SANDSTONE,
            lowerRight = Blocks.CUT_SANDSTONE,
            bottomLeft = Blocks.DYED_TERRACOTTA.orange(),
            bottomInnerLeft = Blocks.SANDSTONE,
            bottomInnerRight = Blocks.SANDSTONE,
            bottomRight = Blocks.DYED_TERRACOTTA.orange(),
        )

    val SAVANNA =
        PortalDesign(
            topLeft = Blocks.STRIPPED_ACACIA_LOG,
            topInnerLeft = Blocks.ACACIA_LEAVES,
            topInnerRight = Blocks.ACACIA_LEAVES,
            topRight = Blocks.STRIPPED_ACACIA_LOG,
            upperLeft = Blocks.ACACIA_PLANKS,
            upperRight = Blocks.ACACIA_PLANKS,
            middleLeft = Blocks.STRIPPED_ACACIA_LOG,
            middleRight = Blocks.STRIPPED_ACACIA_LOG,
            lowerLeft = Blocks.ACACIA_LOG,
            lowerRight = Blocks.ACACIA_LOG,
            bottomLeft = Blocks.DYED_TERRACOTTA.brown(),
            bottomInnerLeft = Blocks.ACACIA_PLANKS,
            bottomInnerRight = Blocks.ACACIA_PLANKS,
            bottomRight = Blocks.DYED_TERRACOTTA.brown(),
        )

    val SAVANNA_PLATEAU =
        PortalDesign(
            topLeft = Blocks.STRIPPED_ACACIA_LOG,
            topInnerLeft = Blocks.ACACIA_LEAVES,
            topInnerRight = Blocks.ACACIA_LEAVES,
            topRight = Blocks.STRIPPED_ACACIA_LOG,
            upperLeft = Blocks.ACACIA_PLANKS,
            upperRight = Blocks.ACACIA_PLANKS,
            middleLeft = Blocks.COARSE_DIRT,
            middleRight = Blocks.COARSE_DIRT,
            lowerLeft = Blocks.ACACIA_LOG,
            lowerRight = Blocks.ACACIA_LOG,
            bottomLeft = Blocks.STONE,
            bottomInnerLeft = Blocks.COARSE_DIRT,
            bottomInnerRight = Blocks.COARSE_DIRT,
            bottomRight = Blocks.STONE,
        )

    val BADLANDS =
        PortalDesign(
            topLeft = Blocks.RED_SAND,
            topInnerLeft = Blocks.CUT_RED_SANDSTONE,
            topInnerRight = Blocks.CUT_RED_SANDSTONE,
            topRight = Blocks.RED_SAND,
            upperLeft = Blocks.DYED_TERRACOTTA.white(),
            upperRight = Blocks.DYED_TERRACOTTA.white(),
            middleLeft = Blocks.TERRACOTTA,
            middleRight = Blocks.TERRACOTTA,
            lowerLeft = Blocks.DYED_TERRACOTTA.brown(),
            lowerRight = Blocks.DYED_TERRACOTTA.brown(),
            bottomLeft = Blocks.DYED_TERRACOTTA.orange(),
            bottomInnerLeft = Blocks.CUT_RED_SANDSTONE,
            bottomInnerRight = Blocks.CUT_RED_SANDSTONE,
            bottomRight = Blocks.DYED_TERRACOTTA.orange(),
        )

    val WOODED_BADLANDS =
        PortalDesign(
            topLeft = Blocks.OAK_LOG,
            topInnerLeft = Blocks.COARSE_DIRT,
            topInnerRight = Blocks.COARSE_DIRT,
            topRight = Blocks.OAK_LOG,
            upperLeft = Blocks.DYED_TERRACOTTA.orange(),
            upperRight = Blocks.DYED_TERRACOTTA.orange(),
            middleLeft = Blocks.TERRACOTTA,
            middleRight = Blocks.TERRACOTTA,
            lowerLeft = Blocks.OAK_LOG,
            lowerRight = Blocks.OAK_LOG,
            bottomLeft = Blocks.DYED_TERRACOTTA.orange(),
            bottomInnerLeft = Blocks.CUT_RED_SANDSTONE,
            bottomInnerRight = Blocks.CUT_RED_SANDSTONE,
            bottomRight = Blocks.DYED_TERRACOTTA.orange(),
        )

    val ERODED_BADLANDS =
        PortalDesign(
            topLeft = Blocks.DEAD_BUSH,
            topInnerLeft = Blocks.CUT_RED_SANDSTONE,
            topInnerRight = Blocks.CUT_RED_SANDSTONE,
            topRight = Blocks.DEAD_BUSH,
            upperLeft = Blocks.RED_SAND,
            upperRight = Blocks.RED_SAND,
            middleLeft = Blocks.DYED_TERRACOTTA.yellow(),
            middleRight = Blocks.DYED_TERRACOTTA.yellow(),
            lowerLeft = Blocks.CUT_RED_SANDSTONE,
            lowerRight = Blocks.CUT_RED_SANDSTONE,
            bottomLeft = Blocks.DYED_TERRACOTTA.orange(),
            bottomInnerLeft = Blocks.CUT_RED_SANDSTONE,
            bottomInnerRight = Blocks.CUT_RED_SANDSTONE,
            bottomRight = Blocks.DYED_TERRACOTTA.orange(),
        )

    val JUNGLE =
        PortalDesign(
            topLeft = Blocks.STRIPPED_JUNGLE_LOG,
            topInnerLeft = Blocks.JUNGLE_LEAVES,
            topInnerRight = Blocks.JUNGLE_LEAVES,
            topRight = Blocks.STRIPPED_JUNGLE_LOG,
            upperLeft = Blocks.JUNGLE_PLANKS,
            upperRight = Blocks.JUNGLE_PLANKS,
            middleLeft = Blocks.JUNGLE_LOG,
            middleRight = Blocks.JUNGLE_LOG,
            lowerLeft = Blocks.JUNGLE_PLANKS,
            lowerRight = Blocks.JUNGLE_PLANKS,
            bottomLeft = Blocks.JUNGLE_LEAVES,
            bottomInnerLeft = Blocks.JUNGLE_PLANKS,
            bottomInnerRight = Blocks.JUNGLE_PLANKS,
            bottomRight = Blocks.JUNGLE_LEAVES,
        )

    val SPARSE_JUNGLE =
        PortalDesign(
            topLeft = Blocks.STRIPPED_JUNGLE_LOG,
            topInnerLeft = Blocks.JUNGLE_LEAVES,
            topInnerRight = Blocks.JUNGLE_LEAVES,
            topRight = Blocks.STRIPPED_JUNGLE_LOG,
            upperLeft = Blocks.JUNGLE_PLANKS,
            upperRight = Blocks.JUNGLE_PLANKS,
            middleLeft = Blocks.JUNGLE_LOG,
            middleRight = Blocks.JUNGLE_LOG,
            lowerLeft = Blocks.MOSSY_COBBLESTONE,
            lowerRight = Blocks.MOSSY_COBBLESTONE,
            bottomLeft = Blocks.MOSS_BLOCK,
            bottomInnerLeft = Blocks.JUNGLE_PLANKS,
            bottomInnerRight = Blocks.JUNGLE_PLANKS,
            bottomRight = Blocks.MOSS_BLOCK,
        )

    val BAMBOO_JUNGLE =
        PortalDesign(
            topLeft = Blocks.BAMBOO_BLOCK,
            topInnerLeft = Blocks.BAMBOO_MOSAIC,
            topInnerRight = Blocks.BAMBOO_MOSAIC,
            topRight = Blocks.BAMBOO_BLOCK,
            upperLeft = Blocks.BAMBOO_BLOCK,
            upperRight = Blocks.BAMBOO_BLOCK,
            middleLeft = Blocks.BAMBOO_BLOCK,
            middleRight = Blocks.BAMBOO_BLOCK,
            lowerLeft = Blocks.BAMBOO_BLOCK,
            lowerRight = Blocks.BAMBOO_BLOCK,
            bottomLeft = Blocks.BAMBOO_BLOCK,
            bottomInnerLeft = Blocks.BAMBOO_MOSAIC,
            bottomInnerRight = Blocks.BAMBOO_MOSAIC,
            bottomRight = Blocks.BAMBOO_BLOCK,
        )

    val SWAMP =
        PortalDesign(
            topLeft = Blocks.FIREFLY_BUSH,
            topInnerLeft = Blocks.MOSS_BLOCK,
            topInnerRight = Blocks.MOSS_BLOCK,
            topRight = Blocks.FIREFLY_BUSH,
            upperLeft = Blocks.GRASS_BLOCK,
            upperRight = Blocks.GRASS_BLOCK,
            middleLeft = Blocks.DIRT,
            middleRight = Blocks.DIRT,
            lowerLeft = Blocks.MOSSY_COBBLESTONE,
            lowerRight = Blocks.MOSSY_COBBLESTONE,
            bottomLeft = Blocks.DYED_TERRACOTTA.green(),
            bottomInnerLeft = Blocks.MOSSY_COBBLESTONE,
            bottomInnerRight = Blocks.MOSSY_COBBLESTONE,
            bottomRight = Blocks.DYED_TERRACOTTA.green(),
        )

    val MANGROVE_SWAMP =
        PortalDesign(
            topLeft = Blocks.MANGROVE_ROOTS,
            topInnerLeft = Blocks.MANGROVE_LEAVES,
            topInnerRight = Blocks.MANGROVE_LEAVES,
            topRight = Blocks.MANGROVE_ROOTS,
            upperLeft = Blocks.STRIPPED_MANGROVE_LOG,
            upperRight = Blocks.STRIPPED_MANGROVE_LOG,
            middleLeft = Blocks.MANGROVE_PLANKS,
            middleRight = Blocks.MANGROVE_PLANKS,
            lowerLeft = Blocks.MANGROVE_LOG,
            lowerRight = Blocks.MANGROVE_LOG,
            bottomLeft = Blocks.MUDDY_MANGROVE_ROOTS,
            bottomInnerLeft = Blocks.MUD,
            bottomInnerRight = Blocks.MUD,
            bottomRight = Blocks.MUDDY_MANGROVE_ROOTS,
        )

    val RIVER =
        PortalDesign(
            topLeft = Blocks.SAND,
            topInnerLeft = Blocks.CLAY,
            topInnerRight = Blocks.CLAY,
            topRight = Blocks.SAND,
            upperLeft = Blocks.CLAY,
            upperRight = Blocks.CLAY,
            middleLeft = Blocks.SAND,
            middleRight = Blocks.SAND,
            lowerLeft = Blocks.CLAY,
            lowerRight = Blocks.CLAY,
            bottomLeft = Blocks.STONE,
            bottomInnerLeft = Blocks.CLAY,
            bottomInnerRight = Blocks.CLAY,
            bottomRight = Blocks.STONE,
        )

    val FROZEN_RIVER =
        PortalDesign(
            topLeft = Blocks.SAND,
            topInnerLeft = Blocks.PACKED_ICE,
            topInnerRight = Blocks.PACKED_ICE,
            topRight = Blocks.SAND,
            upperLeft = Blocks.CLAY,
            upperRight = Blocks.CLAY,
            middleLeft = Blocks.SAND,
            middleRight = Blocks.SAND,
            lowerLeft = Blocks.CLAY,
            lowerRight = Blocks.CLAY,
            bottomLeft = Blocks.STONE,
            bottomInnerLeft = Blocks.CLAY,
            bottomInnerRight = Blocks.CLAY,
            bottomRight = Blocks.STONE,
        )

    val BEACH =
        PortalDesign(
            topLeft = Blocks.SAND,
            topInnerLeft = Blocks.SANDSTONE,
            topInnerRight = Blocks.SANDSTONE,
            topRight = Blocks.SAND,
            upperLeft = Blocks.SAND,
            upperRight = Blocks.SAND,
            middleLeft = Blocks.DIRT,
            middleRight = Blocks.DIRT,
            lowerLeft = Blocks.SAND,
            lowerRight = Blocks.SAND,
            bottomLeft = Blocks.STONE,
            bottomInnerLeft = Blocks.SAND,
            bottomInnerRight = Blocks.SAND,
            bottomRight = Blocks.STONE,
        )

    val SNOWY_BEACH =
        PortalDesign(
            topLeft = Blocks.SNOW_BLOCK,
            topInnerLeft = Blocks.SANDSTONE,
            topInnerRight = Blocks.SANDSTONE,
            topRight = Blocks.SNOW_BLOCK,
            upperLeft = Blocks.SAND,
            upperRight = Blocks.SAND,
            middleLeft = Blocks.DIRT,
            middleRight = Blocks.DIRT,
            lowerLeft = Blocks.SAND,
            lowerRight = Blocks.SAND,
            bottomLeft = Blocks.STONE,
            bottomInnerLeft = Blocks.SNOW_BLOCK,
            bottomInnerRight = Blocks.SNOW_BLOCK,
            bottomRight = Blocks.STONE,
        )

    val STONY_SHORE =
        PortalDesign(
            topLeft = Blocks.STONE,
            topInnerLeft = Blocks.SANDSTONE,
            topInnerRight = Blocks.SANDSTONE,
            topRight = Blocks.STONE,
            upperLeft = Blocks.SAND,
            upperRight = Blocks.SAND,
            middleLeft = Blocks.STONE,
            middleRight = Blocks.STONE,
            lowerLeft = Blocks.SAND,
            lowerRight = Blocks.SAND,
            bottomLeft = Blocks.STONE,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.STONE,
        )

    val OCEAN =
        PortalDesign(
            topLeft = Blocks.SEA_LANTERN,
            topInnerLeft = Blocks.SANDSTONE,
            topInnerRight = Blocks.SANDSTONE,
            topRight = Blocks.SEA_LANTERN,
            upperLeft = Blocks.SAND,
            upperRight = Blocks.SAND,
            middleLeft = Blocks.STONE,
            middleRight = Blocks.STONE,
            lowerLeft = Blocks.PRISMARINE,
            lowerRight = Blocks.PRISMARINE,
            bottomLeft = Blocks.DRIED_KELP_BLOCK,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.DRIED_KELP_BLOCK,
        )

    val WARM_OCEAN =
        PortalDesign(
            topLeft = Blocks.SEA_LANTERN,
            topInnerLeft = Blocks.SANDSTONE,
            topInnerRight = Blocks.SANDSTONE,
            topRight = Blocks.SEA_LANTERN,
            upperLeft = Blocks.SAND,
            upperRight = Blocks.SAND,
            middleLeft = Blocks.STONE,
            middleRight = Blocks.STONE,
            lowerLeft = Blocks.PRISMARINE_BRICKS,
            lowerRight = Blocks.PRISMARINE_BRICKS,
            bottomLeft = Blocks.PRISMARINE,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.PRISMARINE,
        )

    val LUKEWARM_OCEAN =
        PortalDesign(
            topLeft = Blocks.SEA_LANTERN,
            topInnerLeft = Blocks.SANDSTONE,
            topInnerRight = Blocks.SANDSTONE,
            topRight = Blocks.SEA_LANTERN,
            upperLeft = Blocks.SAND,
            upperRight = Blocks.SAND,
            middleLeft = Blocks.STONE,
            middleRight = Blocks.STONE,
            lowerLeft = Blocks.PRISMARINE_BRICKS,
            lowerRight = Blocks.PRISMARINE_BRICKS,
            bottomLeft = Blocks.SAND,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.SAND,
        )

    val DEEP_OCEAN =
        PortalDesign(
            topLeft = Blocks.SEA_LANTERN,
            topInnerLeft = Blocks.SANDSTONE,
            topInnerRight = Blocks.SANDSTONE,
            topRight = Blocks.SEA_LANTERN,
            upperLeft = Blocks.SAND,
            upperRight = Blocks.SAND,
            middleLeft = Blocks.STONE,
            middleRight = Blocks.STONE,
            lowerLeft = Blocks.PRISMARINE_BRICKS,
            lowerRight = Blocks.PRISMARINE_BRICKS,
            bottomLeft = Blocks.DARK_PRISMARINE,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.DARK_PRISMARINE,
        )

    val DEEP_LUKEWARM_OCEAN =
        PortalDesign(
            topLeft = Blocks.SEA_LANTERN,
            topInnerLeft = Blocks.SANDSTONE,
            topInnerRight = Blocks.SANDSTONE,
            topRight = Blocks.SEA_LANTERN,
            upperLeft = Blocks.SAND,
            upperRight = Blocks.SAND,
            middleLeft = Blocks.STONE,
            middleRight = Blocks.STONE,
            lowerLeft = Blocks.DARK_PRISMARINE,
            lowerRight = Blocks.DARK_PRISMARINE,
            bottomLeft = Blocks.SAND,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.SAND,
        )

    val COLD_OCEAN =
        PortalDesign(
            topLeft = Blocks.SEA_LANTERN,
            topInnerLeft = Blocks.PACKED_ICE,
            topInnerRight = Blocks.PACKED_ICE,
            topRight = Blocks.SEA_LANTERN,
            upperLeft = Blocks.SAND,
            upperRight = Blocks.SAND,
            middleLeft = Blocks.STONE,
            middleRight = Blocks.STONE,
            lowerLeft = Blocks.PRISMARINE_BRICKS,
            lowerRight = Blocks.PRISMARINE_BRICKS,
            bottomLeft = Blocks.PRISMARINE,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.PRISMARINE,
        )

    val DEEP_COLD_OCEAN =
        PortalDesign(
            topLeft = Blocks.SEA_LANTERN,
            topInnerLeft = Blocks.PACKED_ICE,
            topInnerRight = Blocks.PACKED_ICE,
            topRight = Blocks.SEA_LANTERN,
            upperLeft = Blocks.SAND,
            upperRight = Blocks.SAND,
            middleLeft = Blocks.STONE,
            middleRight = Blocks.STONE,
            lowerLeft = Blocks.PRISMARINE_BRICKS,
            lowerRight = Blocks.PRISMARINE_BRICKS,
            bottomLeft = Blocks.DARK_PRISMARINE,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.DARK_PRISMARINE,
        )

    val FROZEN_OCEAN =
        PortalDesign(
            topLeft = Blocks.SEA_LANTERN,
            topInnerLeft = Blocks.PACKED_ICE,
            topInnerRight = Blocks.PACKED_ICE,
            topRight = Blocks.SEA_LANTERN,
            upperLeft = Blocks.PRISMARINE_BRICKS,
            upperRight = Blocks.PRISMARINE_BRICKS,
            middleLeft = Blocks.BLUE_ICE,
            middleRight = Blocks.BLUE_ICE,
            lowerLeft = Blocks.PRISMARINE_BRICKS,
            lowerRight = Blocks.PRISMARINE_BRICKS,
            bottomLeft = Blocks.PRISMARINE,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.PRISMARINE,
        )

    val DEEP_FROZEN_OCEAN =
        PortalDesign(
            topLeft = Blocks.SEA_LANTERN,
            topInnerLeft = Blocks.PACKED_ICE,
            topInnerRight = Blocks.PACKED_ICE,
            topRight = Blocks.SEA_LANTERN,
            upperLeft = Blocks.PRISMARINE_BRICKS,
            upperRight = Blocks.PRISMARINE_BRICKS,
            middleLeft = Blocks.BLUE_ICE,
            middleRight = Blocks.BLUE_ICE,
            lowerLeft = Blocks.DARK_PRISMARINE,
            lowerRight = Blocks.DARK_PRISMARINE,
            bottomLeft = Blocks.DARK_PRISMARINE,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.DARK_PRISMARINE,
        )

    val MUSHROOM_FIELDS =
        PortalDesign(
            topLeft = Blocks.MYCELIUM,
            topInnerLeft = Blocks.MYCELIUM,
            topInnerRight = Blocks.MYCELIUM,
            topRight = Blocks.MYCELIUM,
            upperLeft = Blocks.DIRT,
            upperRight = Blocks.DIRT,
            middleLeft = Blocks.BROWN_MUSHROOM_BLOCK,
            middleRight = Blocks.BROWN_MUSHROOM_BLOCK,
            lowerLeft = Blocks.RED_MUSHROOM_BLOCK,
            lowerRight = Blocks.RED_MUSHROOM_BLOCK,
            bottomLeft = Blocks.BROWN_MUSHROOM_BLOCK,
            bottomInnerLeft = Blocks.MYCELIUM,
            bottomInnerRight = Blocks.MYCELIUM,
            bottomRight = Blocks.BROWN_MUSHROOM_BLOCK,
        )

    val LUSH_CAVES =
        PortalDesign(
            topLeft = Blocks.MOSS_BLOCK,
            topInnerLeft = Blocks.FLOWERING_AZALEA_LEAVES,
            topInnerRight = Blocks.FLOWERING_AZALEA_LEAVES,
            topRight = Blocks.MOSS_BLOCK,
            upperLeft = Blocks.MOSS_BLOCK,
            upperRight = Blocks.MOSS_BLOCK,
            middleLeft = Blocks.MOSS_BLOCK,
            middleRight = Blocks.MOSS_BLOCK,
            lowerLeft = Blocks.CLAY,
            lowerRight = Blocks.CLAY,
            bottomLeft = Blocks.CLAY,
            bottomInnerLeft = Blocks.STONE,
            bottomInnerRight = Blocks.STONE,
            bottomRight = Blocks.CLAY,
        )

    val DRIPSTONE_CAVES =
        PortalDesign(
            topLeft = Blocks.DRIPSTONE_BLOCK,
            topInnerLeft = Blocks.COPPER_ORE,
            topInnerRight = Blocks.COPPER_ORE,
            topRight = Blocks.DRIPSTONE_BLOCK,
            upperLeft = Blocks.DRIPSTONE_BLOCK,
            upperRight = Blocks.DRIPSTONE_BLOCK,
            middleLeft = Blocks.DRIPSTONE_BLOCK,
            middleRight = Blocks.DRIPSTONE_BLOCK,
            lowerLeft = Blocks.DRIPSTONE_BLOCK,
            lowerRight = Blocks.DRIPSTONE_BLOCK,
            bottomLeft = Blocks.DRIPSTONE_BLOCK,
            bottomInnerLeft = Blocks.COPPER_ORE,
            bottomInnerRight = Blocks.COPPER_ORE,
            bottomRight = Blocks.DRIPSTONE_BLOCK,
        )

    val DEEP_DARK =
        PortalDesign(
            topLeft = Blocks.SCULK,
            topInnerLeft = Blocks.SCULK,
            topInnerRight = Blocks.SCULK,
            topRight = Blocks.SCULK,
            upperLeft = Blocks.SCULK,
            upperRight = Blocks.SCULK,
            middleLeft = Blocks.DEEPSLATE_BRICKS,
            middleRight = Blocks.DEEPSLATE_BRICKS,
            lowerLeft = Blocks.DEEPSLATE_BRICKS,
            lowerRight = Blocks.DEEPSLATE_BRICKS,
            bottomLeft = Blocks.POLISHED_DEEPSLATE,
            bottomInnerLeft = Blocks.SCULK_CATALYST,
            bottomInnerRight = Blocks.SCULK_CATALYST,
            bottomRight = Blocks.POLISHED_DEEPSLATE,
        )

    val SULFUR_CAVES =
        PortalDesign(
            topLeft = Blocks.POLISHED_CINNABAR,
            topInnerLeft = Blocks.CHISELED_SULFUR,
            topInnerRight = Blocks.CHISELED_SULFUR,
            topRight = Blocks.POLISHED_CINNABAR,
            upperLeft = Blocks.CHISELED_CINNABAR,
            upperRight = Blocks.CHISELED_CINNABAR,
            middleLeft = Blocks.CINNABAR,
            middleRight = Blocks.CINNABAR,
            lowerLeft = Blocks.CINNABAR_BRICKS,
            lowerRight = Blocks.CINNABAR_BRICKS,
            bottomLeft = Blocks.POLISHED_CINNABAR,
            bottomInnerLeft = Blocks.POLISHED_SULFUR,
            bottomInnerRight = Blocks.POLISHED_SULFUR,
            bottomRight = Blocks.POLISHED_CINNABAR,
        )
}
