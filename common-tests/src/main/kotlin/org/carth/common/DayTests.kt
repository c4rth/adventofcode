package org.carth.common

import io.github.oshai.kotlinlogging.KotlinLogging
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.TestMethodOrder
import java.io.File
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor
import kotlin.test.assertEquals
import kotlin.time.measureTimedValue

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
abstract class DayTests<T : Puzzle<*, *>>(private val clazz: KClass<T>) {

    private val logger = KotlinLogging.logger {}

    enum class Type(val text: String) {
        TEST("test"), INPUT("input")
    }

    private val day: String = this.javaClass.simpleName.take(5).lowercase(Locale.getDefault())

    private fun String.toURI() =
        object {}.javaClass.classLoader.getResource(this)?.toURI()
            ?: throw IllegalArgumentException("Cannot find Resource: $this")

    private fun getFilename(type: Type, suffix: String = ""): String =
        "$day/${type.text}$suffix.txt"

    fun readInput(type: Type, suffix: String = ""): String = File(getFilename(type, suffix).toURI()).readText()

    private fun getInstance(data: String): T = clazz.primaryConstructor!!.call(data)

    fun solve(part: Part, type: Type, suffix: String = "", args: List<Any> = emptyList(), expected: Any) {
        val input = readInput(type, suffix)
        val instance = getInstance(input)
        instance.args = args
        val (answer, durationExecution) = measureTimedValue {
            if (part == Part.ONE)
                instance.solvePartOne()
            else
                instance.solvePartTwo()
        }
        logger.info { "Part [${part.text}] : type [${type.text}] - ${durationExecution.inWholeMilliseconds} ms." }
        assertEquals(expected, answer)
    }

    fun solvePartOneSample(expected: Any) = solve(Part.ONE, Type.TEST, expected = expected)
    fun solvePartOneSample(fileSuffix: String = "", expected: Any) =
        solve(Part.ONE, Type.TEST, suffix = fileSuffix, expected = expected)

    fun solvePartOne(expected: Any) = solve(Part.ONE, Type.INPUT, expected = expected)

    fun solvePartTwoSample(expected: Any) = solve(Part.TWO, Type.TEST, expected = expected)
    fun solvePartTwoSample(fileSuffix: String = "", expected: Any) =
        solve(Part.TWO, Type.TEST, suffix = fileSuffix, expected = expected)

    fun solvePartTwo(expected: Any) = solve(Part.TWO, Type.INPUT, expected = expected)

}