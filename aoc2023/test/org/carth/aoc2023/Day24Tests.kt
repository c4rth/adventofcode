package org.carth.aoc2023

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Order
import java.math.BigInteger
import kotlin.test.Test

class Day24Tests : DayTests<Day24>(Day24::class) {

    private val rangeTest = 7.0..27.0
    private val range = 200_000_000_000_000.0 .. 400_000_000_000_000.0

    @Test
    @Order(10)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, args = listOf(rangeTest), expected = 2L)

    @Test
    @Order(20)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, args = listOf(range), expected = 16172L)

    @Test
    @Order(30)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 47.toBigInteger())

    @Test
    @Order(40)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = "14412119159945623979".toBigInteger())
}