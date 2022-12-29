package org.carth.aoc22.day22

import org.carth.aoc22.Day22
import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class Day22Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day22PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day22(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(6032, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day22(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(165094, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day22PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day22(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(5031, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day22(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(95316, answer)
        }
    }
}