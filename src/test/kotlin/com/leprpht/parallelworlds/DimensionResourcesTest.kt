package com.leprpht.parallelworlds

import com.leprpht.parallelworlds.portal.ParallelWorldDefinitions
import java.nio.charset.StandardCharsets
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import net.minecraft.SharedConstants
import net.minecraft.server.Bootstrap

class DimensionResourcesTest {

    @BeforeTest
    fun bootstrapMinecraft() {
        SharedConstants.tryDetectVersion()
        Bootstrap.bootStrap()
    }

    @Test
    fun everyDefinitionHasOneDimensionResourceWithTheExpectedBiome() {
        ParallelWorldDefinitions.all().forEach { definition ->
            val resourcePath = "data/parallelworlds/dimension/${definition.id}.json"
            val resource = javaClass.classLoader.getResource(resourcePath)
            assertNotNull(resource, "Missing dimension resource: $resourcePath")

            val json = resource.openStream().use { it.readBytes().toString(StandardCharsets.UTF_8) }

            assertTrue(json.contains("\"type\": \"minecraft:overworld\""))
            assertTrue(
                json.contains("\"biome\": \"${definition.biome}\""),
                "${definition.id} does not use biome ${definition.biome}",
            )
        }
    }
}
