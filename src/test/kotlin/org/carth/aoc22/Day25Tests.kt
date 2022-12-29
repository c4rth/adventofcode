package org.carth.aoc22

import org.carth.common.DayTests
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

class Day25Tests : DayTests() {

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation::class)
    inner class Day25PartOneTest {

        @Test
        @Order(1)
        fun `solve part one of sample`() {
            // Act
            val answer = Day25(testInputAsListOfString()).solvePartOne()

            // Assert
            assertEquals("2=-1=0", answer)
        }

        @Test
        @Order(2)
        fun `solve part one`() {
            // Act
            val answer = Day25(inputAsListOfString()).solvePartOne()

            // Assert
            assertEquals("2-10==12-122-=1-1-22", answer)
        }
    }

}