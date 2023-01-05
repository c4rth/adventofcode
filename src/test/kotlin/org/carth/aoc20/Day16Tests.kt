package org.carth.aoc20

import org.carth.common.DayTests
import org.junit.jupiter.api.*

class Day16Tests : DayTests<Day16>(Day16::class) {
    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, "1", expected = 71)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 22057)

//    @Test
//    @Order(3)
//    fun solvePartTwoSample() = solve(Part.TWO, Type.TEST, "2", expected = 0)

    @Test
    @Order(4)
    fun solvePartTwo() = solve(Part.TWO, Type.INPUT, expected = 1093427331937L)
}