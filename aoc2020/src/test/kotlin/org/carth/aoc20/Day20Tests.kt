package org.carth.aoc20

import org.carth.common.DayTests
import org.junit.jupiter.api.*

class Day20Tests : DayTests<Day20>(Day20::class) {
    @Test
    @Order(1)
    fun solvePartOneSample1() = solve(Part.ONE, Type.TEST, expected = 20899048083289L)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 20913499394191L)

    @Test
    @Order(3)
    fun solvePartTwoSample2() = solve(Part.TWO, Type.TEST, expected = 273)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 2209)
}