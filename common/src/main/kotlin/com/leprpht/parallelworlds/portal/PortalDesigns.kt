package com.leprpht.parallelworlds.portal

import com.leprpht.parallelworlds.compat.BlockCatalog
import java.util.Locale
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks

object PortalDesigns {
    private fun resourceLocation(id: String): Any? {
        val namespace = id.substringBefore(':', "minecraft")
        val path = id.substringAfter(':')
        val type = Class.forName("net.minecraft.resources.ResourceLocation")

        return runCatching {
            type
                .getMethod(
                    "fromNamespaceAndPath",
                    String::class.java,
                    String::class.java,
                )
                .invoke(null, namespace, path)
        }
            .recoverCatching {
                type
                    .getConstructor(String::class.java, String::class.java)
                    .newInstance(namespace, path)
            }
            .recoverCatching {
                type.getConstructor(String::class.java).newInstance("$namespace:$path")
            }
            .getOrNull()
    }

    private fun registeredBlock(id: String): Block? = runCatching {
        val location = resourceLocation(id) ?: return null
        val registryType = runCatching {
            Class.forName("net.minecraft.core.registries.BuiltInRegistries")
        }
            .getOrElse { Class.forName("net.minecraft.core.Registry") }
        val registry = registryType.getField("BLOCK").get(null)
        val contains = registry.javaClass.getMethod("containsKey", location.javaClass)
        if (!(contains.invoke(registry, location) as Boolean)) {
            return null
        }
        registry.javaClass.getMethod("get", location.javaClass).invoke(registry, location) as Block
    }
        .getOrNull()

    private fun fieldBlock(id: String): Block? = runCatching {
        Blocks::class.java.getField(id.uppercase(Locale.ROOT)).get(null) as Block
    }
        .getOrNull()

    private fun block(id: String, fallback: Block = Blocks.AIR): Block =
        registeredBlock(id)
            ?: registeredBlock(BlockCatalog.fallbackFor(id))
            ?: fieldBlock(id)
            ?: fallback

