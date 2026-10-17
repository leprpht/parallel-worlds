package com.leprpht.parallelworlds.portal

import net.minecraft.world.level.block.Block

data class PortalDesign(
    val topLeft: Block,
    val topInnerLeft: Block,
    val topInnerRight: Block,
    val topRight: Block,
    val upperLeft: Block,
    val upperRight: Block,
    val middleLeft: Block,
    val middleRight: Block,
    val lowerLeft: Block,
    val lowerRight: Block,
    val bottomLeft: Block,
    val bottomInnerLeft: Block,
    val bottomInnerRight: Block,
    val bottomRight: Block,
)
