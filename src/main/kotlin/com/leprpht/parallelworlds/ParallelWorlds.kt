package com.leprpht.parallelworlds

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import org.slf4j.LoggerFactory

object ParallelWorlds : ModInitializer {
    private val logger = LoggerFactory.getLogger("parallelworlds")

    override fun onInitialize() {
        logger.info("Parallel Worlds initialized")

        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            ParallelWorldsCommands.register(dispatcher)
        }
    }
}
