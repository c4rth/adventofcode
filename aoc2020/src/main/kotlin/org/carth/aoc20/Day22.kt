package org.carth.aoc20

import org.carth.common.Puzzle
import java.util.Objects

class Day22(input: String) : Puzzle<Int, Int>() {

    private val players = input.split(System.lineSeparator() + System.lineSeparator())
        .map { lines -> Player(lines.lines().drop(1).map { it.toInt() }.toMutableList()) }

    override fun solvePartOne(): Int {
        val (player1, player2) = players
        while (player1.hasCards() && player2.hasCards()) {
            val card1 = player1.pickCard()
            val card2 = player2.pickCard()
            if (card1 > card2) player1.addCards(card1, card2) else player2.addCards(card2, card1)
        }
        return if (player1.hasCards()) player1.score() else player2.score()
    }

    override fun solvePartTwo(): Int {
        val (player1, player2) = players
        play(player1, player2)
        return if (player1.hasCards()) player1.score() else player2.score()
    }

    private fun play(player1: Player, player2: Player): Boolean {
        val history = mutableSetOf(Objects.hash(player1.cards, player2.cards))
        while (player1.hasCards() && player2.hasCards()) {
            val card1 = player1.pickCard()
            val card2 = player2.pickCard()
            val winner1 = if (player1.machSize(card1) && player2.machSize(card2)) {
                play(Player(player1.getCards(card1)), Player(player2.getCards(card2)))
            } else {
                card1 > card2
            }
            if (winner1) player1.addCards(card1, card2) else player2.addCards(card2, card1)
            val hash = Objects.hash(player1.cards, player2.cards)
            if (hash in history) return true
            history.add(hash)
        }
        return player1.hasCards()
    }

    class Player(val cards: MutableList<Int>) {

        fun hasCards() = cards.isNotEmpty()

        fun pickCard() = cards.removeFirst()

        fun addCards(vararg card: Int) {
            cards.addAll(card.toList())
        }

        fun getCards(number: Int) = cards.take(number).toMutableList()

        fun machSize(card: Int) = card <= cards.size

        fun score(): Int = cards.foldIndexed(0) { index, acc, card -> acc + (card * (cards.size - index)) }
    }
}
