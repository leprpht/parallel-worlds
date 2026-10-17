package com.leprpht.parallelworlds

import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager

object ParallelWorlds : ModInitializer {

    private val logger = LogManager.getLogger("parallelworlds")

    override fun onInitialize() {
        System.setProperty("parallelworlds.version", "1.16.1")
        logger.info("Parallel Worlds initialized")

        ParallelWorldsPortals.register()
    }
}
