package org.carth.common

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.File
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.time.measureTimedValue

abstract class Puzzle2() {

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @Repeatable
    annotation class Sample(val suffix: String = "", val expected: String)

    @Target(AnnotationTarget.FUNCTION)
    @Retention(AnnotationRetention.RUNTIME)
    @Repeatable
    annotation class Puzzle(val suffix: String = "", val expected: String)

    enum class Type(val text: String) {
        SAMPLE("sample"), PUZZLE("puzzle")
    }

    protected val logger = KotlinLogging.logger {}

    protected lateinit var input: String

    fun solve() {
        this::class.declaredMemberFunctions.filter { it.name in listOf("solvePart1", "solvePart2") }.forEach { func ->
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
        val filename = "${this.javaClass.simpleName}/${type.text}${fileSuffix}.txt"
        this.input = File(filename.toURI()).readText()
        val (answer, durationExecution) = measureTimedValue {
            t.call(this)
        }
        val log = "${type.text} / ${t.name} - file '$filename' :"
        if (answer == expected) {
            logger.info { "$log ${durationExecution.inWholeMilliseconds} ms." }
        } else {
            logger.error { "$log Wrong answer: $answer, expected: $expected" }
        }
    }

    abstract fun solvePart1(): String
    abstract fun solvePart2(): String
}

private fun String.toURI() = object {}.javaClass.classLoader.getResource(this)?.toURI()
    ?: throw IllegalArgumentException("Cannot find Resource: $this")

