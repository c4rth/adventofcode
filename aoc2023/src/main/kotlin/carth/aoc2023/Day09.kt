package org.carth.aoc2023

import org.carth.common.Puzzle

class Day09(input: String) : Puzzle<Long, Long>() {

    private val data = input.split(System.lineSeparator()).map { line ->
        line.split(" ").map { it.toLong() }
    }

    override fun solvePartOne(): Long {
        return data.sumOf { line ->
            val tails = ArrayList<Long>()
            var arr = line
            while (!arr.all { it == 0L }) {
                tails.add(arr.last())
                arr = arr.zipWithNext { a, b -> b - a }
            }
            tails.sum()
        }
    }

    override fun solvePartTwo(): Long {
        return data.sumOf { line ->
            val heads = ArrayList<Long>()
            var arr = line
            while (!arr.all { it == 0L }) {
                heads.add(arr.first())
                arr = arr.zipWithNext { a, b -> b - a }
            }
            heads.reversed().reduce { acc, head -> head - acc }
        }
    }
}