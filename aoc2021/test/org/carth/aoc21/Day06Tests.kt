package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.Order
import kotlin.test.Test


class Day06Tests : DayTests<Day06>(Day06::class) {

    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 5934L)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 396210L)

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = 26984457539)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 1770823541496)
}