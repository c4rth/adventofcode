pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        // add repositories:
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/amper/amper")
    }
}

plugins {
    // apply the plugin:
    id("org.jetbrains.amper.settings.plugin").version("0.1.0")
}

rootProject.name = "adventofcode"
include("common")
include("aoc2020")
include("aoc2021")
include("aoc2022")
include("aoc2023")
include("common-tests")

// apply the plugin:
plugins.apply("org.jetbrains.amper.settings.plugin")
