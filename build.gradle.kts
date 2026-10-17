import java.util.Properties
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("net.fabricmc.fabric-loom") version "1.17.21" apply false
    id("net.fabricmc.fabric-loom-remap") version "1.17.21" apply false
    kotlin("jvm") version "2.4.20"
    id("com.diffplug.spotless") version "8.10.3"
}

group = project.property("maven_group") as String
version = project.property("mod_version") as String

val versionLine = providers.gradleProperty("versionLine").orElse("26.3").get()
apply(plugin = if (versionLine.startsWith("26.")) "net.fabricmc.fabric-loom" else "net.fabricmc.fabric-loom-remap")
val loomExtension = extensions.getByType<LoomGradleExtensionAPI>()
val versionMatrix = Properties().apply {
    file("versions/version-lines.properties").inputStream().use(::load)
}
fun versionSetting(name: String): String =
    versionMatrix.getProperty("$versionLine.$name")
        ?: error("Unknown versionLine=$versionLine; run supportedVersionLines")

val selectedMinecraftVersion = versionSetting("minecraft")
val selectedLoaderVersion = versionSetting("loader")
val selectedFabricApiVersion = versionSetting("fabricApi")
val selectedLanguageKotlinVersion = versionSetting("languageKotlin")
val selectedJavaVersion = versionSetting("java").toInt()
val buildJavaVersion = if (selectedJavaVersion < 21) 21 else selectedJavaVersion
val versionSourceDir = "versions/$versionLine/src"
val modDependencyConfiguration =
    if (versionLine.startsWith("26.")) "implementation" else "modImplementation"

base {
    archivesName = "${project.property("archives_base_name")}-${versionLine}"
}

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
}

