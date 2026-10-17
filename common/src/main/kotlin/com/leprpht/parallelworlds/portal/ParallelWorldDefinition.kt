package com.leprpht.parallelworlds.portal

data class ParallelWorldDefinition(
    val id: String,
    val biome: String,
    val dimension: String,
    val portalDesign: PortalDesign,
)
