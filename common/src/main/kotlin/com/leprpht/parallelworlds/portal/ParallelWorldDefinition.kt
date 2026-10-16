package com.leprpht.parallelworlds.portal

import net.minecraft.resources.Identifier

data class ParallelWorldDefinition(
    val id: String,
    val biome: Identifier,
    val dimension: Identifier,
    val portalDesign: PortalDesign,
)
