package org.carth.aoc20

import org.carth.common.DayTests
import org.carth.common.Part
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test


class Day09Tests : DayTests<Day09>(Day09::class) {
    @Test
    @Order(1)
    fun solvePartOneSample() {
        val answer = Day09(readInput(Type.TEST)).solvePartOne(5)
        assertEquals(127, answer)
    }


    @Test
    @Order(2)
    fun solvePartOne() {
        val answer = Day09(readInput(Type.INPUT)).solvePartOne(25)
        assertEquals(1038347917, answer)
    }

    @Test
    @Order(3)
    fun solvePartTwoSample() {
        val answer = Day09(readInput(Type.TEST)).solvePartTwo(5)
        assertEquals(62, answer)
    }

    @Test
    @Order(4)
    fun solvePartTwo() {
        val answer = Day09(readInput(Type.INPUT)).solvePartTwo(25)
        assertEquals(137394018, answer)
    }
}