    val PLAINS =
        PortalDesign(
            topLeft = block("poppy", Blocks.AIR),
            topInnerLeft = block("oak_log", Blocks.AIR),
            topInnerRight = block("oak_log", Blocks.AIR),
            topRight = block("poppy", Blocks.AIR),
            upperLeft = block("grass_block", Blocks.AIR),
            upperRight = block("grass_block", Blocks.AIR),
            middleLeft = block("dirt", Blocks.AIR),
            middleRight = block("dirt", Blocks.AIR),
            lowerLeft = block("stone", Blocks.AIR),
            lowerRight = block("stone", Blocks.AIR),
            bottomLeft = block("smooth_stone", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("smooth_stone", Blocks.AIR),
        )

    val SUNFLOWER_PLAINS =
        PortalDesign(
            topLeft = block("sunflower", Blocks.AIR),
            topInnerLeft = block("oak_log", Blocks.AIR),
            topInnerRight = block("oak_log", Blocks.AIR),
            topRight = block("sunflower", Blocks.AIR),
            upperLeft = block("grass_block", Blocks.AIR),
            upperRight = block("grass_block", Blocks.AIR),
            middleLeft = block("dirt", Blocks.AIR),
            middleRight = block("dirt", Blocks.AIR),
            lowerLeft = block("stone", Blocks.AIR),
            lowerRight = block("stone", Blocks.AIR),
            bottomLeft = block("smooth_stone", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("smooth_stone", Blocks.AIR),
        )

    val FOREST =
        PortalDesign(
            topLeft = block("oak_leaves", Blocks.AIR),
            topInnerLeft = block("stripped_birch_log", Blocks.AIR),
            topInnerRight = block("stripped_birch_log", Blocks.AIR),
            topRight = block("oak_leaves", Blocks.AIR),
            upperLeft = block("stripped_birch_log", Blocks.AIR),
            upperRight = block("stripped_birch_log", Blocks.AIR),
            middleLeft = block("birch_planks", Blocks.AIR),
            middleRight = block("birch_planks", Blocks.AIR),
            lowerLeft = block("stripped_oak_log", Blocks.AIR),
            lowerRight = block("stripped_oak_log", Blocks.AIR),
            bottomLeft = block("moss_block", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("moss_block", Blocks.AIR),
        )

    val FLOWER_FOREST =
        PortalDesign(
            topLeft = block("flowering_azalea_leaves", Blocks.AIR),
            topInnerLeft = block("stripped_birch_log", Blocks.AIR),
            topInnerRight = block("stripped_birch_log", Blocks.AIR),
            topRight = block("flowering_azalea_leaves", Blocks.AIR),
            upperLeft = block("stripped_birch_log", Blocks.AIR),
            upperRight = block("stripped_birch_log", Blocks.AIR),
            middleLeft = block("birch_planks", Blocks.AIR),
            middleRight = block("birch_planks", Blocks.AIR),
            lowerLeft = block("stripped_oak_log", Blocks.AIR),
            lowerRight = block("stripped_oak_log", Blocks.AIR),
            bottomLeft = block("moss_block", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("moss_block", Blocks.AIR),
        )

    val BIRCH_FOREST =
        PortalDesign(
            topLeft = block("birch_leaves", Blocks.AIR),
            topInnerLeft = block("stripped_birch_log", Blocks.AIR),
            topInnerRight = block("stripped_birch_log", Blocks.AIR),
            topRight = block("birch_leaves", Blocks.AIR),
            upperLeft = block("stripped_birch_log", Blocks.AIR),
            upperRight = block("stripped_birch_log", Blocks.AIR),
            middleLeft = block("birch_planks", Blocks.AIR),
            middleRight = block("birch_planks", Blocks.AIR),
            lowerLeft = block("birch_log", Blocks.AIR),
            lowerRight = block("birch_log", Blocks.AIR),
            bottomLeft = block("white_terracotta"),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("white_terracotta"),
        )

    val OLD_GROWTH_BIRCH_FOREST =
        PortalDesign(
            topLeft = block("birch_leaves", Blocks.AIR),
            topInnerLeft = block("stripped_birch_log", Blocks.AIR),
            topInnerRight = block("stripped_birch_log", Blocks.AIR),
            topRight = block("birch_leaves", Blocks.AIR),
            upperLeft = block("stripped_birch_log", Blocks.AIR),
            upperRight = block("stripped_birch_log", Blocks.AIR),
            middleLeft = block("birch_planks", Blocks.AIR),
            middleRight = block("birch_planks", Blocks.AIR),
            lowerLeft = block("birch_log", Blocks.AIR),
            lowerRight = block("birch_log", Blocks.AIR),
            bottomLeft = block("birch_log", Blocks.AIR),
            bottomInnerLeft = block("mossy_cobblestone", Blocks.AIR),
            bottomInnerRight = block("mossy_cobblestone", Blocks.AIR),
            bottomRight = block("birch_log", Blocks.AIR),
        )

    val DARK_FOREST =
        PortalDesign(
            topLeft = block("dark_oak_leaves", Blocks.AIR),
            topInnerLeft = block("stripped_dark_oak_log", Blocks.AIR),
            topInnerRight = block("stripped_dark_oak_log", Blocks.AIR),
            topRight = block("dark_oak_leaves", Blocks.AIR),
            upperLeft = block("stripped_dark_oak_log", Blocks.AIR),
            upperRight = block("stripped_dark_oak_log", Blocks.AIR),
            middleLeft = block("dark_oak_planks", Blocks.AIR),
            middleRight = block("dark_oak_planks", Blocks.AIR),
            lowerLeft = block("dark_oak_log", Blocks.AIR),
            lowerRight = block("dark_oak_log", Blocks.AIR),
            bottomLeft = block("brown_terracotta"),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("brown_terracotta"),
        )

    val DAPPLED_FOREST =
        PortalDesign(
            topLeft = block("orange_poplar_leaves", block("oak_leaves", Blocks.AIR)),
            topInnerLeft = block("red_poplar_leaves", block("oak_leaves", Blocks.AIR)),
            topInnerRight = block("red_poplar_leaves", block("oak_leaves", Blocks.AIR)),
            topRight = block("orange_poplar_leaves", block("oak_leaves", Blocks.AIR)),
            upperLeft = block("yellow_poplar_leaves", block("oak_leaves", Blocks.AIR)),
            upperRight = block("yellow_poplar_leaves", block("oak_leaves", Blocks.AIR)),
            middleLeft = block("stripped_poplar_log", block("oak_log", Blocks.AIR)),
            middleRight = block("stripped_poplar_log", block("oak_log", Blocks.AIR)),
            lowerLeft = block("poplar_planks", block("oak_planks", Blocks.AIR)),
            lowerRight = block("poplar_planks", block("oak_planks", Blocks.AIR)),
            bottomLeft = block("poplar_log", block("oak_log", Blocks.AIR)),
            bottomInnerLeft = block("mossy_cobblestone", Blocks.AIR),
            bottomInnerRight = block("mossy_cobblestone", Blocks.AIR),
            bottomRight = block("poplar_log", block("oak_log", Blocks.AIR)),
        )

    val PALE_GARDEN =
        PortalDesign(
            topLeft = block("stripped_pale_oak_log", Blocks.AIR),
            topInnerLeft = block("pale_oak_leaves", Blocks.AIR),
            topInnerRight = block("pale_oak_leaves", Blocks.AIR),
            topRight = block("stripped_pale_oak_log", Blocks.AIR),
            upperLeft = block("stripped_pale_oak_log", Blocks.AIR),
            upperRight = block("stripped_pale_oak_log", Blocks.AIR),
            middleLeft = block("pale_oak_planks", Blocks.AIR),
            middleRight = block("pale_oak_planks", Blocks.AIR),
            lowerLeft = block("pale_oak_log", Blocks.AIR),
            lowerRight = block("pale_oak_log", Blocks.AIR),
            bottomLeft = block("resin_block", Blocks.AIR),
            bottomInnerLeft = block("mossy_cobblestone", Blocks.AIR),
            bottomInnerRight = block("mossy_cobblestone", Blocks.AIR),
            bottomRight = block("resin_block", Blocks.AIR),
        )

    val CHERRY_GROVE =
        PortalDesign(
            topLeft = block("pink_terracotta"),
            topInnerLeft = block("cherry_leaves", Blocks.AIR),
            topInnerRight = block("cherry_leaves", Blocks.AIR),
            topRight = block("pink_terracotta"),
            upperLeft = block("cherry_log", Blocks.AIR),
            upperRight = block("cherry_log", Blocks.AIR),
            middleLeft = block("stripped_cherry_log", Blocks.AIR),
            middleRight = block("stripped_cherry_log", Blocks.AIR),
            lowerLeft = block("cherry_log", Blocks.AIR),
            lowerRight = block("cherry_log", Blocks.AIR),
            bottomLeft = block("pink_terracotta"),
            bottomInnerLeft = block("cherry_planks", Blocks.AIR),
            bottomInnerRight = block("cherry_planks", Blocks.AIR),
            bottomRight = block("pink_terracotta"),
        )

    val TAIGA =
        PortalDesign(
            topLeft = block("moss_block", Blocks.AIR),
            topInnerLeft = block("stripped_spruce_log", Blocks.AIR),
            topInnerRight = block("stripped_spruce_log", Blocks.AIR),
            topRight = block("moss_block", Blocks.AIR),
            upperLeft = block("spruce_planks", Blocks.AIR),
            upperRight = block("spruce_planks", Blocks.AIR),
            middleLeft = block("stripped_spruce_log", Blocks.AIR),
            middleRight = block("stripped_spruce_log", Blocks.AIR),
            lowerLeft = block("spruce_log", Blocks.AIR),
            lowerRight = block("spruce_log", Blocks.AIR),
            bottomLeft = block("tuff", Blocks.AIR),
            bottomInnerLeft = block("mossy_cobblestone", Blocks.AIR),
            bottomInnerRight = block("mossy_cobblestone", Blocks.AIR),
            bottomRight = block("tuff", Blocks.AIR),
        )

    val SNOWY_TAIGA =
        PortalDesign(
            topLeft = block("snow_block", Blocks.AIR),
            topInnerLeft = block("stripped_spruce_log", Blocks.AIR),
            topInnerRight = block("stripped_spruce_log", Blocks.AIR),
            topRight = block("snow_block", Blocks.AIR),
            upperLeft = block("spruce_planks", Blocks.AIR),
            upperRight = block("spruce_planks", Blocks.AIR),
            middleLeft = block("stripped_spruce_log", Blocks.AIR),
            middleRight = block("stripped_spruce_log", Blocks.AIR),
            lowerLeft = block("spruce_log", Blocks.AIR),
            lowerRight = block("spruce_log", Blocks.AIR),
            bottomLeft = block("snow_block", Blocks.AIR),
            bottomInnerLeft = block("spruce_log", Blocks.AIR),
            bottomInnerRight = block("spruce_log", Blocks.AIR),
            bottomRight = block("snow_block", Blocks.AIR),
        )

    val OLD_GROWTH_SPRUCE_TAIGA =
        PortalDesign(
            topLeft = block("podzol", Blocks.AIR),
            topInnerLeft = block("spruce_leaves", Blocks.AIR),
            topInnerRight = block("spruce_leaves", Blocks.AIR),
            topRight = block("podzol", Blocks.AIR),
            upperLeft = block("spruce_planks", Blocks.AIR),
            upperRight = block("spruce_planks", Blocks.AIR),
            middleLeft = block("stripped_spruce_log", Blocks.AIR),
            middleRight = block("stripped_spruce_log", Blocks.AIR),
            lowerLeft = block("spruce_log", Blocks.AIR),
            lowerRight = block("spruce_log", Blocks.AIR),
            bottomLeft = block("spruce_leaves", Blocks.AIR),
            bottomInnerLeft = block("mossy_cobblestone", Blocks.AIR),
            bottomInnerRight = block("mossy_cobblestone", Blocks.AIR),
            bottomRight = block("spruce_leaves", Blocks.AIR),
        )

    val OLD_GROWTH_PINE_TAIGA =
        PortalDesign(
            topLeft = block("podzol", Blocks.AIR),
            topInnerLeft = block("stripped_spruce_log", Blocks.AIR),
            topInnerRight = block("stripped_spruce_log", Blocks.AIR),
            topRight = block("podzol", Blocks.AIR),
            upperLeft = block("spruce_planks", Blocks.AIR),
            upperRight = block("spruce_planks", Blocks.AIR),
            middleLeft = block("stripped_spruce_log", Blocks.AIR),
            middleRight = block("stripped_spruce_log", Blocks.AIR),
            lowerLeft = block("spruce_log", Blocks.AIR),
            lowerRight = block("spruce_log", Blocks.AIR),
            bottomLeft = block("spruce_log", Blocks.AIR),
            bottomInnerLeft = block("mossy_cobblestone", Blocks.AIR),
            bottomInnerRight = block("mossy_cobblestone", Blocks.AIR),
            bottomRight = block("spruce_log", Blocks.AIR),
        )

    val MEADOW =
        PortalDesign(
            topLeft = block("poppy", Blocks.AIR),
            topInnerLeft = block("flowering_azalea_leaves", Blocks.AIR),
            topInnerRight = block("flowering_azalea_leaves", Blocks.AIR),
            topRight = block("poppy", Blocks.AIR),
            upperLeft = block("grass_block", Blocks.AIR),
            upperRight = block("grass_block", Blocks.AIR),
            middleLeft = block("stone", Blocks.AIR),
            middleRight = block("stone", Blocks.AIR),
            lowerLeft = block("moss_block", Blocks.AIR),
            lowerRight = block("moss_block", Blocks.AIR),
            bottomLeft = block("red_terracotta"),
            bottomInnerLeft = block("moss_block", Blocks.AIR),
            bottomInnerRight = block("moss_block", Blocks.AIR),
            bottomRight = block("red_terracotta"),
        )

    val GROVE =
        PortalDesign(
            topLeft = block("powder_snow", Blocks.AIR),
            topInnerLeft = block("spruce_leaves", Blocks.AIR),
            topInnerRight = block("spruce_leaves", Blocks.AIR),
            topRight = block("powder_snow", Blocks.AIR),
            upperLeft = block("spruce_planks", Blocks.AIR),
            upperRight = block("spruce_planks", Blocks.AIR),
            middleLeft = block("stripped_spruce_log", Blocks.AIR),
            middleRight = block("stripped_spruce_log", Blocks.AIR),
            lowerLeft = block("spruce_log", Blocks.AIR),
            lowerRight = block("spruce_log", Blocks.AIR),
            bottomLeft = block("powder_snow", Blocks.AIR),
            bottomInnerLeft = block("spruce_log", Blocks.AIR),
            bottomInnerRight = block("spruce_log", Blocks.AIR),
            bottomRight = block("powder_snow", Blocks.AIR),
        )

    val SNOWY_SLOPES =
        PortalDesign(
            topLeft = block("snow_block", Blocks.AIR),
            topInnerLeft = block("snow_block", Blocks.AIR),
            topInnerRight = block("snow_block", Blocks.AIR),
            topRight = block("snow_block", Blocks.AIR),
            upperLeft = block("packed_ice", Blocks.AIR),
            upperRight = block("packed_ice", Blocks.AIR),
            middleLeft = block("snow_block", Blocks.AIR),
            middleRight = block("snow_block", Blocks.AIR),
            lowerLeft = block("dirt", Blocks.AIR),
            lowerRight = block("dirt", Blocks.AIR),
            bottomLeft = block("blue_glazed_terracotta"),
            bottomInnerLeft = block("snow_block", Blocks.AIR),
            bottomInnerRight = block("snow_block", Blocks.AIR),
            bottomRight = block("blue_glazed_terracotta"),
        )

    val JAGGED_PEAKS =
        PortalDesign(
            topLeft = block("snow_block", Blocks.AIR),
            topInnerLeft = block("snow_block", Blocks.AIR),
            topInnerRight = block("snow_block", Blocks.AIR),
            topRight = block("snow_block", Blocks.AIR),
            upperLeft = block("packed_ice", Blocks.AIR),
            upperRight = block("packed_ice", Blocks.AIR),
            middleLeft = block("snow_block", Blocks.AIR),
            middleRight = block("snow_block", Blocks.AIR),
            lowerLeft = block("stone", Blocks.AIR),
            lowerRight = block("stone", Blocks.AIR),
            bottomLeft = block("calcite", Blocks.AIR),
            bottomInnerLeft = block("snow_block", Blocks.AIR),
            bottomInnerRight = block("snow_block", Blocks.AIR),
            bottomRight = block("calcite", Blocks.AIR),
        )

    val FROZEN_PEAKS =
        PortalDesign(
            topLeft = block("packed_ice", Blocks.AIR),
            topInnerLeft = block("packed_ice", Blocks.AIR),
            topInnerRight = block("packed_ice", Blocks.AIR),
            topRight = block("packed_ice", Blocks.AIR),
            upperLeft = block("snow_block", Blocks.AIR),
            upperRight = block("snow_block", Blocks.AIR),
            middleLeft = block("packed_ice", Blocks.AIR),
            middleRight = block("packed_ice", Blocks.AIR),
            lowerLeft = block("stone", Blocks.AIR),
            lowerRight = block("stone", Blocks.AIR),
            bottomLeft = block("calcite", Blocks.AIR),
            bottomInnerLeft = block("snow_block", Blocks.AIR),
            bottomInnerRight = block("snow_block", Blocks.AIR),
            bottomRight = block("calcite", Blocks.AIR),
        )

    val STONY_PEAKS =
        PortalDesign(
            topLeft = block("coal_ore", Blocks.AIR),
            topInnerLeft = block("stone", Blocks.AIR),
            topInnerRight = block("stone", Blocks.AIR),
            topRight = block("coal_ore", Blocks.AIR),
            upperLeft = block("stone", Blocks.AIR),
            upperRight = block("stone", Blocks.AIR),
            middleLeft = block("calcite", Blocks.AIR),
            middleRight = block("calcite", Blocks.AIR),
            lowerLeft = block("stone", Blocks.AIR),
            lowerRight = block("stone", Blocks.AIR),
            bottomLeft = block("coal_ore", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("coal_ore", Blocks.AIR),
        )

    val WINDSWEPT_HILLS =
        PortalDesign(
            topLeft = block("grass_block", Blocks.AIR),
            topInnerLeft = block("grass_block", Blocks.AIR),
            topInnerRight = block("grass_block", Blocks.AIR),
            topRight = block("grass_block", Blocks.AIR),
            upperLeft = block("stone", Blocks.AIR),
            upperRight = block("stone", Blocks.AIR),
            middleLeft = block("coal_ore", Blocks.AIR),
            middleRight = block("coal_ore", Blocks.AIR),
            lowerLeft = block("stone", Blocks.AIR),
            lowerRight = block("stone", Blocks.AIR),
            bottomLeft = block("dirt", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("dirt", Blocks.AIR),
        )

    val WINDSWEPT_GRAVELLY_HILLS =
        PortalDesign(
            topLeft = block("gravel", Blocks.AIR),
            topInnerLeft = block("grass_block", Blocks.AIR),
            topInnerRight = block("grass_block", Blocks.AIR),
            topRight = block("gravel", Blocks.AIR),
            upperLeft = block("stone", Blocks.AIR),
            upperRight = block("stone", Blocks.AIR),
            middleLeft = block("coal_ore", Blocks.AIR),
            middleRight = block("coal_ore", Blocks.AIR),
            lowerLeft = block("stone", Blocks.AIR),
            lowerRight = block("stone", Blocks.AIR),
            bottomLeft = block("gravel", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("gravel", Blocks.AIR),
        )

    val WINDSWEPT_FOREST =
        PortalDesign(
            topLeft = block("oak_log", Blocks.AIR),
            topInnerLeft = block("grass_block", Blocks.AIR),
            topInnerRight = block("grass_block", Blocks.AIR),
            topRight = block("oak_log", Blocks.AIR),
            upperLeft = block("stone", Blocks.AIR),
            upperRight = block("stone", Blocks.AIR),
            middleLeft = block("coal_ore", Blocks.AIR),
            middleRight = block("coal_ore", Blocks.AIR),
            lowerLeft = block("stone", Blocks.AIR),
            lowerRight = block("stone", Blocks.AIR),
            bottomLeft = block("dirt", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("dirt", Blocks.AIR),
        )

    val WINDSWEPT_SAVANNA =
        PortalDesign(
            topLeft = block("stripped_acacia_log", Blocks.AIR),
            topInnerLeft = block("grass_block", Blocks.AIR),
            topInnerRight = block("grass_block", Blocks.AIR),
            topRight = block("stripped_acacia_log", Blocks.AIR),
            upperLeft = block("stone", Blocks.AIR),
            upperRight = block("stone", Blocks.AIR),
            middleLeft = block("coal_ore", Blocks.AIR),
            middleRight = block("coal_ore", Blocks.AIR),
            lowerLeft = block("stone", Blocks.AIR),
            lowerRight = block("stone", Blocks.AIR),
            bottomLeft = block("dirt", Blocks.AIR),
            bottomInnerLeft = block("acacia_log", Blocks.AIR),
            bottomInnerRight = block("acacia_log", Blocks.AIR),
            bottomRight = block("dirt", Blocks.AIR),
        )

    val ICE_SPIKES =
        PortalDesign(
            topLeft = block("packed_ice", Blocks.AIR),
            topInnerLeft = block("snow_block", Blocks.AIR),
            topInnerRight = block("snow_block", Blocks.AIR),
            topRight = block("packed_ice", Blocks.AIR),
            upperLeft = block("packed_ice", Blocks.AIR),
            upperRight = block("packed_ice", Blocks.AIR),
            middleLeft = block("packed_ice", Blocks.AIR),
            middleRight = block("packed_ice", Blocks.AIR),
            lowerLeft = block("blue_ice", Blocks.AIR),
            lowerRight = block("blue_ice", Blocks.AIR),
            bottomLeft = block("blue_ice", Blocks.AIR),
            bottomInnerLeft = block("snow_block", Blocks.AIR),
            bottomInnerRight = block("snow_block", Blocks.AIR),
            bottomRight = block("blue_ice", Blocks.AIR),
        )

    val SNOWY_PLAINS =
        PortalDesign(
            topLeft = block("snow_block", Blocks.AIR),
            topInnerLeft = block("snow_block", Blocks.AIR),
            topInnerRight = block("snow_block", Blocks.AIR),
            topRight = block("snow_block", Blocks.AIR),
            upperLeft = block("snow_block", Blocks.AIR),
            upperRight = block("snow_block", Blocks.AIR),
            middleLeft = block("snow_block", Blocks.AIR),
            middleRight = block("snow_block", Blocks.AIR),
            lowerLeft = block("snow_block", Blocks.AIR),
            lowerRight = block("snow_block", Blocks.AIR),
            bottomLeft = block("snow_block", Blocks.AIR),
            bottomInnerLeft = block("snow_block", Blocks.AIR),
            bottomInnerRight = block("snow_block", Blocks.AIR),
            bottomRight = block("snow_block", Blocks.AIR),
        )

    val DESERT =
        PortalDesign(
            topLeft = block("sand", Blocks.AIR),
            topInnerLeft = block("cut_sandstone", Blocks.AIR),
            topInnerRight = block("cut_sandstone", Blocks.AIR),
            topRight = block("sand", Blocks.AIR),
            upperLeft = block("smooth_sandstone", Blocks.AIR),
            upperRight = block("smooth_sandstone", Blocks.AIR),
            middleLeft = block("cut_sandstone", Blocks.AIR),
            middleRight = block("cut_sandstone", Blocks.AIR),
            lowerLeft = block("cut_sandstone", Blocks.AIR),
            lowerRight = block("cut_sandstone", Blocks.AIR),
            bottomLeft = block("orange_terracotta"),
            bottomInnerLeft = block("sandstone", Blocks.AIR),
            bottomInnerRight = block("sandstone", Blocks.AIR),
            bottomRight = block("orange_terracotta"),
        )

    val SAVANNA =
        PortalDesign(
            topLeft = block("stripped_acacia_log", Blocks.AIR),
            topInnerLeft = block("acacia_leaves", Blocks.AIR),
            topInnerRight = block("acacia_leaves", Blocks.AIR),
            topRight = block("stripped_acacia_log", Blocks.AIR),
            upperLeft = block("acacia_planks", Blocks.AIR),
            upperRight = block("acacia_planks", Blocks.AIR),
            middleLeft = block("stripped_acacia_log", Blocks.AIR),
            middleRight = block("stripped_acacia_log", Blocks.AIR),
            lowerLeft = block("acacia_log", Blocks.AIR),
            lowerRight = block("acacia_log", Blocks.AIR),
            bottomLeft = block("brown_terracotta"),
            bottomInnerLeft = block("acacia_planks", Blocks.AIR),
            bottomInnerRight = block("acacia_planks", Blocks.AIR),
            bottomRight = block("brown_terracotta"),
        )

    val SAVANNA_PLATEAU =
        PortalDesign(
            topLeft = block("stripped_acacia_log", Blocks.AIR),
            topInnerLeft = block("acacia_leaves", Blocks.AIR),
            topInnerRight = block("acacia_leaves", Blocks.AIR),
            topRight = block("stripped_acacia_log", Blocks.AIR),
            upperLeft = block("acacia_planks", Blocks.AIR),
            upperRight = block("acacia_planks", Blocks.AIR),
            middleLeft = block("coarse_dirt", Blocks.AIR),
            middleRight = block("coarse_dirt", Blocks.AIR),
            lowerLeft = block("acacia_log", Blocks.AIR),
            lowerRight = block("acacia_log", Blocks.AIR),
            bottomLeft = block("stone", Blocks.AIR),
            bottomInnerLeft = block("coarse_dirt", Blocks.AIR),
            bottomInnerRight = block("coarse_dirt", Blocks.AIR),
            bottomRight = block("stone", Blocks.AIR),
        )

    val BADLANDS =
        PortalDesign(
            topLeft = block("red_sand", Blocks.AIR),
            topInnerLeft = block("cut_red_sandstone", Blocks.AIR),
            topInnerRight = block("cut_red_sandstone", Blocks.AIR),
            topRight = block("red_sand", Blocks.AIR),
            upperLeft = block("white_terracotta"),
            upperRight = block("white_terracotta"),
            middleLeft = block("terracotta", Blocks.AIR),
            middleRight = block("terracotta", Blocks.AIR),
            lowerLeft = block("brown_terracotta"),
            lowerRight = block("brown_terracotta"),
            bottomLeft = block("orange_terracotta"),
            bottomInnerLeft = block("cut_red_sandstone", Blocks.AIR),
            bottomInnerRight = block("cut_red_sandstone", Blocks.AIR),
            bottomRight = block("orange_terracotta"),
        )

    val WOODED_BADLANDS =
        PortalDesign(
            topLeft = block("oak_log", Blocks.AIR),
            topInnerLeft = block("coarse_dirt", Blocks.AIR),
            topInnerRight = block("coarse_dirt", Blocks.AIR),
            topRight = block("oak_log", Blocks.AIR),
            upperLeft = block("orange_terracotta"),
            upperRight = block("orange_terracotta"),
            middleLeft = block("terracotta", Blocks.AIR),
            middleRight = block("terracotta", Blocks.AIR),
            lowerLeft = block("oak_log", Blocks.AIR),
            lowerRight = block("oak_log", Blocks.AIR),
            bottomLeft = block("orange_terracotta"),
            bottomInnerLeft = block("cut_red_sandstone", Blocks.AIR),
            bottomInnerRight = block("cut_red_sandstone", Blocks.AIR),
            bottomRight = block("orange_terracotta"),
        )

    val ERODED_BADLANDS =
        PortalDesign(
            topLeft = block("dead_bush", Blocks.AIR),
            topInnerLeft = block("cut_red_sandstone", Blocks.AIR),
            topInnerRight = block("cut_red_sandstone", Blocks.AIR),
            topRight = block("dead_bush", Blocks.AIR),
            upperLeft = block("red_sand", Blocks.AIR),
            upperRight = block("red_sand", Blocks.AIR),
            middleLeft = block("yellow_terracotta"),
            middleRight = block("yellow_terracotta"),
            lowerLeft = block("cut_red_sandstone", Blocks.AIR),
            lowerRight = block("cut_red_sandstone", Blocks.AIR),
            bottomLeft = block("orange_terracotta"),
            bottomInnerLeft = block("cut_red_sandstone", Blocks.AIR),
            bottomInnerRight = block("cut_red_sandstone", Blocks.AIR),
            bottomRight = block("orange_terracotta"),
        )

    val JUNGLE =
        PortalDesign(
            topLeft = block("stripped_jungle_log", Blocks.AIR),
            topInnerLeft = block("jungle_leaves", Blocks.AIR),
            topInnerRight = block("jungle_leaves", Blocks.AIR),
            topRight = block("stripped_jungle_log", Blocks.AIR),
            upperLeft = block("jungle_planks", Blocks.AIR),
            upperRight = block("jungle_planks", Blocks.AIR),
            middleLeft = block("jungle_log", Blocks.AIR),
            middleRight = block("jungle_log", Blocks.AIR),
            lowerLeft = block("jungle_planks", Blocks.AIR),
            lowerRight = block("jungle_planks", Blocks.AIR),
            bottomLeft = block("jungle_leaves", Blocks.AIR),
            bottomInnerLeft = block("jungle_planks", Blocks.AIR),
            bottomInnerRight = block("jungle_planks", Blocks.AIR),
            bottomRight = block("jungle_leaves", Blocks.AIR),
        )

    val SPARSE_JUNGLE =
        PortalDesign(
            topLeft = block("stripped_jungle_log", Blocks.AIR),
            topInnerLeft = block("jungle_leaves", Blocks.AIR),
            topInnerRight = block("jungle_leaves", Blocks.AIR),
            topRight = block("stripped_jungle_log", Blocks.AIR),
            upperLeft = block("jungle_planks", Blocks.AIR),
            upperRight = block("jungle_planks", Blocks.AIR),
            middleLeft = block("jungle_log", Blocks.AIR),
            middleRight = block("jungle_log", Blocks.AIR),
            lowerLeft = block("mossy_cobblestone", Blocks.AIR),
            lowerRight = block("mossy_cobblestone", Blocks.AIR),
            bottomLeft = block("moss_block", Blocks.AIR),
            bottomInnerLeft = block("jungle_planks", Blocks.AIR),
            bottomInnerRight = block("jungle_planks", Blocks.AIR),
            bottomRight = block("moss_block", Blocks.AIR),
        )

    val BAMBOO_JUNGLE =
        PortalDesign(
            topLeft = block("bamboo_block", Blocks.AIR),
            topInnerLeft = block("bamboo_mosaic", Blocks.AIR),
            topInnerRight = block("bamboo_mosaic", Blocks.AIR),
            topRight = block("bamboo_block", Blocks.AIR),
            upperLeft = block("bamboo_block", Blocks.AIR),
            upperRight = block("bamboo_block", Blocks.AIR),
            middleLeft = block("bamboo_block", Blocks.AIR),
            middleRight = block("bamboo_block", Blocks.AIR),
            lowerLeft = block("bamboo_block", Blocks.AIR),
            lowerRight = block("bamboo_block", Blocks.AIR),
            bottomLeft = block("bamboo_block", Blocks.AIR),
            bottomInnerLeft = block("bamboo_mosaic", Blocks.AIR),
            bottomInnerRight = block("bamboo_mosaic", Blocks.AIR),
            bottomRight = block("bamboo_block", Blocks.AIR),
        )

    val SWAMP =
        PortalDesign(
            topLeft = block("firefly_bush", Blocks.AIR),
            topInnerLeft = block("moss_block", Blocks.AIR),
            topInnerRight = block("moss_block", Blocks.AIR),
            topRight = block("firefly_bush", Blocks.AIR),
            upperLeft = block("grass_block", Blocks.AIR),
            upperRight = block("grass_block", Blocks.AIR),
            middleLeft = block("dirt", Blocks.AIR),
            middleRight = block("dirt", Blocks.AIR),
            lowerLeft = block("mossy_cobblestone", Blocks.AIR),
            lowerRight = block("mossy_cobblestone", Blocks.AIR),
            bottomLeft = block("green_terracotta"),
            bottomInnerLeft = block("mossy_cobblestone", Blocks.AIR),
            bottomInnerRight = block("mossy_cobblestone", Blocks.AIR),
            bottomRight = block("green_terracotta"),
        )

    val MANGROVE_SWAMP =
        PortalDesign(
            topLeft = block("mangrove_roots", Blocks.AIR),
            topInnerLeft = block("mangrove_leaves", Blocks.AIR),
            topInnerRight = block("mangrove_leaves", Blocks.AIR),
            topRight = block("mangrove_roots", Blocks.AIR),
            upperLeft = block("stripped_mangrove_log", Blocks.AIR),
            upperRight = block("stripped_mangrove_log", Blocks.AIR),
            middleLeft = block("mangrove_planks", Blocks.AIR),
            middleRight = block("mangrove_planks", Blocks.AIR),
            lowerLeft = block("mangrove_log", Blocks.AIR),
            lowerRight = block("mangrove_log", Blocks.AIR),
            bottomLeft = block("muddy_mangrove_roots", Blocks.AIR),
            bottomInnerLeft = block("mud", Blocks.AIR),
            bottomInnerRight = block("mud", Blocks.AIR),
            bottomRight = block("muddy_mangrove_roots", Blocks.AIR),
        )

    val RIVER =
        PortalDesign(
            topLeft = block("sand", Blocks.AIR),
            topInnerLeft = block("clay", Blocks.AIR),
            topInnerRight = block("clay", Blocks.AIR),
            topRight = block("sand", Blocks.AIR),
            upperLeft = block("clay", Blocks.AIR),
            upperRight = block("clay", Blocks.AIR),
            middleLeft = block("sand", Blocks.AIR),
            middleRight = block("sand", Blocks.AIR),
            lowerLeft = block("clay", Blocks.AIR),
            lowerRight = block("clay", Blocks.AIR),
            bottomLeft = block("stone", Blocks.AIR),
            bottomInnerLeft = block("clay", Blocks.AIR),
            bottomInnerRight = block("clay", Blocks.AIR),
            bottomRight = block("stone", Blocks.AIR),
        )

    val FROZEN_RIVER =
        PortalDesign(
            topLeft = block("sand", Blocks.AIR),
            topInnerLeft = block("packed_ice", Blocks.AIR),
            topInnerRight = block("packed_ice", Blocks.AIR),
            topRight = block("sand", Blocks.AIR),
            upperLeft = block("clay", Blocks.AIR),
            upperRight = block("clay", Blocks.AIR),
            middleLeft = block("sand", Blocks.AIR),
            middleRight = block("sand", Blocks.AIR),
            lowerLeft = block("clay", Blocks.AIR),
            lowerRight = block("clay", Blocks.AIR),
            bottomLeft = block("stone", Blocks.AIR),
            bottomInnerLeft = block("clay", Blocks.AIR),
            bottomInnerRight = block("clay", Blocks.AIR),
            bottomRight = block("stone", Blocks.AIR),
        )

    val BEACH =
        PortalDesign(
            topLeft = block("sand", Blocks.AIR),
            topInnerLeft = block("sandstone", Blocks.AIR),
            topInnerRight = block("sandstone", Blocks.AIR),
            topRight = block("sand", Blocks.AIR),
            upperLeft = block("sand", Blocks.AIR),
            upperRight = block("sand", Blocks.AIR),
            middleLeft = block("dirt", Blocks.AIR),
            middleRight = block("dirt", Blocks.AIR),
            lowerLeft = block("sand", Blocks.AIR),
            lowerRight = block("sand", Blocks.AIR),
            bottomLeft = block("stone", Blocks.AIR),
            bottomInnerLeft = block("sand", Blocks.AIR),
            bottomInnerRight = block("sand", Blocks.AIR),
            bottomRight = block("stone", Blocks.AIR),
        )

    val SNOWY_BEACH =
        PortalDesign(
            topLeft = block("snow_block", Blocks.AIR),
            topInnerLeft = block("sandstone", Blocks.AIR),
            topInnerRight = block("sandstone", Blocks.AIR),
            topRight = block("snow_block", Blocks.AIR),
            upperLeft = block("sand", Blocks.AIR),
            upperRight = block("sand", Blocks.AIR),
            middleLeft = block("dirt", Blocks.AIR),
            middleRight = block("dirt", Blocks.AIR),
            lowerLeft = block("sand", Blocks.AIR),
            lowerRight = block("sand", Blocks.AIR),
            bottomLeft = block("stone", Blocks.AIR),
            bottomInnerLeft = block("snow_block", Blocks.AIR),
            bottomInnerRight = block("snow_block", Blocks.AIR),
            bottomRight = block("stone", Blocks.AIR),
        )

    val STONY_SHORE =
        PortalDesign(
            topLeft = block("stone", Blocks.AIR),
            topInnerLeft = block("sandstone", Blocks.AIR),
            topInnerRight = block("sandstone", Blocks.AIR),
            topRight = block("stone", Blocks.AIR),
            upperLeft = block("sand", Blocks.AIR),
            upperRight = block("sand", Blocks.AIR),
            middleLeft = block("stone", Blocks.AIR),
            middleRight = block("stone", Blocks.AIR),
            lowerLeft = block("sand", Blocks.AIR),
            lowerRight = block("sand", Blocks.AIR),
            bottomLeft = block("stone", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("stone", Blocks.AIR),
        )

    val OCEAN =
        PortalDesign(
            topLeft = block("sea_lantern", Blocks.AIR),
            topInnerLeft = block("sandstone", Blocks.AIR),
            topInnerRight = block("sandstone", Blocks.AIR),
            topRight = block("sea_lantern", Blocks.AIR),
            upperLeft = block("sand", Blocks.AIR),
            upperRight = block("sand", Blocks.AIR),
            middleLeft = block("stone", Blocks.AIR),
            middleRight = block("stone", Blocks.AIR),
            lowerLeft = block("prismarine", Blocks.AIR),
            lowerRight = block("prismarine", Blocks.AIR),
            bottomLeft = block("dried_kelp_block", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("dried_kelp_block", Blocks.AIR),
        )

    val WARM_OCEAN =
        PortalDesign(
            topLeft = block("sea_lantern", Blocks.AIR),
            topInnerLeft = block("sandstone", Blocks.AIR),
            topInnerRight = block("sandstone", Blocks.AIR),
            topRight = block("sea_lantern", Blocks.AIR),
            upperLeft = block("sand", Blocks.AIR),
            upperRight = block("sand", Blocks.AIR),
            middleLeft = block("stone", Blocks.AIR),
            middleRight = block("stone", Blocks.AIR),
            lowerLeft = block("prismarine_bricks", Blocks.AIR),
            lowerRight = block("prismarine_bricks", Blocks.AIR),
            bottomLeft = block("prismarine", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("prismarine", Blocks.AIR),
        )

    val LUKEWARM_OCEAN =
        PortalDesign(
            topLeft = block("sea_lantern", Blocks.AIR),
            topInnerLeft = block("sandstone", Blocks.AIR),
            topInnerRight = block("sandstone", Blocks.AIR),
            topRight = block("sea_lantern", Blocks.AIR),
            upperLeft = block("sand", Blocks.AIR),
            upperRight = block("sand", Blocks.AIR),
            middleLeft = block("stone", Blocks.AIR),
            middleRight = block("stone", Blocks.AIR),
            lowerLeft = block("prismarine_bricks", Blocks.AIR),
            lowerRight = block("prismarine_bricks", Blocks.AIR),
            bottomLeft = block("sand", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("sand", Blocks.AIR),
        )

    val DEEP_OCEAN =
        PortalDesign(
            topLeft = block("sea_lantern", Blocks.AIR),
            topInnerLeft = block("sandstone", Blocks.AIR),
            topInnerRight = block("sandstone", Blocks.AIR),
            topRight = block("sea_lantern", Blocks.AIR),
            upperLeft = block("sand", Blocks.AIR),
            upperRight = block("sand", Blocks.AIR),
            middleLeft = block("stone", Blocks.AIR),
            middleRight = block("stone", Blocks.AIR),
            lowerLeft = block("prismarine_bricks", Blocks.AIR),
            lowerRight = block("prismarine_bricks", Blocks.AIR),
            bottomLeft = block("dark_prismarine", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("dark_prismarine", Blocks.AIR),
        )

    val DEEP_LUKEWARM_OCEAN =
        PortalDesign(
            topLeft = block("sea_lantern", Blocks.AIR),
            topInnerLeft = block("sandstone", Blocks.AIR),
            topInnerRight = block("sandstone", Blocks.AIR),
            topRight = block("sea_lantern", Blocks.AIR),
            upperLeft = block("sand", Blocks.AIR),
            upperRight = block("sand", Blocks.AIR),
            middleLeft = block("stone", Blocks.AIR),
            middleRight = block("stone", Blocks.AIR),
            lowerLeft = block("dark_prismarine", Blocks.AIR),
            lowerRight = block("dark_prismarine", Blocks.AIR),
            bottomLeft = block("sand", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("sand", Blocks.AIR),
        )

    val COLD_OCEAN =
        PortalDesign(
            topLeft = block("sea_lantern", Blocks.AIR),
            topInnerLeft = block("packed_ice", Blocks.AIR),
            topInnerRight = block("packed_ice", Blocks.AIR),
            topRight = block("sea_lantern", Blocks.AIR),
            upperLeft = block("sand", Blocks.AIR),
            upperRight = block("sand", Blocks.AIR),
            middleLeft = block("stone", Blocks.AIR),
            middleRight = block("stone", Blocks.AIR),
            lowerLeft = block("prismarine_bricks", Blocks.AIR),
            lowerRight = block("prismarine_bricks", Blocks.AIR),
            bottomLeft = block("prismarine", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("prismarine", Blocks.AIR),
        )

    val DEEP_COLD_OCEAN =
        PortalDesign(
            topLeft = block("sea_lantern", Blocks.AIR),
            topInnerLeft = block("packed_ice", Blocks.AIR),
            topInnerRight = block("packed_ice", Blocks.AIR),
            topRight = block("sea_lantern", Blocks.AIR),
            upperLeft = block("sand", Blocks.AIR),
            upperRight = block("sand", Blocks.AIR),
            middleLeft = block("stone", Blocks.AIR),
            middleRight = block("stone", Blocks.AIR),
            lowerLeft = block("prismarine_bricks", Blocks.AIR),
            lowerRight = block("prismarine_bricks", Blocks.AIR),
            bottomLeft = block("dark_prismarine", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("dark_prismarine", Blocks.AIR),
        )

    val FROZEN_OCEAN =
        PortalDesign(
            topLeft = block("sea_lantern", Blocks.AIR),
            topInnerLeft = block("packed_ice", Blocks.AIR),
            topInnerRight = block("packed_ice", Blocks.AIR),
            topRight = block("sea_lantern", Blocks.AIR),
            upperLeft = block("prismarine_bricks", Blocks.AIR),
            upperRight = block("prismarine_bricks", Blocks.AIR),
            middleLeft = block("blue_ice", Blocks.AIR),
            middleRight = block("blue_ice", Blocks.AIR),
            lowerLeft = block("prismarine_bricks", Blocks.AIR),
            lowerRight = block("prismarine_bricks", Blocks.AIR),
            bottomLeft = block("prismarine", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("prismarine", Blocks.AIR),
        )

    val DEEP_FROZEN_OCEAN =
        PortalDesign(
            topLeft = block("sea_lantern", Blocks.AIR),
            topInnerLeft = block("packed_ice", Blocks.AIR),
            topInnerRight = block("packed_ice", Blocks.AIR),
            topRight = block("sea_lantern", Blocks.AIR),
            upperLeft = block("prismarine_bricks", Blocks.AIR),
            upperRight = block("prismarine_bricks", Blocks.AIR),
            middleLeft = block("blue_ice", Blocks.AIR),
            middleRight = block("blue_ice", Blocks.AIR),
            lowerLeft = block("dark_prismarine", Blocks.AIR),
            lowerRight = block("dark_prismarine", Blocks.AIR),
            bottomLeft = block("dark_prismarine", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("dark_prismarine", Blocks.AIR),
        )

    val MUSHROOM_FIELDS =
        PortalDesign(
            topLeft = block("mycelium", Blocks.AIR),
            topInnerLeft = block("mycelium", Blocks.AIR),
            topInnerRight = block("mycelium", Blocks.AIR),
            topRight = block("mycelium", Blocks.AIR),
            upperLeft = block("dirt", Blocks.AIR),
            upperRight = block("dirt", Blocks.AIR),
            middleLeft = block("brown_mushroom_block", Blocks.AIR),
            middleRight = block("brown_mushroom_block", Blocks.AIR),
            lowerLeft = block("red_mushroom_block", Blocks.AIR),
            lowerRight = block("red_mushroom_block", Blocks.AIR),
            bottomLeft = block("brown_mushroom_block", Blocks.AIR),
            bottomInnerLeft = block("mycelium", Blocks.AIR),
            bottomInnerRight = block("mycelium", Blocks.AIR),
            bottomRight = block("brown_mushroom_block", Blocks.AIR),
        )

    val LUSH_CAVES =
        PortalDesign(
            topLeft = block("moss_block", Blocks.AIR),
            topInnerLeft = block("flowering_azalea_leaves", Blocks.AIR),
            topInnerRight = block("flowering_azalea_leaves", Blocks.AIR),
            topRight = block("moss_block", Blocks.AIR),
            upperLeft = block("moss_block", Blocks.AIR),
            upperRight = block("moss_block", Blocks.AIR),
            middleLeft = block("moss_block", Blocks.AIR),
            middleRight = block("moss_block", Blocks.AIR),
            lowerLeft = block("clay", Blocks.AIR),
            lowerRight = block("clay", Blocks.AIR),
            bottomLeft = block("clay", Blocks.AIR),
            bottomInnerLeft = block("stone", Blocks.AIR),
            bottomInnerRight = block("stone", Blocks.AIR),
            bottomRight = block("clay", Blocks.AIR),
        )

    val DRIPSTONE_CAVES =
        PortalDesign(
            topLeft = block("dripstone_block", Blocks.AIR),
            topInnerLeft = block("copper_ore", Blocks.AIR),
            topInnerRight = block("copper_ore", Blocks.AIR),
            topRight = block("dripstone_block", Blocks.AIR),
            upperLeft = block("dripstone_block", Blocks.AIR),
            upperRight = block("dripstone_block", Blocks.AIR),
            middleLeft = block("dripstone_block", Blocks.AIR),
            middleRight = block("dripstone_block", Blocks.AIR),
            lowerLeft = block("dripstone_block", Blocks.AIR),
            lowerRight = block("dripstone_block", Blocks.AIR),
            bottomLeft = block("dripstone_block", Blocks.AIR),
            bottomInnerLeft = block("copper_ore", Blocks.AIR),
            bottomInnerRight = block("copper_ore", Blocks.AIR),
            bottomRight = block("dripstone_block", Blocks.AIR),
        )

    val DEEP_DARK =
        PortalDesign(
            topLeft = block("sculk", Blocks.AIR),
            topInnerLeft = block("sculk", Blocks.AIR),
            topInnerRight = block("sculk", Blocks.AIR),
            topRight = block("sculk", Blocks.AIR),
            upperLeft = block("sculk", Blocks.AIR),
            upperRight = block("sculk", Blocks.AIR),
            middleLeft = block("deepslate_bricks", Blocks.AIR),
            middleRight = block("deepslate_bricks", Blocks.AIR),
            lowerLeft = block("deepslate_bricks", Blocks.AIR),
            lowerRight = block("deepslate_bricks", Blocks.AIR),
            bottomLeft = block("polished_deepslate", Blocks.AIR),
            bottomInnerLeft = block("sculk_catalyst", Blocks.AIR),
            bottomInnerRight = block("sculk_catalyst", Blocks.AIR),
            bottomRight = block("polished_deepslate", Blocks.AIR),
        )

    val SULFUR_CAVES =
        PortalDesign(
            topLeft = block("polished_cinnabar", Blocks.AIR),
            topInnerLeft = block("chiseled_sulfur", Blocks.AIR),
            topInnerRight = block("chiseled_sulfur", Blocks.AIR),
            topRight = block("polished_cinnabar", Blocks.AIR),
            upperLeft = block("chiseled_cinnabar", Blocks.AIR),
            upperRight = block("chiseled_cinnabar", Blocks.AIR),
            middleLeft = block("cinnabar", Blocks.AIR),
            middleRight = block("cinnabar", Blocks.AIR),
            lowerLeft = block("cinnabar_bricks", Blocks.AIR),
            lowerRight = block("cinnabar_bricks", Blocks.AIR),
            bottomLeft = block("polished_cinnabar", Blocks.AIR),
            bottomInnerLeft = block("polished_sulfur", Blocks.AIR),
            bottomInnerRight = block("polished_sulfur", Blocks.AIR),
            bottomRight = block("polished_cinnabar", Blocks.AIR),
        )
}
