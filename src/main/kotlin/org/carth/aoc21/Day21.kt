package org.carth.aoc21

import org.carth.common.Puzzle
import java.lang.Long.max
import java.lang.Long.min


class Day21(input: String) : Puzzle<String, String>() {

    private val data = input.lines()

    object Constant {
        val diceRolls = mapOf(3 to 1, 4 to 3, 5 to 6, 6 to 7, 7 to 6, 8 to 3, 9 to 1)
    }

    private fun readPosition(str: String): Int =
        "Player (\\d+) starting position: (\\d+)".toRegex()
            .matchEntire(str)!!
            .destructured
            .component2()
            .toInt()

    override fun solvePartOne(): String {
        val player1 = Player(readPosition(data[0]))
        val player2 = Player(readPosition(data[1]))
        val dice = Dice()
        while (true) {
            player1.move(dice.roll())
            if (player1.score >= 1000) break
            player2.move(dice.roll())
            if (player2.score >= 1000) break

        }
        return (dice.rolled() * min(player1.score, player2.score)).toString()
    }

    // Not my solution
    override fun solvePartTwo(): String {
        val position1 = readPosition(data[0])
        val position2 = readPosition(data[1])
        val cache = HashMap<Int, Pair<Long, Long>>().withDefault { 0L to 0L }
        val (winPlayer1, winPlayer2) = solve(cache, position1, 0, position2, 0)
        return max(winPlayer1, winPlayer2).toString()
    }


    private fun solve(
        cache: MutableMap<Int, Pair<Long, Long>>,
        position1: Int,
        score1: Int,
        position2: Int,
        score2: Int
    ): Pair<Long, Long> {
        val hash = position1 + 10 * score1 + 1000 * position2 + 10000 * score2
        if (!cache.containsKey(hash)) {
            var winPlayer1 = 0L
            var winPlayer2 = 0L
            for ((dice, mult) in Constant.diceRolls) {
                val play = (position1 + dice - 1) % 10 + 1
                if (score1 + play < 21) {
                    val (winPlayer2b, winPlayer1b) = solve(cache, position2, score2, play, score1 + play)
                    winPlayer1 += mult * winPlayer1b
                    winPlayer2 += mult * winPlayer2b
                } else {
                    winPlayer1 += mult
                }
            }
            cache[hash] = winPlayer1 to winPlayer2
        }
        return cache[hash]!!
    }


    class Dice {
        private var value = 1
        private var roll = 0

        fun roll(): Int {
            val sum = next() + next() + next()
            roll += 3
            return sum
        }

        private fun next(): Int {
            value += 1
            if (value > 100) {
                value = 1
                return 100
            }
            return value - 1
        }

        fun rolled(): Int = roll
    }

    class Player(initPosition: Int, initScore: Long = 0L) {
        private var _position = initPosition
        private var _score = initScore

        fun move(move: Int) {
            _position += move
            while (_position > 10) _position -= 10
            _score += _position
        }

        val score: Long
            get() = _score

    }
}