// Shared, Minecraft-independent compatibility data lives in common. The
// version adapter and the resources for the current release live under
// versions/<minecraft>/ so adding a release never changes older adapters.
sourceSets {
    named("main") {
        java.setSrcDirs(
            listOf(
                "common/src/main/kotlin",
                "common/src/main/java",
                "$versionSourceDir/main/kotlin",
                "$versionSourceDir/main/java",
            )
        )
        resources.setSrcDirs(
            listOf(
                "common/src/main/resources",
                "$versionSourceDir/main/resources",
            )
        )
    }
    named("test") {
        java.setSrcDirs(listOf("$versionSourceDir/test/kotlin"))
        resources.setSrcDirs(listOf("$versionSourceDir/test/resources"))
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    add("minecraft",
        "com.mojang:minecraft:$selectedMinecraftVersion"
    )

    if (!versionLine.startsWith("26.")) {
        add("mappings", loomExtension.officialMojangMappings())
    }

    add(modDependencyConfiguration,
        "net.fabricmc:fabric-loader:$selectedLoaderVersion"
    )

    add(modDependencyConfiguration,
        "net.fabricmc.fabric-api:fabric-api:$selectedFabricApiVersion"
    )

    add(modDependencyConfiguration,
        "net.fabricmc:fabric-language-kotlin:$selectedLanguageKotlinVersion"
    )

    testImplementation(kotlin("test"))
}

java {
    toolchain {
        // Gradle/Loom can build old bytecode with a modern JDK. The selected
        // value remains the Minecraft runtime requirement; this only avoids
        // requiring every historical JDK on the developer machine.
        languageVersion.set(JavaLanguageVersion.of(buildJavaVersion))
    }
}

kotlin {
    jvmToolchain(buildJavaVersion)
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(selectedJavaVersion)
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(
        org.jetbrains.kotlin.gradle.dsl.JvmTarget.fromTarget(
            if (selectedJavaVersion == 8) "1.8" else selectedJavaVersion.toString()
        )
    )
}

spotless {
    kotlin {
        target("common/src/**/*.kt", "versions/**/src/**/*.kt")
        ktfmt("0.64").kotlinlangStyle()
    }

    java {
        target("common/src/**/*.java", "versions/**/src/**/*.java")
        googleJavaFormat("1.36.1").aosp()
    }
}

tasks.processResources {
    val targetMinecraft = selectedMinecraftVersion
    val targetKey = targetMinecraft.split('.').map(String::toInt)
    val introducedIn = mapOf(
        "lush_caves" to listOf(1, 17),
        "dripstone_caves" to listOf(1, 17),
        "meadow" to listOf(1, 18),
        "grove" to listOf(1, 18),
        "snowy_slopes" to listOf(1, 18),
        "jagged_peaks" to listOf(1, 18),
        "frozen_peaks" to listOf(1, 18),
        "stony_peaks" to listOf(1, 18),
        "windswept_hills" to listOf(1, 18),
        "windswept_gravelly_hills" to listOf(1, 18),
        "windswept_forest" to listOf(1, 18),
        "windswept_savanna" to listOf(1, 18),
        "old_growth_birch_forest" to listOf(1, 18),
        "old_growth_spruce_taiga" to listOf(1, 18),
        "old_growth_pine_taiga" to listOf(1, 18),
        "mangrove_swamp" to listOf(1, 19),
        "deep_dark" to listOf(1, 19),
        "cherry_grove" to listOf(1, 20),
        "pale_garden" to listOf(1, 21, 4),
        "sulfur_caves" to listOf(26, 2),
        "dappled_forest" to listOf(26, 3),
    )
    val renamedIn = mapOf(
        "snowy_tundra" to listOf(1, 18),
        "stone_shore" to listOf(1, 18),
        "wooded_badlands_plateau" to listOf(1, 18),
        "mountains" to listOf(1, 18),
        "gravelly_mountains" to listOf(1, 18),
        "wooded_mountains" to listOf(1, 18),
        "tall_birch_forest" to listOf(1, 18),
        "giant_tree_taiga" to listOf(1, 18),
        "giant_spruce_taiga" to listOf(1, 18),
        "shattered_savanna" to listOf(1, 18),
        "shattered_savanna_plateau" to listOf(1, 18),
        "deep_warm_ocean" to listOf(1, 18),
        "snowy_plains" to listOf(1, 18),
        "stony_shore" to listOf(1, 18),
        "wooded_badlands" to listOf(1, 18),
        "windswept_hills" to listOf(1, 18),
        "windswept_gravelly_hills" to listOf(1, 18),
        "windswept_forest" to listOf(1, 18),
        "old_growth_birch_forest" to listOf(1, 18),
        "old_growth_spruce_taiga" to listOf(1, 18),
        "old_growth_pine_taiga" to listOf(1, 18),
    )

    fun versionAtLeast(left: List<Int>, right: List<Int>): Boolean =
        (left + List(3 - left.size) { 0 }).zip(right + List(3 - right.size) { 0 })
            .firstOrNull { (a, b) -> a != b }
            ?.let { (a, b) -> a > b }
            ?: true

    exclude { details ->
        val fileName = details.path.substringAfterLast('/').removeSuffix(".json")
        val renamed = renamedIn[fileName]
        if (renamed != null) {
            val isNewName = fileName !in BiomeCatalogLegacyNames
            return@exclude if (isNewName) {
                !versionAtLeast(targetKey, renamed)
            } else {
                versionAtLeast(targetKey, renamed)
            }
        }
        val introduced = introducedIn[fileName] ?: return@exclude false
        !versionAtLeast(targetKey, introduced)
    }

    val props = mapOf(
        "version" to project.version,
        "minecraft_version" to selectedMinecraftVersion,
        "loader_version" to selectedLoaderVersion,
        "fabric_language_kotlin_version" to selectedLanguageKotlinVersion
    )

    inputs.properties(props)
    inputs.property("versionLine", versionLine)

    filesMatching("fabric.mod.json") {
        expand(props)
    }
}

private val BiomeCatalogLegacyNames = setOf(
    "snowy_tundra",
    "stone_shore",
    "wooded_badlands_plateau",
    "mountains",
    "gravelly_mountains",
    "wooded_mountains",
    "tall_birch_forest",
    "giant_tree_taiga",
    "giant_spruce_taiga",
    "shattered_savanna",
    "shattered_savanna_plateau",
    "deep_warm_ocean",
)

tasks.jar {
    from("LICENSE") {
        rename {
            "${it}_${project.property("archives_base_name")}"
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("supportedVersionLines") {
    group = "verification"
    description = "Print the Minecraft/Fabric build lines maintained by this project."
    doLast {
        println(
            """
            1.16.1  classic biomes, pre-1.16.2 Fabric API
            1.16.2  classic biomes, post-refactor Fabric API (through 1.16.5)
            1.17.1  cave biomes and 1.17 blocks
            1.18.2  renamed mountains and highland biomes
            1.19.4  mangrove swamp and deep dark
            1.20.1  stable 1.20 baseline
            1.20.6  component API baseline
            1.21.1  portal/registry API baseline
            1.21.4  pale garden
            26.1.2  calendar-versioned baseline and official mappings
            26.2    sulfur caves
            26.3    dappled forest and 26.3 API removals
            """.trimIndent()
        )
    }
}
