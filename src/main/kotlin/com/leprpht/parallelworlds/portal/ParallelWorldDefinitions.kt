package com.leprpht.parallelworlds.portal

import kotlin.collections.associateBy
import kotlin.collections.listOf
import net.minecraft.resources.Identifier

object ParallelWorldDefinitions {

    private fun biome(id: String): Identifier = Identifier.fromNamespaceAndPath("minecraft", id)

    private fun dimension(id: String): Identifier =
        Identifier.fromNamespaceAndPath("parallelworlds", id)

    val PLAINS =
        ParallelWorldDefinition(
            id = "plains",
            biome = biome("plains"),
            dimension = dimension("plains"),
            portalDesign = PortalDesigns.PLAINS,
        )

    val SUNFLOWER_PLAINS =
        ParallelWorldDefinition(
            id = "sunflower_plains",
            biome = biome("sunflower_plains"),
            dimension = dimension("sunflower_plains"),
            portalDesign = PortalDesigns.SUNFLOWER_PLAINS,
        )

    val FOREST =
        ParallelWorldDefinition(
            id = "forest",
            biome = biome("forest"),
            dimension = dimension("forest"),
            portalDesign = PortalDesigns.FOREST,
        )

    val FLOWER_FOREST =
        ParallelWorldDefinition(
            id = "flower_forest",
            biome = biome("flower_forest"),
            dimension = dimension("flower_forest"),
            portalDesign = PortalDesigns.FLOWER_FOREST,
        )

    val BIRCH_FOREST =
        ParallelWorldDefinition(
            id = "birch_forest",
            biome = biome("birch_forest"),
            dimension = dimension("birch_forest"),
            portalDesign = PortalDesigns.BIRCH_FOREST,
        )

    val OLD_GROWTH_BIRCH_FOREST =
        ParallelWorldDefinition(
            id = "old_growth_birch_forest",
            biome = biome("old_growth_birch_forest"),
            dimension = dimension("old_growth_birch_forest"),
            portalDesign = PortalDesigns.OLD_GROWTH_BIRCH_FOREST,
        )

    val DARK_FOREST =
        ParallelWorldDefinition(
            id = "dark_forest",
            biome = biome("dark_forest"),
            dimension = dimension("dark_forest"),
            portalDesign = PortalDesigns.DARK_FOREST,
        )

    val DAPPLED_FOREST =
        ParallelWorldDefinition(
            id = "dappled_forest",
            biome = biome("dappled_forest"),
            dimension = dimension("dappled_forest"),
            portalDesign = PortalDesigns.DAPPLED_FOREST,
        )

    val PALE_GARDEN =
        ParallelWorldDefinition(
            id = "pale_garden",
            biome = biome("pale_garden"),
            dimension = dimension("pale_garden"),
            portalDesign = PortalDesigns.PALE_GARDEN,
        )

    val CHERRY_GROVE =
        ParallelWorldDefinition(
            id = "cherry_grove",
            biome = biome("cherry_grove"),
            dimension = dimension("cherry_grove"),
            portalDesign = PortalDesigns.CHERRY_GROVE,
        )

    val TAIGA =
        ParallelWorldDefinition(
            id = "taiga",
            biome = biome("taiga"),
            dimension = dimension("taiga"),
            portalDesign = PortalDesigns.TAIGA,
        )

    val SNOWY_TAIGA =
        ParallelWorldDefinition(
            id = "snowy_taiga",
            biome = biome("snowy_taiga"),
            dimension = dimension("snowy_taiga"),
            portalDesign = PortalDesigns.SNOWY_TAIGA,
        )

    val OLD_GROWTH_SPRUCE_TAIGA =
        ParallelWorldDefinition(
            id = "old_growth_spruce_taiga",
            biome = biome("old_growth_spruce_taiga"),
            dimension = dimension("old_growth_spruce_taiga"),
            portalDesign = PortalDesigns.OLD_GROWTH_SPRUCE_TAIGA,
        )

    val OLD_GROWTH_PINE_TAIGA =
        ParallelWorldDefinition(
            id = "old_growth_pine_taiga",
            biome = biome("old_growth_pine_taiga"),
            dimension = dimension("old_growth_pine_taiga"),
            portalDesign = PortalDesigns.OLD_GROWTH_PINE_TAIGA,
        )

    val MEADOW =
        ParallelWorldDefinition(
            id = "meadow",
            biome = biome("meadow"),
            dimension = dimension("meadow"),
            portalDesign = PortalDesigns.MEADOW,
        )

