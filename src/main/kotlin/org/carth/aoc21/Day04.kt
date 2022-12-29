package org.carth.aoc21

import org.carth.common.Puzzle

class Day04(private val data: List<String>) : Puzzle<Int, Int>() {

    private fun getGrids(): List<Grid> =
        data.drop(1).chunked(6).map { list ->
            Grid(list.takeLast(5))
        }

    override fun solvePartOne(): Int {
        val draws = data.first().split(",").map { s: String -> s.toInt() }
        val grids = getGrids()
        var winner: Grid? = null
        var indexDraw = 0
        for (index in draws.indices) {
            grids.forEach { grid ->
                grid.checkNumber(draws[index])
            }
            winner = grids.firstOrNull { card -> card.isWinner() }
            if (winner != null) {
                indexDraw = index
                break
            }
        }
        if (null != winner) {
            return draws[indexDraw] * winner.sumOfNonChecked
        }
        return 0
    }

    override fun solvePartTwo(): Int {
        val draws = data.first().split(",").map { s: String -> s.toInt() }
        val grids = getGrids()
        var numberOfWinners = 0
        var indexDraw = 0
        for (index in draws.indices) {
            grids.forEach {
                it.checkNumber(draws[index])
            }
            numberOfWinners = grids.filter { card ->
                card.isWinnerWithDrawIndex(index)
            }.size
            if (numberOfWinners == grids.size) {
                indexDraw = index
                break
            }
        }
        if (numberOfWinners == grids.size) {
            val lastWinner = grids.first { card ->
                card.indexWinnerDraw == indexDraw
            }
            return draws[indexDraw] * lastWinner.sumOfNonChecked
        }
        return 0
    }



    class Grid(gridNumbers: List<String>) {

        private val gridSize = 5

        private val numbers: Array<IntArray> = Array(gridSize) { IntArray(gridSize) { 0 } }
        private val checked: Array<BooleanArray> = Array(gridSize) { BooleanArray(gridSize) { false } }

        init {
            initGrid(gridNumbers)
        }

        private var _indexWinnerDraw = -1
        var indexWinnerDraw
            set(value) {
                if (_indexWinnerDraw == -1) _indexWinnerDraw = value
            }
            get() = _indexWinnerDraw

        private fun initGrid(numbersAsStrings: List<String>) {
            numbersAsStrings.forEachIndexed { indexL, line ->
                line.trim().split(Regex("\\s+")).forEachIndexed { indexC, s ->
                    numbers[indexL][indexC] = s.toInt()
                }
            }
        }

        fun checkNumber(number: Int) {
            for (indexL in 0 until gridSize) {
                for (indexC in 0 until gridSize) {
                    if (numbers[indexL][indexC] == number) {
                        checked[indexL][indexC] = true
                    }
                }
            }
        }

        private fun isBingo(row: BooleanArray): Boolean = row.toList().filter { it }.size == row.size

        fun isWinner(): Boolean = isWinnerWithDrawIndex(-1)

        fun isWinnerWithDrawIndex(indexDraw: Int = -1): Boolean {
            // check rows
            for (indexL in 0 until gridSize) {
                if (isBingo(checked[indexL])) {
                    indexWinnerDraw = indexDraw
                    return true
                }
            }
            // check columns
            for (indexC in 0 until gridSize) {
                val column = BooleanArray(gridSize)
                for (indexL in 0 until gridSize) {
                    column[indexL] = checked[indexL][indexC]
                }
                if (isBingo(column)) {
                    indexWinnerDraw = indexDraw
                    return true
                }
            }
            return false
        }

        val sumOfNonChecked: Int
            get() {
                var sum = 0
                for (x in 0 until gridSize) {
                    for (y in 0 until gridSize) {
                        if (!checked[x][y]) sum += numbers[x][y]
                    }
                }
                return sum
            }

        override fun toString(): String {
            val sb = StringBuilder()
            sb.append("index: $indexWinnerDraw\n")
            for (x in 0 until gridSize) {
                for (y in 0 until gridSize) {
                    sb.append(String.format("%2d", numbers[x][y]))
                    if (checked[x][y]) sb.append("*") else sb.append(" ")
                    sb.append(" ")
                }
                sb.append("\n")
            }
            return sb.toString().trim()
        }

    }
}