package com.leprpht.parallelworlds

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object ParallelWorlds : ModInitializer {

    private val logger = LoggerFactory.getLogger("parallelworlds")

    override fun onInitialize() {
        System.setProperty("parallelworlds.version", "1.17.1")
        logger.info("Parallel Worlds initialized")

        ParallelWorldsPortals.register()
    }
}
