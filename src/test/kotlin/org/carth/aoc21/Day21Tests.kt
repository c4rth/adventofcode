package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*


class Day21Tests : DayTests<Day21>(Day21::class) {

    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = "739785")

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = "598416")

    @Test
    @Order(3)
    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, expected = "444356092776315")

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = "27674034218179")

}