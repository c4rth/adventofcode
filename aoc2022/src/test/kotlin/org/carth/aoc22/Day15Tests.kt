package org.carth.aoc22

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test

class Day15Tests : DayTests<Day15>(Day15::class) {

    @Test
    @Order(1)
    fun solvePartOneSample() {
        val answer = Day15(readInput(Type.TEST)).solvePartOne(10)
        assertEquals(26, answer)
    }


    @Test
    @Order(2)
    fun solvePartOne() {
        val answer = Day15(readInput(Type.INPUT)).solvePartOne(2_000_000)
        assertEquals(4876693, answer)
    }

    @Test
    @Order(3)
    fun solvePartTwoSample() {
        val answer = Day15(readInput(Type.TEST)).solvePartTwo(40)
        assertEquals(56000011, answer)
    }

    @Test
    @Order(4)
    fun solvePartTwo() {
        val answer = Day15(readInput(Type.INPUT)).solvePartTwo(4_000_000)
        assertEquals(11645454855041, answer)
    }

}