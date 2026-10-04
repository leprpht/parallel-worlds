plugins {
    id("net.fabricmc.fabric-loom") version "1.17.21"
    kotlin("jvm") version "2.4.20"
    id("com.diffplug.spotless") version "8.10.3"
}

group = project.property("maven_group") as String
version = project.property("mod_version") as String

base {
    archivesName = project.property("archives_base_name") as String
}

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
}

dependencies {
    implementation(kotlin("stdlib"))

    minecraft(
        "com.mojang:minecraft:${project.property("minecraft_version")}"
    )

    implementation(
        "net.fabricmc:fabric-loader:${project.property("loader_version")}"
    )

    implementation(
        "net.fabricmc.fabric-api:fabric-api:${project.property("fabric_api_version")}"
    )

    implementation(
        "net.fabricmc:fabric-language-kotlin:${project.property("fabric_language_kotlin_version")}"
    )
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

kotlin {
    jvmToolchain(25)
}

spotless {
    kotlin {
        target("src/**/*.kt")
        ktfmt("0.64").kotlinlangStyle()
    }

    java {
        target("src/**/*.java")
        googleJavaFormat("1.36.1")
    }
}

tasks.processResources {
    val props = mapOf(
        "version" to project.version,
        "minecraft_version" to project.property("minecraft_version"),
        "loader_version" to project.property("loader_version"),
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