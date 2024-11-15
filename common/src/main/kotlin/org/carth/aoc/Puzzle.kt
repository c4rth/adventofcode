package org.carth.aoc

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.time.measureTimedValue

abstract class Puzzle<T1, T2> {

    init {
        System.setProperty("slf4j.internal.verbosity", "WARN")
    }

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @Repeatable
    annotation class Sample(val suffix: String = "", val expected: String)

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @Repeatable
    annotation class Puzzle(val suffix: String = "", val expected: String)

    enum class Part(val text: String) {
        ONE("one"), TWO("two"), ALL("all")
    }

    enum class Type(val text: String) {
        SAMPLE("sample"), PUZZLE("puzzle")
    }

    protected val logger = KotlinLogging.logger {}

    fun solve(part: Part = Part.ALL) {
        val methods = when (part) {
            Part.ONE -> listOf("solvePart1")
            Part.TWO -> listOf("solvePart2")
            Part.ALL -> listOf("solvePart1", "solvePart2")
        }
        this::class.declaredMemberFunctions.filter { it.name in methods }.forEach { func ->
            func.annotations.filter { it is Sample || it is Puzzle }.forEach { ann ->
                when (ann) {
                    is Sample -> solveInternal(func, Type.SAMPLE, ann.suffix, ann.expected)
                    is Puzzle -> solveInternal(func, Type.PUZZLE, ann.suffix, ann.expected)
                }
            }
        }
    }

    private fun solveInternal(t: KFunction<*>, type: Type, suffix: String, expected: String) {
        val fileSuffix = if (suffix.isEmpty()) "" else "-$suffix"
        val filename = "${this.javaClass.simpleName.lowercase()}/${type.text}${fileSuffix}.txt"
        val data = Data(File(filename.toURI()).readText())
        val (answer, durationExecution) = measureTimedValue {
            t.call(this, data)
        }
        val log = "${type.text} / ${t.name} - file '$filename' :"
        if (answer.toString() == expected) {
            logger.info { "$log ${durationExecution.inWholeMilliseconds} ms." }
        } else {
            logger.error { "$log Wrong answer: $answer, expected: $expected" }
        }
    }

    abstract fun solvePart1(data: Data): T1

    abstract fun solvePart2(data: Data): T2

}

private fun String.toURI() = object {}.javaClass.classLoader.getResource(this)?.toURI()
    ?: throw IllegalArgumentException("Cannot find Resource: $this")

