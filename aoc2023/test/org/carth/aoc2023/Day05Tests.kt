package org.carth.aoc2023

import org.carth.common.DayTests
import org.junit.jupiter.api.Order
import kotlin.test.Test

class Day05Tests : DayTests<Day05>(Day05::class) {
    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 35L)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 199602917L)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 46L)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 2254686L)
}