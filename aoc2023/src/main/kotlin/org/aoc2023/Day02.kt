package org.carth.aoc2023

import org.carth.common.Puzzle
import java.util.*

class Day02(private val data: String) : Puzzle<Int, Int>() {

    private val MAX_RED = 12
    private val MAX_GREEN = 13
    private val MAX_BLUE = 14

    override fun solvePartOne(): Int {
        var total = 0
        data.split(System.lineSeparator()).forEach { line ->
            parseGame(line).let { game ->
                game.hands.firstOrNull { hand ->
                    hand.getNumber(Color.RED) > MAX_RED || hand.getNumber(Color.GREEN) > MAX_GREEN || hand.getNumber(Color.BLUE) > MAX_BLUE
                } ?: run { total += game.id }
            }
        }
        return total
    }

    override fun solvePartTwo(): Int {
        return data.split(System.lineSeparator()).sumOf { line ->
            parseGame(line).let { game ->
                Color.entries.map { color ->
                    game.hands.maxOf { hand -> hand.getNumber(color) }
                }.reduce { acc, max -> acc * max }
            }
        }
    }

    class Game(val id: Int, val hands: List<Hand>)

    class Hand(private val number: Int, private val color: Color) {
        fun getNumber(c: Color) = if (color == c) number else 0
    }

    enum class Color {
        RED,
        GREEN,
        BLUE
    }

    private fun parseGame(line: String): Game {
        val hands = mutableListOf<Hand>()
        line.split("Game ", ": ").let { (_, iGame, iHands) ->
            iHands.split("; ", ", ").forEach { iColors ->
                iColors.split(" ").let { (iNum, iColor) ->
                    hands.add(Hand(iNum.toInt(), Color.valueOf(iColor.uppercase(Locale.getDefault()))))
                }
            }
            return Game(iGame.toInt(), hands)
        }
    }
}