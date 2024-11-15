package org.carth.aoc

import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.*
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.time.measureTimedValue

abstract class Puzzle {

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

    enum class Type(val text: String) {
        SAMPLE("sample"), PUZZLE("puzzle")
    }

    protected val logger = KotlinLogging.logger {}

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
        val data = Data.read(this.javaClass.simpleName.lowercase(), type.text, suffix)
        val (answer, durationExecution) = measureTimedValue {
            t.call(this, data)
        }
        val log = "${type.text} / ${t.name} - file '${data.filename}' :"
        if (Objects.equals(answer.toString(), expected)) {
            logger.info { "$log ${durationExecution.inWholeMilliseconds} ms." }
        } else {
            logger.error { "$log Wrong answer: $answer, expected: $expected" }
        }
    }

    abstract fun solvePart1(data: Data): Any

    abstract fun solvePart2(data: Data): Any
}



