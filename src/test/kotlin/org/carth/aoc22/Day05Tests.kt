package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day05Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day05PartOneTest {
        @Test
        @Order(1)
        fun `solve part one of sample`() {

            // Act
            val answer = Day05(testInputAsText()).solvePartOne()

            // Assert
            assertEquals("CMZ", answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day05(inputAsText()).solvePartOne()

            // Assert
            assertEquals("QGTHFZBHV", answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day05PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day05(testInputAsText()).solvePartTwo()

            // Assert
            assertEquals("MCD", answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day05(inputAsText()).solvePartTwo()

            // Assert
            assertEquals("MGDMPSZTM", answer)
        }
    }
}