package org.carth.aoc22

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
            val answer = Day20(testInputAsListOfInt()).solvePartOne()

            // Assert
            assertEquals(3, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day20(inputAsListOfInt()).solvePartOne()

            // Assert
            assertEquals(16_533, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day20PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day20(testInputAsListOfInt()).solvePartTwo()

            // Assert
            assertEquals(1_623_178_306, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day20(inputAsListOfInt()).solvePartTwo()
            // Assert
            assertEquals(4_789_999_181_006, answer)
        }
    }
}