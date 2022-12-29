package org.carth.common

import org.junit.jupiter.api.extension.ExtendWith
import java.io.File
import java.util.*

private const val TEST = "test"

private const val INPUT = "input"

@ExtendWith(TimingExtension::class)
open class DayTests {

    private val year: String = this.javaClass.`package`.name.takeLast(2)
    private val day: String = this.javaClass.simpleName.take(5).lowercase(Locale.getDefault())

    private fun String.toURI() =
        object {}.javaClass.classLoader.getResource(this)?.toURI()
            ?: throw IllegalArgumentException("Cannot find Resource: $this")

    private fun internalAsListOfString(type: String, suffix: String = ""): List<String> =
        File(getFilename(type, suffix).toURI()).readLines()

    private fun getFilename(type: String, suffix: String = ""): String = "aoc$year/$day/$type$suffix.txt"

    fun testInputAsListOfString(suffix: String = ""): List<String> = internalAsListOfString(TEST, suffix)

    fun testInputAsText(suffix: String = ""): String = File(getFilename(TEST, suffix).toURI()).readText()

    fun testInputAsListOfInt(suffix: String = ""): List<Int> = internalAsListOfString(TEST, suffix).map { it.toInt() }

    fun inputAsListOfString(suffix: String = ""): List<String> = internalAsListOfString(INPUT, suffix)

    fun inputAsText(suffix: String = ""): String = File(getFilename(INPUT, suffix).toURI()).readText()

    fun inputAsListOfInt(suffix: String = ""): List<Int> = internalAsListOfString(INPUT, suffix).map { it.toInt() }

}