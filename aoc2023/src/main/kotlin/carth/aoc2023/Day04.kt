package org.carth.aoc2023

import org.carth.common.Puzzle
import kotlin.math.pow

class Day04(input: String) : Puzzle<Int, Int>() {

    private fun parseNumbers(str: String) =
        str.replace("  ", " ").trim().split(" ").map { it.toInt() }.toSet()

    private val cards = input.split(System.lineSeparator()).map { line ->
        line.split("Card ", ": ", " | ").let { (_, idx, w, n) ->
            Card(idx.trim().toInt(), parseNumbers(w), parseNumbers(n))
        }
    }

    override fun solvePartOne(): Int {
        return cards.sumOf { card ->
            val matching = card.getMatchingNumber()
            if (matching > 0) {
                2.0.pow(matching - 1).toInt()
            } else {
                0
            }
        }
    }

    override fun solvePartTwo(): Int {
        val wonCards = Array(cards.size) { 1 }
        for (card in cards) {
            val matching = card.getMatchingNumber()
            (card.index..<card.index + matching).forEach { i ->
                wonCards[i] += wonCards[card.index - 1]
            }
        }
        return wonCards.sum()
    }

    class Card(val index: Int, private val winning: Set<Int>, private val numbers: Set<Int>) {
        fun getMatchingNumber() = winning.intersect(numbers).size
    }
}