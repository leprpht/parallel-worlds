package com.leprpht.parallelworlds.compat

/**
 * Comparable Minecraft release identifier. Calendar-versioned releases sort after the 1.x releases,
 * so the catalog can be used without Minecraft APIs.
 */
data class MinecraftVersion(val major: Int, val minor: Int, val patch: Int = 0) :
    Comparable<MinecraftVersion> {
    override fun compareTo(other: MinecraftVersion): Int =
        compareValuesBy(
            this,
            other,
            MinecraftVersion::major,
            MinecraftVersion::minor,
            MinecraftVersion::patch,
        )

    override fun toString(): String = if (patch == 0) "$major.$minor" else "$major.$minor.$patch"

    companion object {
        fun parse(value: String): MinecraftVersion {
            val parts = value.split('.')
            require(parts.size in 2..3) { "Invalid Minecraft version: $value" }
            return MinecraftVersion(
                parts[0].toInt(),
                parts[1].toInt(),
                parts.getOrNull(2)?.toInt() ?: 0,
            )
        }
    }
}

data class VersionLine(
    val id: String,
    val minecraft: MinecraftVersion,
    val fabricApi: String,
    val loader: String,
    val java: Int,
    val dimensionsAdded: Set<String>,
    val strategyChanges: Set<String> = emptySet(),
)

object SupportedVersions {
    private val CLASSIC_BIOMES =
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
    private val CAVES = setOf("lush_caves", "dripstone_caves")
    private val CAVES_118 =
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
        )
    private val MANGROVE_AND_DEEP_DARK = setOf("mangrove_swamp", "deep_dark")
    private val PALE_GARDEN = setOf("pale_garden")
    private val SULFUR_CAVES = setOf("sulfur_caves")
    private val DAPPLED_FOREST = setOf("dappled_forest")

    /** One build per behavior/API boundary, not one build per patch release. */
    val lines =
        listOf(
            VersionLine(
                "1.16.1",
                MinecraftVersion(1, 16, 1),
                "0.16.2+build.385-1.16.1",
                "0.11.7",
                8,
                CLASSIC_BIOMES,
            ),
            VersionLine(
                "1.16.2",
                MinecraftVersion(1, 16, 2),
                "0.20.2+build.402-1.16",
                "0.11.7",
                8,
                CLASSIC_BIOMES,
                setOf("biome_api_refactor"),
            ),
            VersionLine(
                "1.17.1",
                MinecraftVersion(1, 17, 1),
                "0.46.1+1.17",
                "0.12.12",
                16,
                CLASSIC_BIOMES + CAVES,
            ),
            VersionLine(
                "1.18.2",
                MinecraftVersion(1, 18, 2),
                "0.77.0+1.18",
                "0.14.9",
                17,
                CLASSIC_BIOMES + CAVES + CAVES_118,
                setOf("renamed_mountain_biomes"),
            ),
            VersionLine(
                "1.19.4",
                MinecraftVersion(1, 19, 4),
                "0.87.2+1.19.4",
                "0.14.19",
                17,
                CLASSIC_BIOMES + CAVES + CAVES_118 + MANGROVE_AND_DEEP_DARK,
            ),
            VersionLine(
                "1.20.1",
                MinecraftVersion(1, 20, 1),
                "0.92.2+1.20.1",
                "0.14.22",
                17,
                CLASSIC_BIOMES + CAVES + CAVES_118 + MANGROVE_AND_DEEP_DARK,
            ),
            VersionLine(
                "1.20.6",
                MinecraftVersion(1, 20, 6),
                "0.100.8+1.20.6",
                "0.15.11",
                21,
                CLASSIC_BIOMES + CAVES + CAVES_118 + MANGROVE_AND_DEEP_DARK,
                setOf("component_api"),
            ),
            VersionLine(
                "1.21.1",
                MinecraftVersion(1, 21, 1),
                "0.110.0+1.21.1",
                "0.16.5",
                21,
                CLASSIC_BIOMES + CAVES + CAVES_118 + MANGROVE_AND_DEEP_DARK,
                setOf("portal_teleport_api"),
            ),
            VersionLine(
                "1.21.4",
                MinecraftVersion(1, 21, 4),
                "0.119.2+1.21.4",
                "0.16.9",
                21,
                CLASSIC_BIOMES + CAVES + CAVES_118 + MANGROVE_AND_DEEP_DARK + PALE_GARDEN,
            ),
            VersionLine(
                "26.1.2",
                MinecraftVersion(26, 1, 2),
                "0.149.1+26.1.2",
                "0.18.4",
                25,
                CLASSIC_BIOMES + CAVES + CAVES_118 + MANGROVE_AND_DEEP_DARK + PALE_GARDEN,
                setOf("calendar_versioning", "official_mappings"),
            ),
            VersionLine(
                "26.2",
                MinecraftVersion(26, 2),
                "0.151.0+26.2",
                "0.19.3",
                25,
                CLASSIC_BIOMES +
                    CAVES +
                    CAVES_118 +
                    MANGROVE_AND_DEEP_DARK +
                    PALE_GARDEN +
                    SULFUR_CAVES,
            ),
            VersionLine(
                "26.3",
                MinecraftVersion(26, 3),
                "0.162.0+26.3",
                "0.19.5",
                25,
                CLASSIC_BIOMES +
                    CAVES +
                    CAVES_118 +
                    MANGROVE_AND_DEEP_DARK +
                    PALE_GARDEN +
                    SULFUR_CAVES +
                    DAPPLED_FOREST,
                setOf("reloadable_registry_api", "material_rules"),
            ),
        )

    private val byId = lines.associateBy(VersionLine::id)

    fun byId(id: String): VersionLine = byId[id] ?: error("Unsupported version line: $id")

    fun forMinecraft(version: MinecraftVersion): VersionLine =
        lines.lastOrNull { it.minecraft <= version }
            ?: error("No supported line for Minecraft $version")
}
