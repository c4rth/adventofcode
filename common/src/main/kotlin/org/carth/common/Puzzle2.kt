package org.carth.common

import io.github.oshai.kotlinlogging.KotlinLogging
import org.carth.common.Puzzle2.Part
import org.carth.common.Puzzle2.Type
import java.io.File
import java.util.*
import kotlin.time.measureTimedValue

sealed class PuzzlePart(
    val part: Part,
    val type: Type,
    val suffix: String?,
    val expected: Any
) {
    class Part1Sample(suffix: String? = null, expected: Any) : PuzzlePart(Part.ONE, Type.SAMPLE, suffix, expected)
    class Part1(suffix: String? = null, expected: Any) : PuzzlePart(Part.ONE, Type.PUZZLE, suffix, expected)
    class Part2Sample(suffix: String? = null, expected: Any) : PuzzlePart(Part.TWO, Type.SAMPLE, suffix, expected)
    class Part2(suffix: String? = null, expected: Any) : PuzzlePart(Part.TWO, Type.PUZZLE, suffix, expected)
}

abstract class Puzzle2<T1, T2>() {

    enum class Part() {
        ONE, TWO
    }

    enum class Type(val text: String) {
        SAMPLE("sample"), PUZZLE("puzzle")
    }

    protected val logger = KotlinLogging.logger {}
    protected lateinit var input: String

    private fun readInput(type: Type, suffix: String?): String {
        val day: String = this.javaClass.simpleName.take(5).lowercase(Locale.getDefault())
        val fileSuffix = if (suffix != null) "-$suffix" else ""
        return File("$day/${type.toString().lowercase(Locale.getDefault())}${fileSuffix}.txt".toURI()).readText()
    }

    private fun solve(part: Part, type: Type, suffix: String?, expected: Any) {
        this.input = readInput(type, suffix)
        val (answer, durationExecution) = measureTimedValue {
            if (part == Part.ONE)
                solvePart1()
            else
                solvePart2()
        }
        logger.info { "Part [${part}] : type [${type.text}] - ${durationExecution.inWholeMilliseconds} ms." }
        if (answer != expected) {
            throw IllegalArgumentException("Wrong answer: $answer, expected: $expected")
        }
    }

    fun solve(parts: List<PuzzlePart>) {
        parts.forEach { part ->
            solve(part.part, part.type, part.suffix, part.expected)
        }
    }

    abstract fun solvePart1(): T1
    abstract fun solvePart2(): T2
}


private fun String.toURI() =
    object {}.javaClass.classLoader.getResource(this)?.toURI()
        ?: throw IllegalArgumentException("Cannot find Resource: $this")

