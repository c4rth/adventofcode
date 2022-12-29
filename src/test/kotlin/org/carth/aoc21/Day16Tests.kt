package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day16Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day16PartOneTest {

        @Test
        @Order(2)
        fun `solve part one of sample a`() {
            // Act
            val answer = Day16(testInputAsText("1a")).solvePartOne()

            // Assert
            assertEquals(6, answer)
        }

        @Test
        @Order(3)
        fun `solve part one of sample b`() {
            // Act
            val answer = Day16(testInputAsText("1b")).solvePartOne()

            // Assert
            assertEquals(16, answer)
        }

        @Test
        @Order(4)
        fun `solve part one of sample c`() {
            // Act
            val answer = Day16(testInputAsText("1c")).solvePartOne()

            // Assert
            assertEquals(12, answer)
        }

        @Test
        @Order(5)
        fun `solve part one of sample d`() {
            // Act
            val answer = Day16(testInputAsText("1d")).solvePartOne()

            // Assert
            assertEquals(23, answer)
        }

        @Test
        @Order(6)
        fun `solve part one of sample e`() {
            // Act
            val answer = Day16(testInputAsText("1e")).solvePartOne()

            // Assert
            assertEquals(31, answer)
        }

        @Test
        @Order(7)
        fun `solve part one`() {
            // Act
            val answer = Day16(inputAsText()).solvePartOne()

            // Assert
            assertEquals(991, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day16PartTwoTest {

        @Test
        @Order(2)
        fun `solve part one of sample a`() {
            // Act
            val answer = Day16(testInputAsText("2a")).solvePartTwo()

            // Assert
            assertEquals(3, answer)
        }

        @Test
        @Order(3)
        fun `solve part one of sample b`() {
            // Act
            val answer = Day16(testInputAsText("2b")).solvePartTwo()

            // Assert
            assertEquals(54, answer)
        }

        @Test
        @Order(4)
        fun `solve part one of sample c`() {
            // Act
            val answer = Day16(testInputAsText("2c")).solvePartTwo()

            // Assert
            assertEquals(7, answer)
        }

        @Test
        @Order(5)
        fun `solve part one of sample d`() {
            // Act
            val answer = Day16(testInputAsText("2d")).solvePartTwo()

            // Assert
            assertEquals(9, answer)
        }

        @Test
        @Order(6)
        fun `solve part one of sample e`() {
            // Act
            val answer = Day16(testInputAsText("2e")).solvePartTwo()

            // Assert
            assertEquals(1, answer)
        }

        @Test
        @Order(7)
        fun `solve part one of sample f`() {
            // Act
            val answer = Day16(testInputAsText("2f")).solvePartTwo()

            // Assert
            assertEquals(0, answer)
        }

        @Test
        @Order(8)
        fun `solve part one of sample g`() {
            // Act
            val answer = Day16(testInputAsText("2g")).solvePartTwo()

            // Assert
            assertEquals(0, answer)
        }

        @Test
        @Order(8)
        fun `solve part one of sample h`() {
            // Act
            val answer = Day16(testInputAsText("2h")).solvePartTwo()

            // Assert
            assertEquals(1, answer)
        }

        @Test
        @Order(9)
        fun `solve part one`() {
            // Act
            val answer = Day16(inputAsText()).solvePartTwo()

            // Assert
            assertEquals(1264485568252, answer)
        }
    }
}