package org.carth.aoc22

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
            val answer = Day17(testInputAsText()).solvePartOne()

            // Assert
            assertEquals(3068, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day17(inputAsText()).solvePartOne()

            // Assert
            assertEquals(3069, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day17PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day17(testInputAsText()).solvePartTwo()

            // Assert
            assertEquals(1_514_285_714_288L, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day17(inputAsText()).solvePartTwo()
            // Assert
            assertEquals(1_523_167_155_404L, answer)
        }
    }
}