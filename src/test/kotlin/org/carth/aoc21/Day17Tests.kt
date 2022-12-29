package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day17Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day17PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day17(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(45, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day17(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(4560, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day17PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day17(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(112, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day17(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(3344, answer)
        }
    }
}