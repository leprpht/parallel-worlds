package com.leprpht.parallelworlds.compat

/**
 * Biomes known to the shared portal catalog, grouped by the first supported release that added
 * them. Version lines select a snapshot from this catalog instead of defining their own copies.
 */
object BiomeCatalog {
    val BASE_1_16_1 =
        setOf(
            "plains",
            "sunflower_plains",
            "forest",
            "flower_forest",
            "birch_forest",
            "dark_forest",
            "taiga",
            "snowy_taiga",
            "giant_tree_taiga",
            "giant_spruce_taiga",
            "tall_birch_forest",
            "ice_spikes",
            "snowy_tundra",
            "desert",
            "savanna",
            "savanna_plateau",
            "badlands",
            "wooded_badlands_plateau",
            "eroded_badlands",
            "jungle",
            "bamboo_jungle",
            "swamp",
            "river",
            "frozen_river",
            "beach",
            "snowy_beach",
            "stone_shore",
            "ocean",
            "warm_ocean",
            "lukewarm_ocean",
            "deep_warm_ocean",
            "deep_ocean",
            "deep_lukewarm_ocean",
            "cold_ocean",
            "deep_cold_ocean",
            "frozen_ocean",
            "deep_frozen_ocean",
            "mushroom_fields",
            "mountains",
            "gravelly_mountains",
            "wooded_mountains",
            "shattered_savanna",
            "shattered_savanna_plateau",
        )

    private val ADDITIONS =
        listOf(
            MinecraftVersion(1, 17) to setOf("lush_caves", "dripstone_caves"),
            MinecraftVersion(1, 18) to
                setOf(
                    "meadow",
                    "grove",
                    "snowy_slopes",
                    "jagged_peaks",
                    "frozen_peaks",
                    "stony_peaks",
                    "windswept_hills",
                    "windswept_gravelly_hills",
                    "windswept_forest",
                    "windswept_savanna",
                    "old_growth_birch_forest",
                    "old_growth_spruce_taiga",
                    "old_growth_pine_taiga",
                    "snowy_plains",
                    "sparse_jungle",
                ),
            MinecraftVersion(1, 19) to setOf("mangrove_swamp", "deep_dark"),
            MinecraftVersion(1, 20) to setOf("cherry_grove"),
            MinecraftVersion(1, 21, 4) to setOf("pale_garden"),
            MinecraftVersion(26, 2) to setOf("sulfur_caves"),
            MinecraftVersion(26, 3) to setOf("dappled_forest"),
        )

    fun availableAt(version: MinecraftVersion): Set<String> =
        (BASE_1_16_1 +
                ADDITIONS.filter { (introduced, _) -> introduced <= version }.flatMap { it.second })
            .let { available ->
                if (version >= MinecraftVersion(1, 18)) {
                    available - RENAMED_IN_1_18
                } else {
                    available
                }
            }

    private val RENAMED_IN_1_18 =
        setOf(
            "snowy_tundra",
            "stone_shore",
            "wooded_badlands_plateau",
            "mountains",
            "gravelly_mountains",
            "wooded_mountains",
            "tall_birch_forest",
            "giant_tree_taiga",
            "giant_spruce_taiga",
            "shattered_savanna",
            "shattered_savanna_plateau",
            "deep_warm_ocean",
        )
}
