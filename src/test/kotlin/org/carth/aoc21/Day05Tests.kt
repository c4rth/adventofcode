package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*


class Day05Tests : DayTests<Day05>(Day05::class) {

    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 5)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 7473)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 12)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 24164)
}