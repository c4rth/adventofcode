package org.carth.common

import mu.KotlinLogging


enum class Part {
    ONE, TWO
}

abstract class Puzzle<T1, T2> {
    protected val logger = KotlinLogging.logger {}
    abstract fun solvePartOne(): T1
    abstract fun solvePartTwo(): T2
}