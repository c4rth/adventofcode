package org.carth.aoc20

import org.carth.common.DayTests
import org.junit.jupiter.api.*

class Day04Tests : DayTests<Day04>(Day04::class) {

    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 2)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 245)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 2)

    @Test
    @Order(4)
    fun solvePartTwoSample2() = solve(Part.TWO, Type.TEST, "1", expected = 4)

    @Test
    @Order(5)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 133)
}