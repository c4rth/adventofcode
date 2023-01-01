package org.carth.aoc20

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
            val answer = Day06(testInputAsText()).solvePartOne()

            // Assert
            assertEquals(11, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day06(inputAsText()).solvePartOne()

            // Assert
            assertEquals(6310, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day06PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day06(testInputAsText()).solvePartTwo()

            // Assert
            assertEquals(6, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day06(inputAsText()).solvePartTwo()
            // Assert
            assertEquals(3193, answer)
        }
    }
 }