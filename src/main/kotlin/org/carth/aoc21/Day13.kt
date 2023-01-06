package org.carth.aoc21

import org.carth.common.Point2d
import org.carth.common.Puzzle
import java.lang.Integer.max

typealias Grid = Array<BooleanArray>

class Day13(input: String) : Puzzle<Int, Int>() {
    private val data = input.lines()

    private fun readGridAndFolding(): Pair<Grid, List<Pair<String, Int>>> {
        val folding = mutableListOf<Pair<String, Int>>()
        val points = mutableListOf<Point2d>()
        var max = (0 to 0)
        data.forEach { line ->
            if (line.isNotEmpty()) {
                if (line.first().isDigit()) {
                    val (c, l) = line.split(",").map { it.toInt() }
                    max = (max(max.first, l) to max(max.second, c))
                    points.add(Point2d(l,c))
                } else {
                    val (axis, position) = line.split("=")
                    if (axis.endsWith("y"))
                        folding.add("y" to position.toInt())
                    else
                        folding.add("x" to position.toInt())
                }
            }
        }
        val grid = Array(max.first + max.first % 2 + 1) { BooleanArray(max.second + max.second % 2 + 1) }.also {
            points.forEach { (l, c) ->
                it[l][c] = true
            }
        }
        return grid to folding
    }

    private fun Grid.foldOnX(): Grid =
        Grid(this.size) { BooleanArray((this[0].size - 1) / 2) }.also {
            it.forEachIndexed { indexL, line ->
                line.forEachIndexed { indexC, _ ->
                    it[indexL][indexC] = this[indexL][indexC] || this[indexL][this[0].size - 1 - indexC]
                }
            }
        }

    private fun Grid.foldOnY(): Grid =
        Grid((this.size - 1) / 2) { BooleanArray(this[0].size) }.also {
            it.forEachIndexed { indexL, line ->
                line.forEachIndexed { indexC, _ ->
                    it[indexL][indexC] = this[indexL][indexC] || this[this.size - 1 - indexL][indexC]
                }
            }
        }

    private fun Grid.dump(): String {
        return StringBuilder().also { sb ->
            this.forEach { line ->
                line.forEach { b ->
                    sb.append(if (b) "#" else " ")
                }
                sb.appendLine()
            }
        }.toString()
    }

    override fun solvePartOne(): Int {
        var (grid, folding) = readGridAndFolding()
        folding.first().also { (axis, _) ->
            grid = if (axis == "x") {
                grid.foldOnX()
            } else {
                grid.foldOnY()
            }
        }
        return grid.sumOf { line -> line.count { it } }
    }

    override fun solvePartTwo(): Int {
        var (grid, folding) = readGridAndFolding()
        folding.forEach { (axis, _) ->
            grid = if (axis == "x") {
                grid.foldOnX()
            } else {
                grid.foldOnY()
            }
        }
        println(grid.dump())
        return 0
    }
}