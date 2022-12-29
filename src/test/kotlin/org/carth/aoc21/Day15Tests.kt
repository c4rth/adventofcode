package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day15Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day15PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day15(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(40, answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day15(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(398, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day15PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two of sample`() {
            // Act
            val answer = Day15(testInputAsListOfString()).solvePartTwo()

            // Assert
            assertEquals(315, answer)
        }

        @Test
        @Order(2)
        fun `solve part two`() {
            // Act
            val answer = Day15(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(2817, answer)
        }
    }

}