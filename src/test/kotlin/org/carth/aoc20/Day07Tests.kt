package org.carth.aoc20

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day07Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day07PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day07(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(4, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day07(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(115, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day07PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample 1`() {
            // Act
            val answer = Day07(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(32, answer)
        }

        @Test
        @Order(2)
        fun `solve part two of sample 2`() {
            // Act
            val answer = Day07(testInputAsListOfString("2")).solvePartTwo()

            // Assert
            assertEquals(126, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day07(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(1250, answer)
        }
    }
}