    val GROVE =
        ParallelWorldDefinition(
            id = "grove",
            biome = biome("grove"),
            dimension = dimension("grove"),
            portalDesign = PortalDesigns.GROVE,
        )

    val SNOWY_SLOPES =
        ParallelWorldDefinition(
            id = "snowy_slopes",
            biome = biome("snowy_slopes"),
            dimension = dimension("snowy_slopes"),
            portalDesign = PortalDesigns.SNOWY_SLOPES,
        )

    val JAGGED_PEAKS =
        ParallelWorldDefinition(
            id = "jagged_peaks",
            biome = biome("jagged_peaks"),
            dimension = dimension("jagged_peaks"),
            portalDesign = PortalDesigns.JAGGED_PEAKS,
        )

    val FROZEN_PEAKS =
        ParallelWorldDefinition(
            id = "frozen_peaks",
            biome = biome("frozen_peaks"),
            dimension = dimension("frozen_peaks"),
            portalDesign = PortalDesigns.FROZEN_PEAKS,
        )

    val STONY_PEAKS =
        ParallelWorldDefinition(
            id = "stony_peaks",
            biome = biome("stony_peaks"),
            dimension = dimension("stony_peaks"),
            portalDesign = PortalDesigns.STONY_PEAKS,
        )

    val WINDSWEPT_HILLS =
        ParallelWorldDefinition(
            id = "windswept_hills",
            biome = biome("windswept_hills"),
            dimension = dimension("windswept_hills"),
            portalDesign = PortalDesigns.WINDSWEPT_HILLS,
        )

    val WINDSWEPT_GRAVELLY_HILLS =
        ParallelWorldDefinition(
            id = "windswept_gravelly_hills",
            biome = biome("windswept_gravelly_hills"),
            dimension = dimension("windswept_gravelly_hills"),
            portalDesign = PortalDesigns.WINDSWEPT_GRAVELLY_HILLS,
        )

    val WINDSWEPT_FOREST =
        ParallelWorldDefinition(
            id = "windswept_forest",
            biome = biome("windswept_forest"),
            dimension = dimension("windswept_forest"),
            portalDesign = PortalDesigns.WINDSWEPT_FOREST,
        )

    val WINDSWEPT_SAVANNA =
        ParallelWorldDefinition(
            id = "windswept_savanna",
            biome = biome("windswept_savanna"),
            dimension = dimension("windswept_savanna"),
            portalDesign = PortalDesigns.WINDSWEPT_SAVANNA,
        )

    val ICE_SPIKES =
        ParallelWorldDefinition(
            id = "ice_spikes",
            biome = biome("ice_spikes"),
            dimension = dimension("ice_spikes"),
            portalDesign = PortalDesigns.ICE_SPIKES,
        )

    val SNOWY_PLAINS =
        ParallelWorldDefinition(
            id = "snowy_plains",
            biome = biome("snowy_plains"),
            dimension = dimension("snowy_plains"),
            portalDesign = PortalDesigns.SNOWY_PLAINS,
        )

    val DESERT =
        ParallelWorldDefinition(
            id = "desert",
            biome = biome("desert"),
            dimension = dimension("desert"),
            portalDesign = PortalDesigns.DESERT,
        )

    val SAVANNA =
        ParallelWorldDefinition(
            id = "savanna",
            biome = biome("savanna"),
            dimension = dimension("savanna"),
            portalDesign = PortalDesigns.SAVANNA,
        )

    val SAVANNA_PLATEAU =
        ParallelWorldDefinition(
            id = "savanna_plateau",
            biome = biome("savanna_plateau"),
            dimension = dimension("savanna_plateau"),
            portalDesign = PortalDesigns.SAVANNA_PLATEAU,
        )

    val BADLANDS =
        ParallelWorldDefinition(
            id = "badlands",
            biome = biome("badlands"),
            dimension = dimension("badlands"),
            portalDesign = PortalDesigns.BADLANDS,
        )

    val WOODED_BADLANDS =
        ParallelWorldDefinition(
            id = "wooded_badlands",
            biome = biome("wooded_badlands"),
            dimension = dimension("wooded_badlands"),
            portalDesign = PortalDesigns.WOODED_BADLANDS,
        )

    val ERODED_BADLANDS =
        ParallelWorldDefinition(
            id = "eroded_badlands",
            biome = biome("eroded_badlands"),
            dimension = dimension("eroded_badlands"),
            portalDesign = PortalDesigns.ERODED_BADLANDS,
        )

