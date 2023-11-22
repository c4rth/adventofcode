package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day11Tests : DayTests<Day11>(Day11::class) {
    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 10605L)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 64032L)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 2713310158)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 12729522272)
}