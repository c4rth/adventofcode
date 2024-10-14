package org.carth.aoc20

import org.carth.common.Puzzle

class Day23(input: String) : Puzzle<String, String>() {

    private var cups = input.map { c -> c - '0' }.toMutableList()
    private val size = cups.size - 1

    override fun solvePartOne(): String {
        var idx = 0
        repeat(10) {
            println("-- move ${it+1} --")
            println("cups: $cups")
            println("current: ${cups[idx]}")
            val picks = listOf(cups[(idx + 1) % size], cups[(idx + 2) % size], cups[(idx + 3) % size])
            println("picks: $picks")
            val work = cups.toMutableList()
            work.removeAll(picks)
            var dest = cups[idx] - 1
            while (dest in picks) {
                dest--
                if (dest == 0) dest = size
            }
            println("destination: $dest")
            val x = work.indexOf(dest)
            work.addAll((x + 1) % size, picks)
            idx = (idx + 1) % size
            cups = work
            println()
        }
        return cups.joinToString("")
    }

    override fun solvePartTwo(): String {
        return ""
    }
}
