package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*


class Day25Tests : DayTests<Day25>(Day25::class) {
    @Test
    @Order(1)
    fun solvePartOneSample() = solve(Part.ONE, Type.TEST, expected = 58)

    @Test
    @Order(2)
    fun solvePartOne() = solve(Part.ONE, Type.INPUT, expected = 300)

}