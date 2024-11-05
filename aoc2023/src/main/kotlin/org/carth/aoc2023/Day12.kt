package org.carth.aoc2023

import org.carth.common.Puzzle


class Day12(input: String) : Puzzle<Long, Long>() {

    private val data = input.lines()

    override fun solvePartOne() = solve(1)

    override fun solvePartTwo() = solve(5)

    private fun solve(repeat: Int): Long {
        val records = data.map { line ->
            val splits = line.split(" ")
            val springs = splits[0]
            val damaged = splits[1].split(",").map { it.toInt() }

            val accSprings = Array(repeat) { springs }.joinToString("?")
            val accDamaged = buildList { repeat(repeat) { addAll(damaged) } }
            Record(accSprings, accDamaged)
        }
        return records.sumOf { record ->
            cache.clear()
            count(record.springs, record.damaged, 0)
        }
    }

    private val cache = hashMapOf<Triple<String, List<Int>, Int>, Long>()

    private fun count(springs: String, damaged: List<Int>, damagedLength: Int): Long {
        return cache.getOrPut(Triple(springs, damaged, damagedLength)) {
            if (springs.isEmpty())
                return if (damaged.isEmpty() || damaged.size == 1 && damagedLength == damaged[0]) 1 else 0
            val spring = springs.first()
            var res = 0L
            if (spring in ".?") {
                if (damagedLength > 0 && damaged[0] == damagedLength) {
                    res += count(springs.drop(1), damaged.drop(1), 0)
                } else if (damagedLength == 0) {
                    res += count(springs.drop(1), damaged, 0)
                }
            }
            if (spring in "#?") {
                if (damaged.isNotEmpty() && damagedLength < damaged[0]) {
                    res += count(springs.drop(1), damaged, damagedLength + 1)
                }
            }
            res
        }
    }

    data class Record(val springs: String, val damaged: List<Int>)
}