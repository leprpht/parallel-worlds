import java.util.Properties
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("net.fabricmc.fabric-loom") version "1.17.21"
    kotlin("jvm") version "2.4.20"
    id("com.diffplug.spotless") version "8.10.3"
}

group = project.property("maven_group") as String
version = project.property("mod_version") as String

val versionLine = providers.gradleProperty("versionLine").orElse("26.3").get()
val versionMatrix = Properties().apply {
    file("versions/version-lines.properties").inputStream().use(::load)
}
fun versionSetting(name: String): String =
    versionMatrix.getProperty("$versionLine.$name")
        ?: error("Unknown versionLine=$versionLine; run supportedVersionLines")

val selectedMinecraftVersion = versionSetting("minecraft")
val selectedLoaderVersion = versionSetting("loader")
val selectedFabricApiVersion = versionSetting("fabricApi")
val selectedJavaVersion = versionSetting("java").toInt()
val versionSourceDir = "versions/$versionLine/src"

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
                "$versionSourceDir/main/kotlin",
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

    minecraft(
        "com.mojang:minecraft:$selectedMinecraftVersion"
    )

    implementation(
        "net.fabricmc:fabric-loader:$selectedLoaderVersion"
    )

    implementation(
        "net.fabricmc.fabric-api:fabric-api:$selectedFabricApiVersion"
    )

    implementation(
        "net.fabricmc:fabric-language-kotlin:${project.property("fabric_language_kotlin_version")}"
    )

    testImplementation(kotlin("test"))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(selectedJavaVersion))
    }
}

kotlin {
    jvmToolchain(selectedJavaVersion)
}

tasks.withType<KotlinCompile>().configureEach {
    if (name == "compileKotlin" && versionLine == "1.16.1") {
        // Older adapters provide their own definitions/designs where newer
        // registry constants (for example 26.3's poplar blocks) do not exist.
        val sharedSources = fileTree("common/src/main/kotlin") {
            exclude("com/leprpht/parallelworlds/portal/ParallelWorldDefinition.kt")
            exclude("com/leprpht/parallelworlds/portal/ParallelWorldDefinitions.kt")
            exclude("com/leprpht/parallelworlds/portal/PortalDesign.kt")
            exclude("com/leprpht/parallelworlds/portal/PortalDesigns.kt")
        }
        setSource(files(sharedSources, "$versionSourceDir/main/kotlin"))
    }
}

spotless {
    kotlin {
        target("common/src/**/*.kt", "versions/src/**/*.kt")
        ktfmt("0.64").kotlinlangStyle()
    }

    java {
        target("common/src/**/*.java", "versions/src/**/*.java")
        googleJavaFormat("1.36.1")
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

    fun versionAtLeast(left: List<Int>, right: List<Int>): Boolean =
        (left + List(3 - left.size) { 0 }).zip(right + List(3 - right.size) { 0 })
            .firstOrNull { (a, b) -> a != b }
            ?.let { (a, b) -> a > b }
            ?: true

    exclude { details ->
        val fileName = details.path.substringAfterLast('/').removeSuffix(".json")
        val introduced = introducedIn[fileName] ?: return@exclude false
        !versionAtLeast(targetKey, introduced)
    }

    val props = mapOf(
        "version" to project.version,
        "minecraft_version" to selectedMinecraftVersion,
        "loader_version" to selectedLoaderVersion,
        "fabric_language_kotlin_version" to project.property(
            "fabric_language_kotlin_version"
        )
    )

    inputs.properties(props)

    filesMatching("fabric.mod.json") {
        expand(props)
    }
}

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
