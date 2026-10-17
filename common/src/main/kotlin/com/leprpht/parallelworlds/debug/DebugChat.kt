package com.leprpht.parallelworlds.debug

import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel

/** Optional in-game diagnostics enabled with -Dparallelworlds.debugChat=true. */
object DebugChat {
    private val enabled: Boolean
        get() = System.getProperty("parallelworlds.debugChat", "false").toBoolean()

    fun send(world: ServerLevel, message: String) {
        if (!enabled) return

        world.server.playerList.broadcastSystemMessage(
            Component.literal("[ParallelWorlds] $message"),
            false,
        )
    }
}
