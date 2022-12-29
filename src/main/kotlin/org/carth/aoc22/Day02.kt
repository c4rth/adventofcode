package org.carth.aoc22

import org.carth.common.Puzzle

class Day02(private val data: List<String>) : Puzzle<Int, Int>() {

    override fun solvePartOne(): Int {
        val scores = mapOf(
            "X" to mapOf("A" to 3, "B" to 0, "C" to 6),
            "Y" to mapOf("A" to 6, "B" to 3, "C" to 0),
            "Z" to mapOf("A" to 0, "B" to 6, "C" to 3)
        )
        val values = mapOf(
            "X" to 1, "Y" to 2, "Z" to 3
        )
        return getRounds().sumOf { round ->
            values[round.player2]!! + scores[round.player2]!![round.player1]!!
        }
    }

    override fun solvePartTwo(): Int {
        val values = mapOf(
            "X" to mapOf("A" to 3, "B" to 1, "C" to 2),
            "Y" to mapOf("A" to 1, "B" to 2, "C" to 3),
            "Z" to mapOf("A" to 2, "B" to 3, "C" to 1)
        )
        val scores = mapOf(
            "X" to 0, "Y" to 3, "Z" to 6
        )
        return getRounds().sumOf { round ->
            values[round.player2]!![round.player1]!! + scores[round.player2]!!
        }
    }

    private fun getRounds(): List<Round> =
        data.map { line ->
            val split = line.trim().split(" ")
            Round(split[0], split[1])
        }

    class Round(val player1: String, val player2: String)

}

