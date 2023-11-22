package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test


class Day23Tests : DayTests<Day23>(Day23::class) {

    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, "1", expected = 12521L)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, "1", expected = 11536L)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, "2", expected = 44169L)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, "2", expected = 55136L)
}