    val JUNGLE =
        ParallelWorldDefinition(
            id = "jungle",
            biome = biome("jungle"),
            dimension = dimension("jungle"),
            portalDesign = PortalDesigns.JUNGLE,
        )

    val SPARSE_JUNGLE =
        ParallelWorldDefinition(
            id = "sparse_jungle",
            biome = biome("sparse_jungle"),
            dimension = dimension("sparse_jungle"),
            portalDesign = PortalDesigns.SPARSE_JUNGLE,
        )

    val BAMBOO_JUNGLE =
        ParallelWorldDefinition(
            id = "bamboo_jungle",
            biome = biome("bamboo_jungle"),
            dimension = dimension("bamboo_jungle"),
            portalDesign = PortalDesigns.BAMBOO_JUNGLE,
        )

    val SWAMP =
        ParallelWorldDefinition(
            id = "swamp",
            biome = biome("swamp"),
            dimension = dimension("swamp"),
            portalDesign = PortalDesigns.SWAMP,
        )

    val MANGROVE_SWAMP =
        ParallelWorldDefinition(
            id = "mangrove_swamp",
            biome = biome("mangrove_swamp"),
            dimension = dimension("mangrove_swamp"),
            portalDesign = PortalDesigns.MANGROVE_SWAMP,
        )

    val RIVER =
        ParallelWorldDefinition(
            id = "river",
            biome = biome("river"),
            dimension = dimension("river"),
            portalDesign = PortalDesigns.RIVER,
        )

    val FROZEN_RIVER =
        ParallelWorldDefinition(
            id = "frozen_river",
            biome = biome("frozen_river"),
            dimension = dimension("frozen_river"),
            portalDesign = PortalDesigns.FROZEN_RIVER,
        )

    val BEACH =
        ParallelWorldDefinition(
            id = "beach",
            biome = biome("beach"),
            dimension = dimension("beach"),
            portalDesign = PortalDesigns.BEACH,
        )

    val SNOWY_BEACH =
        ParallelWorldDefinition(
            id = "snowy_beach",
            biome = biome("snowy_beach"),
            dimension = dimension("snowy_beach"),
            portalDesign = PortalDesigns.SNOWY_BEACH,
        )

    val STONY_SHORE =
        ParallelWorldDefinition(
            id = "stony_shore",
            biome = biome("stony_shore"),
            dimension = dimension("stony_shore"),
            portalDesign = PortalDesigns.STONY_SHORE,
        )

    val OCEAN =
        ParallelWorldDefinition(
            id = "ocean",
            biome = biome("ocean"),
            dimension = dimension("ocean"),
            portalDesign = PortalDesigns.OCEAN,
        )

    val WARM_OCEAN =
        ParallelWorldDefinition(
            id = "warm_ocean",
            biome = biome("warm_ocean"),
            dimension = dimension("warm_ocean"),
            portalDesign = PortalDesigns.WARM_OCEAN,
        )

    val LUKEWARM_OCEAN =
        ParallelWorldDefinition(
            id = "lukewarm_ocean",
            biome = biome("lukewarm_ocean"),
            dimension = dimension("lukewarm_ocean"),
            portalDesign = PortalDesigns.LUKEWARM_OCEAN,
        )

    val DEEP_OCEAN =
        ParallelWorldDefinition(
            id = "deep_ocean",
            biome = biome("deep_ocean"),
            dimension = dimension("deep_ocean"),
            portalDesign = PortalDesigns.DEEP_OCEAN,
        )

    val DEEP_LUKEWARM_OCEAN =
        ParallelWorldDefinition(
            id = "deep_lukewarm_ocean",
            biome = biome("deep_lukewarm_ocean"),
            dimension = dimension("deep_lukewarm_ocean"),
            portalDesign = PortalDesigns.DEEP_LUKEWARM_OCEAN,
        )

    val COLD_OCEAN =
        ParallelWorldDefinition(
            id = "cold_ocean",
            biome = biome("cold_ocean"),
            dimension = dimension("cold_ocean"),
            portalDesign = PortalDesigns.COLD_OCEAN,
        )

    val DEEP_COLD_OCEAN =
        ParallelWorldDefinition(
            id = "deep_cold_ocean",
            biome = biome("deep_cold_ocean"),
            dimension = dimension("deep_cold_ocean"),
            portalDesign = PortalDesigns.DEEP_COLD_OCEAN,
        )

