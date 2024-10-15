package org.carth.common

import io.github.oshai.kotlinlogging.KotlinLogging


abstract class Puzzle<T1, T2> {

    enum class Part(val text: String) {
        ONE("one"), TWO("two")
    }

    protected val logger = KotlinLogging.logger {}
    var args = emptyList<Any>()
    abstract fun solvePartOne(): T1
    abstract fun solvePartTwo(): T2
}