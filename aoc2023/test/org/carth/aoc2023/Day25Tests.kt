package org.carth.aoc2023

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Order
import kotlin.test.Test

class Day25Tests : DayTests<Day25>(Day25::class) {
    @Test
    @Order(10)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 54)

    @Test
    @Order(20)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 545528)

    @Test
    @Order(30)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 0)

    @Test
    @Order(40)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 0)
}