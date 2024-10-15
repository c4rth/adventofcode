package org.carth.common

import io.github.oshai.kotlinlogging.KotlinLogging
import org.carth.common.Puzzle2.Part
import org.carth.common.Puzzle2.Type
import java.io.File
import java.util.*
import kotlin.time.measureTimedValue

class PuzzlePart(
    val part: Part,
    val type: Type,
    val suffix: String?,
    val expected: Any
)


fun sample1(suffix: String? = null, expected: Any) = PuzzlePart(Part.ONE, Type.SAMPLE, suffix, expected)
fun puzzle1(suffix: String? = null, expected: Any) = PuzzlePart(Part.ONE, Type.PUZZLE, suffix, expected)
fun sample2(suffix: String? = null, expected: Any) = PuzzlePart(Part.TWO, Type.SAMPLE, suffix, expected)
fun puzzle2(suffix: String? = null, expected: Any) = PuzzlePart(Part.TWO, Type.PUZZLE, suffix, expected)

abstract class Puzzle2<T1, T2>() {

    enum class Part() {
        ONE, TWO
    }

    enum class Type(val text: String) {
        SAMPLE("sample"), PUZZLE("puzzle")
    }

    protected val logger = KotlinLogging.logger {}

    protected lateinit var input: String

    private fun fileSuffix(suffix: String?) = if (suffix != null) "-$suffix" else ""

    private fun readInput(filename: String): String {
        return File(filename.toURI()).readText()
    }

    private fun solve(part: Part, type: Type, suffix: String?, expected: Any) {
        val day: String = this.javaClass.simpleName.take(5)
        val filename = "$day/${type.toString().lowercase(Locale.getDefault())}${fileSuffix(suffix)}.txt"
        this.input = readInput(filename)
        val (answer, durationExecution) = measureTimedValue {
            if (part == Part.ONE)
                solvePart1()
            else
                solvePart2()
        }
        val log = "${type.text} / ${part.toString().lowercase()} - file [$filename] :"
        if (answer == expected) {
            logger.info { "$log ${durationExecution.inWholeMilliseconds} ms." }
        } else {
            logger.error { "$log Wrong answer: $answer, expected: $expected" }
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

