package org.carth.aoc21

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals


class Day24Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day24PartOneTest {

        @Test
        @Order(1)
        fun `solve part one`() {
            // Act
            val answer = Day24(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals(59_692_994_994_998, answer)
        }
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day24PartTwoTest {

        @Test
        @Order(1)
        fun `solve part two`() {
            // Act
            val answer = Day24(inputAsListOfString()).solvePartTwo()
            // Assert
            assertEquals(16_181_111_641_521, answer)
        }
    }
}