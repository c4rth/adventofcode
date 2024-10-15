package org.carth.aoc2023

import org.carth.common.Part
import org.carth.common.Puzzle


class Day07(private val input: String) : Puzzle<Long, Long>() {

    companion object {
        val partRanks = mapOf(
            Part.ONE to arrayOf('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'),
            Part.TWO to arrayOf('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A')
        )
    }

    private fun solve(part: Part): Long {
        val hands = input.split(System.lineSeparator()).map { line ->
            line.split(" ").let {
                Hand(it[0], it[1].toInt(), part)
            }
        }.toMutableList()
        hands.sort()
        return hands.map { it.bid.toLong() }.reduceIndexed { index, acc, l ->
            acc + (l * (index + 1))
        }
    }

    override fun solvePartOne(): Long {
        return solve(Part.ONE)
    }

    override fun solvePartTwo(): Long {
        return solve(Part.TWO)
    }

    class Hand(strCards: String, val bid: Int, part: Part) : Comparable<Hand> {
        private val cards: IntArray
        private val handType: IntArray

        init {
            val cardRanks = partRanks[part]!!
            cards = strCards.map { cardRanks.indexOf(it) + 1 }.toIntArray()
            val numOfJokers = cards.count { it == 1 }
            if (part == Part.ONE || numOfJokers == 0)
                handType = cards.toList().toHandType()
            else {
                if (numOfJokers == 5)
                    handType = intArrayOf(5)
                else {
                    val withJokers = cards.filter { it != 1 }.distinct()
                        .map { candidate -> cards.map { card -> if (card == 1) candidate else card }.toHandType() }
                        .sortedWith(compareBy({ it[0] }, { it.getOrNull(1) }))
                    handType = withJokers.last()
                }
            }
        }

        override fun compareTo(other: Hand): Int {
            if (handType[0] != other.handType[0]) {
                return handType[0] - other.handType[0]
            } else {
                if (handType.size > 1 && other.handType.size > 1 && handType[1] != other.handType[1]) {
                    return handType[1] - other.handType[1]
                } else {
                    cards.forEachIndexed { index, card ->
                        if (card != other.cards[index])
                            return card - other.cards[index]
                    }
                    return 0
                }
            }
        }
    }
}

fun List<Int>.toHandType() =
    this.groupBy { card -> card }.map { cardCount -> cardCount.value.size }.sortedDescending().toIntArray()