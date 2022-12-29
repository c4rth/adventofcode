package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class Day12Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day12PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day12(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(31, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day12(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(412, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day12PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day12(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(29, answer)
        }

        @Test
        @Order(4)
        fun `solve part two`() {
            // Act
            val answer = Day12(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(402, answer)
        }
    }
}