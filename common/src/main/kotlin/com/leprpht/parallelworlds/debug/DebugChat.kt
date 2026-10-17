package com.leprpht.parallelworlds.debug

import net.minecraft.server.level.ServerLevel

/** Optional in-game diagnostics enabled with -Dparallelworlds.debugChat=true. */
object DebugChat {
    private val enabled: Boolean
        get() = System.getProperty("parallelworlds.debugChat", "false").toBoolean()

    fun send(world: ServerLevel, message: String) {
        if (!enabled) return

        runCatching {
            val server = world.server
            val playerList = server.javaClass.getMethod("getPlayerList").invoke(server)
            val componentType = Class.forName("net.minecraft.network.chat.Component")
            val component =
                componentType
                    .getMethod("literal", String::class.java)
                    .invoke(null, "[ParallelWorlds] $message")
            val players =
                playerList.javaClass.getMethod("getPlayers").invoke(playerList) as Iterable<*>

            players.forEach { player ->
                player
                    ?.javaClass
                    ?.getMethod("sendSystemMessage", componentType)
                    ?.invoke(player, component)
            }
        }
    }
}
