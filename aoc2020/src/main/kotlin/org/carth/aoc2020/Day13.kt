package org.carth.aoc2020

import org.carth.common.Puzzle

class Day13(input: String) : Puzzle<Int, Long>() {
    private val data = input.lines()
    private val time = data[0].toInt()
    private val allBuses = data[1].split(",")

    override fun solvePartOne(): Int {
        val buses = allBuses.filter { it != "x" }.map { it.toInt() }
        var busId = 0
        var earliestTime = time
        while (busId == 0) {
            for (bus in buses) {
                if (earliestTime.mod(bus) == 0) {
                    busId = bus
                }
            }
            if (busId == 0) {
                earliestTime++
            }
        }
        return (earliestTime - time) * busId
    }

    override fun solvePartTwo(): Long {
        val buses = allBuses.mapIndexed { index, s -> (s.toLongOrNull() ?: 0) to index }.filter { (bus, _) -> bus != 0L }
        var step = buses.first().first
        var time = 0L
        buses.drop(1).forEach { (bus, index) ->
            while ((time + index).mod(bus) != 0L) {
                time += step
            }
            step *= bus
        }
        return time
    }

}
