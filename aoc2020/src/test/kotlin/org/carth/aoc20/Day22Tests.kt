package org.carth.aoc20

import org.carth.common.DayTests
import org.junit.jupiter.api.*

class Day22Tests : DayTests<Day22>(Day22::class) {
    @Test
    @Order(1)
    fun solvePartOneSample1() = solve(Part.ONE, Type.TEST, expected = 306)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 30138)

    @Test
    @Order(3)
    fun solvePartTwoSample2() = solve(Part.TWO, Type.TEST, expected = 291)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 0)
}