package org.carth.aoc20

import org.carth.common.Puzzle

class Day02(data: List<String>) : Puzzle<Int, Int>() {

    private val passwords = data.map { line ->
        val sp = line.split(" ")
        val range = sp[0].split("-").map { it.toInt() }
        val letter = sp[1][0]
        val pass = sp[2]
        Triple(letter, IntRange(range[0], range[1]), pass)
    }

    override fun solvePartOne(): Int {
        var total = 0
        passwords.forEach { (letter, range, pass) ->
            val count = pass.count { c -> c == letter }
            if (count in range) total += 1
        }
        return total
    }

    override fun solvePartTwo(): Int {
        var total = 0
        passwords.forEach { (letter, range, pass) ->
            if (pass[range.first - 1] == letter && pass[range.last - 1] != letter) total += 1
            if (pass[range.first - 1] != letter && pass[range.last - 1] == letter) total += 1
        }
        return total
    }
}