package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*


class Day17Tests : DayTests<Day17>(Day17::class) {

    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 45)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 4560)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 112)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 3344)

}