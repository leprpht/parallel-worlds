package com.leprpht.parallelworlds.compat

/**
 * Registry names used by portal frames, grouped by the release that introduced them. The shared
 * design file can therefore mention later blocks without making an older adapter compile against a
 * missing `Blocks` field.
 */
object BlockCatalog {
    private val ADDITIONS =
        mapOf(
            MinecraftVersion(1, 17) to
                setOf(
                    "calcite",
                    "copper_ore",
                    "deepslate_bricks",
                    "dripstone_block",
                    "flowering_azalea_leaves",
                    "moss_block",
                    "polished_deepslate",
                    "powder_snow",
                    "tuff",
                ),
            MinecraftVersion(1, 19) to
                setOf(
                    "mangrove_leaves",
                    "mangrove_log",
                    "mangrove_planks",
                    "mangrove_roots",
                    "mud",
                    "muddy_mangrove_roots",
                    "sculk",
                    "sculk_catalyst",
                ),
            MinecraftVersion(1, 20) to
                setOf(
                    "bamboo_block",
                    "bamboo_mosaic",
                    "cherry_leaves",
                    "cherry_log",
                    "cherry_planks",
                ),
            MinecraftVersion(1, 21, 4) to
                setOf(
                    "pale_oak_leaves",
                    "pale_oak_log",
                    "pale_oak_planks",
                    "resin_block",
                ),
            MinecraftVersion(26, 1, 2) to setOf("firefly_bush"),
            MinecraftVersion(26, 2) to
                setOf(
                    "cinnabar",
                    "cinnabar_bricks",
                    "polished_cinnabar",
                    "polished_sulfur",
                    "sulfur",
                ),
            MinecraftVersion(26, 3) to
                setOf(
                    "chiseled_cinnabar",
                    "chiseled_sulfur",
                    "orange_poplar_leaves",
                    "poplar_log",
                    "poplar_planks",
                    "red_poplar_leaves",
                    "yellow_poplar_leaves",
                ),
        )

    fun availableAt(version: MinecraftVersion): Set<String> =
        ADDITIONS.filter { (introduced, _) -> introduced <= version }.values.flatten().toSet()

    fun fallbackFor(id: String): String =
        when (id) {
            "flowering_azalea_leaves",
            "cherry_leaves",
            "mangrove_leaves",
            "pale_oak_leaves",
            "orange_poplar_leaves",
            "red_poplar_leaves",
            "yellow_poplar_leaves" -> "oak_leaves"
            "cherry_log",
            "mangrove_log",
            "pale_oak_log",
            "poplar_log" -> "oak_log"
            "cherry_planks",
            "mangrove_planks",
            "pale_oak_planks",
            "poplar_planks" -> "oak_planks"
            "bamboo_block",
            "bamboo_mosaic" -> "jungle_planks"
            "mangrove_roots",
            "muddy_mangrove_roots",
            "mud" -> "dirt"
            "powder_snow" -> "snow_block"
            "calcite",
            "copper_ore",
            "deepslate_bricks",
            "dripstone_block",
            "polished_deepslate",
            "sculk",
            "sculk_catalyst",
            "tuff",
            "cinnabar",
            "cinnabar_bricks",
            "chiseled_cinnabar",
            "chiseled_sulfur",
            "polished_cinnabar",
            "resin_block",
            "sulfur" -> "stone"
            "firefly_bush" -> "dead_bush"
            else -> "air"
        }
}
