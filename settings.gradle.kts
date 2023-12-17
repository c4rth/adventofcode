pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/amper/amper")
    }
}

plugins {
    // apply the plugin:
    id("org.jetbrains.amper.settings.plugin").version("0.1.4")
}

rootProject.name = "adventofcode"
include("common")
include("common-tests")
include("aoc2020")
include("aoc2021")
include("aoc2022")
include("aoc2023")

