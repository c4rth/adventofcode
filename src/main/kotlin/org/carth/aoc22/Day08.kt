package org.carth.aoc22

import org.carth.common.Puzzle
import java.lang.Integer.max

class Day08(data: List<String>) : Puzzle<Int, Int>() {

    private val trees = data.map { line ->
        line.toCharArray().map { c -> c.digitToInt() }
    }
    private val width = data.first().length
    private val height = data.size

    private fun isVisible(tree: Int, rows: IntProgression, cols: IntProgression): Boolean {
        rows.forEach { row ->
            cols.forEach { col ->
                if (tree <= trees[row][col]) return false
            }
        }
        return true
    }

    override fun solvePartOne(): Int {
        var total = width * 2 + height * 2 - 4
        (1 until height - 1).forEach { row ->
            (1 until width - 1).forEach { col ->
                val tree = trees[row][col]
                if (isVisible(tree, row + 1 until height, col..col) ||
                    isVisible(tree, 0 until row, col..col) ||
                    isVisible(tree, row..row, 0 until col) ||
                    isVisible(tree, row..row, col + 1 until width)
                )
                    total++
            }
        }
        return total
    }

    private fun scenicScore(tree: Int, lRange: IntProgression, cRange: IntProgression): Int {
        var score = 0
        lRange.forEach { l ->
            cRange.forEach { c ->
                score += 1
                if (tree <= trees[l][c]) return score
            }
        }
        return score
    }

    override fun solvePartTwo(): Int {
        var highestScore = 0
        (1 until height - 1).forEach { l ->
            (1 until width - 1).forEach { c ->
                val tree = trees[l][c]
                val score = scenicScore(tree, l + 1 until height, c..c) *
                        scenicScore(tree, l - 1 downTo 0, c..c) *
                        scenicScore(tree, l..l, c + 1 until width) *
                        scenicScore(tree, l..l, c - 1 downTo 0)
                highestScore = max(highestScore, score)
            }
        }
        return highestScore
    }

}