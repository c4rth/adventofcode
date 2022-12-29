package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class Day06Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day06PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer1 = Day06(testInputAsText("1")).solvePartOne()
            val answer2 = Day06(testInputAsText("2")).solvePartOne()
            val answer3 = Day06(testInputAsText("3")).solvePartOne()
            val answer4 = Day06(testInputAsText("4")).solvePartOne()
            val answer5 = Day06(testInputAsText("5")).solvePartOne()

            // Assert
            assertEquals(7, answer1)
            assertEquals(5, answer2)
            assertEquals(6, answer3)
            assertEquals(10, answer4)
            assertEquals(11, answer5)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day06(inputAsText()).solvePartOne()

            // Assert
            assertEquals(1658, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day06PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer1 = Day06(testInputAsText("1")).solvePartTwo()
            val answer2 = Day06(testInputAsText("2")).solvePartTwo()
            val answer3 = Day06(testInputAsText("3")).solvePartTwo()
            val answer4 = Day06(testInputAsText("4")).solvePartTwo()
            val answer5 = Day06(testInputAsText("5")).solvePartTwo()

            // Assert
            assertEquals(19, answer1)
            assertEquals(23, answer2)
            assertEquals(23, answer3)
            assertEquals(29, answer4)
            assertEquals(26, answer5)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day06(inputAsText()).solvePartTwo()
            // Assert
            assertEquals(2260, answer)
        }
    }
}