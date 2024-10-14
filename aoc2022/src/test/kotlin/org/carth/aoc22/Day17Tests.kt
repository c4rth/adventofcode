package org.carth.aoc22

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day17Tests : DayTests<Day17>(Day17::class) {
    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 3068L)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 3069L)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 1514285714288)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 1523167155404)
}