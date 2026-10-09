package com.leprpht.parallelworlds

import com.leprpht.parallelworlds.portal.ParallelWorldDefinitions
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import net.minecraft.SharedConstants
import net.minecraft.server.Bootstrap

class ParallelWorldDefinitionsTest {

    @BeforeTest
    fun bootstrapMinecraft() {
        SharedConstants.tryDetectVersion()
        Bootstrap.bootStrap()
    }

    @Test
    fun definitionsHaveUniqueIdsDimensionsAndDesigns() {
        val definitions = ParallelWorldDefinitions.all()

        assertTrue(definitions.isNotEmpty())
        assertEquals(definitions.size, definitions.map { it.id }.toSet().size)
        assertEquals(definitions.size, definitions.map { it.dimension }.toSet().size)
        assertEquals(definitions.size, definitions.map { it.portalDesign }.toSet().size)
        assertEquals(definitions.size, ParallelWorldDefinitions.BY_ID.size)
        assertEquals(definitions.size, ParallelWorldDefinitions.BY_DIMENSION.size)
        assertEquals(definitions.size, ParallelWorldDefinitions.BY_DESIGN.size)
    }

    @Test
    fun everyLookupReturnsTheOriginalDefinition() {
        ParallelWorldDefinitions.all().forEach { definition ->
            assertEquals(definition, ParallelWorldDefinitions.byId(definition.id))
            assertEquals(definition, ParallelWorldDefinitions.byDimension(definition.dimension))
            assertEquals(definition, ParallelWorldDefinitions.byDesign(definition.portalDesign))
        }
    }

    @Test
    fun everyDefinitionHasACompletePortalDesign() {
        ParallelWorldDefinitions.all().forEach { definition ->
            val design = definition.portalDesign
            listOf(
                    design.topLeft,
                    design.topInnerLeft,
                    design.topInnerRight,
                    design.topRight,
                    design.upperLeft,
                    design.upperRight,
                    design.middleLeft,
                    design.middleRight,
                    design.lowerLeft,
                    design.lowerRight,
                    design.bottomLeft,
                    design.bottomInnerLeft,
                    design.bottomInnerRight,
                    design.bottomRight,
                )
                .forEach(::assertNotNull)
        }
    }
}
