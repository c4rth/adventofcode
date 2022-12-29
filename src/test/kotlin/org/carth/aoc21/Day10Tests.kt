package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day10Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day10PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day10(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(26397, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day10(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(462693, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day10PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day10(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(288957, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day10(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(3094671161, answer)
        }
    }
}