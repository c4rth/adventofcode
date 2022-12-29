package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day12Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day12PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample 1`() {
            // Act
            val answer = Day12(testInputAsListOfString("a")).solvePartOne()

            // Assert
            assertEquals(10, answer)
        }

        @Test
        @Order(2)
        fun `solve part one of sample 2`() {
            // Act
            val answer = Day12(testInputAsListOfString( "b")).solvePartOne()

            // Assert
            assertEquals(19, answer)
        }

        @Test
        @Order(3)
        fun `solve part one of sample 3`() {
            // Act
            val answer = Day12(testInputAsListOfString( "c")).solvePartOne()

            // Assert
            assertEquals(226, answer)
        }

        @Test
        @Order(4)
        fun `solve part one`() {
            // Act
            val answer = Day12(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(4749, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day12PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample 1`() {
            // Act
            val answer = Day12(testInputAsListOfString( "a")).solvePartTwo()

            // Assert
            assertEquals(36, answer)
        }

        @Test
        @Order(2)
        fun `solve part two of sample 2`() {
            // Act
            val answer = Day12(testInputAsListOfString( "b")).solvePartTwo()

            // Assert
            assertEquals(103, answer)
        }

        @Test
        @Order(3)
        fun `solve part two of sample 3`() {
            // Act
            val answer = Day12(testInputAsListOfString( "C")).solvePartTwo()

            // Assert
            assertEquals(3509, answer)
        }

        @Test
        @Order(4)
        fun `solve part two`() {
            // Act
            val answer = Day12(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(123054, answer)
        }
    }
}