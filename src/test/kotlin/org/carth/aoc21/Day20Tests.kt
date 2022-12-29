package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day20Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day20PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day20(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(35, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day20(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(5597, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day20PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day20(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(3351, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day20(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(18723, answer)
        }
    }
}