    val FROZEN_OCEAN =
        ParallelWorldDefinition(
            id = "frozen_ocean",
            biome = biome("frozen_ocean"),
            dimension = dimension("frozen_ocean"),
            portalDesign = PortalDesigns.FROZEN_OCEAN,
        )

    val DEEP_FROZEN_OCEAN =
        ParallelWorldDefinition(
            id = "deep_frozen_ocean",
            biome = biome("deep_frozen_ocean"),
            dimension = dimension("deep_frozen_ocean"),
            portalDesign = PortalDesigns.DEEP_FROZEN_OCEAN,
        )

    val MUSHROOM_FIELDS =
        ParallelWorldDefinition(
            id = "mushroom_fields",
            biome = biome("mushroom_fields"),
            dimension = dimension("mushroom_fields"),
            portalDesign = PortalDesigns.MUSHROOM_FIELDS,
        )

    val LUSH_CAVES =
        ParallelWorldDefinition(
            id = "lush_caves",
            biome = biome("lush_caves"),
            dimension = dimension("lush_caves"),
            portalDesign = PortalDesigns.LUSH_CAVES,
        )

    val DRIPSTONE_CAVES =
        ParallelWorldDefinition(
            id = "dripstone_caves",
            biome = biome("dripstone_caves"),
            dimension = dimension("dripstone_caves"),
            portalDesign = PortalDesigns.DRIPSTONE_CAVES,
        )

    val DEEP_DARK =
        ParallelWorldDefinition(
            id = "deep_dark",
            biome = biome("deep_dark"),
            dimension = dimension("deep_dark"),
            portalDesign = PortalDesigns.DEEP_DARK,
        )

    val SULFUR_CAVES =
        ParallelWorldDefinition(
            id = "sulfur_caves",
            biome = biome("sulfur_caves"),
            dimension = dimension("sulfur_caves"),
            portalDesign = PortalDesigns.SULFUR_CAVES,
        )

    val ALL: List<ParallelWorldDefinition> =
        listOf(
            PLAINS,
            SUNFLOWER_PLAINS,
            FOREST,
            FLOWER_FOREST,
            BIRCH_FOREST,
            OLD_GROWTH_BIRCH_FOREST,
            DARK_FOREST,
            DAPPLED_FOREST,
            PALE_GARDEN,
            CHERRY_GROVE,
            TAIGA,
            SNOWY_TAIGA,
            OLD_GROWTH_SPRUCE_TAIGA,
            OLD_GROWTH_PINE_TAIGA,
            MEADOW,
            GROVE,
            SNOWY_SLOPES,
            JAGGED_PEAKS,
            FROZEN_PEAKS,
            STONY_PEAKS,
            WINDSWEPT_HILLS,
            WINDSWEPT_GRAVELLY_HILLS,
            WINDSWEPT_FOREST,
            WINDSWEPT_SAVANNA,
            ICE_SPIKES,
            SNOWY_PLAINS,
            DESERT,
            SAVANNA,
            SAVANNA_PLATEAU,
            BADLANDS,
            WOODED_BADLANDS,
            ERODED_BADLANDS,
            JUNGLE,
            SPARSE_JUNGLE,
            BAMBOO_JUNGLE,
            SWAMP,
            MANGROVE_SWAMP,
            RIVER,
            FROZEN_RIVER,
            BEACH,
            SNOWY_BEACH,
            STONY_SHORE,
            OCEAN,
            WARM_OCEAN,
            LUKEWARM_OCEAN,
            DEEP_OCEAN,
            DEEP_LUKEWARM_OCEAN,
            COLD_OCEAN,
            DEEP_COLD_OCEAN,
            FROZEN_OCEAN,
            DEEP_FROZEN_OCEAN,
            MUSHROOM_FIELDS,
            LUSH_CAVES,
            DRIPSTONE_CAVES,
            DEEP_DARK,
            SULFUR_CAVES,
        )

    val BY_ID: Map<String, ParallelWorldDefinition> = ALL.associateBy { it.id }

    val BY_DIMENSION: Map<Identifier, ParallelWorldDefinition> = ALL.associateBy { it.dimension }

    val BY_DESIGN: Map<PortalDesign, ParallelWorldDefinition> = ALL.associateBy { it.portalDesign }

    fun all(): List<ParallelWorldDefinition> = ALL

    fun byId(id: String): ParallelWorldDefinition? = BY_ID[id]

    fun byDimension(dimension: Identifier): ParallelWorldDefinition? = BY_DIMENSION[dimension]

    fun byDesign(design: PortalDesign): ParallelWorldDefinition? = BY_DESIGN[design]
}
