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
            "ice_spikes",
            "snowy_plains",
            "desert",
            "savanna",
            "savanna_plateau",
            "badlands",
            "wooded_badlands",
            "eroded_badlands",
            "jungle",
            "sparse_jungle",
            "bamboo_jungle",
            "swamp",
            "river",
            "frozen_river",
            "beach",
            "snowy_beach",
            "stony_shore",
            "ocean",
            "warm_ocean",
            "lukewarm_ocean",
            "deep_ocean",
            "deep_lukewarm_ocean",
            "cold_ocean",
            "deep_cold_ocean",
            "frozen_ocean",
            "deep_frozen_ocean",
            "mushroom_fields",
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
                ),
            MinecraftVersion(1, 19) to setOf("mangrove_swamp", "deep_dark"),
            MinecraftVersion(1, 20) to setOf("cherry_grove"),
            MinecraftVersion(1, 21, 4) to setOf("pale_garden"),
            MinecraftVersion(26, 2) to setOf("sulfur_caves"),
            MinecraftVersion(26, 3) to setOf("dappled_forest"),
        )

    fun availableAt(version: MinecraftVersion): Set<String> =
        BASE_1_16_1 +
            ADDITIONS.filter { (introduced, _) -> introduced <= version }.flatMap { it.second }
}
