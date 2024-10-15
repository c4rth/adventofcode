package org.carth.aoc2020

import org.carth.common.Direction
import org.carth.common.Point2d
import org.carth.common.Puzzle
import kotlin.math.sqrt

// https://github.com/tginsberg/advent-2020-kotlin/blob/main/src/main/kotlin/com/ginsberg/advent2020/Day20.kt

class Day20(input: String) : Puzzle<Long, Int>() {

    private val tiles: List<Tile> = parseInput(input)
    private val image: List<List<Tile>> = createImage()

    override fun solvePartOne(): Long {
        return image.first().first().id * image.first().last().id *
                image.last().first().id * image.last().last().id
    }

    override fun solvePartTwo(): Int {
        val seaMonsters = """
            ..................#.
            #....##....##....###
            .#..#..#..#..#..#...            
        """.trimIndent()
        val seaMonsterOffsets = seaMonsters.lines().mapIndexed { line, str ->
            str.mapIndexed { col, c -> if (c == '#') Point2d(line, col) else null }.filterNotNull()
        }.flatten()

        return imageToSingleTile()
            .orientations()
            .first { it.maskIfFound(seaMonsterOffsets) }
            .body
            .sumOf { row ->
                row.count { char -> char == '#' }
            }
    }

    private fun imageToSingleTile(): Tile {
        val rowsPerTile = tiles.first().body.size
        val body = image.flatMap { row ->
            (1 until rowsPerTile - 1).map { y ->
                row.joinToString("") { it.insetRow(y) }.toCharArray()
            }
        }.toTypedArray()
        return Tile(0, body)
    }

    private fun createImage(): List<List<Tile>> {
        val width = sqrt(tiles.count().toFloat()).toInt()
        var mostRecentTile: Tile = findTopCorner()
        var mostRecentRowHeader: Tile = mostRecentTile
        return (0 until width).map { row ->
            (0 until width).map { col ->
                when {
                    row == 0 && col == 0 -> mostRecentTile

                    col == 0 -> {
                        mostRecentRowHeader =
                            mostRecentRowHeader.findAndOrientNeighbor(Direction.SOUTH, Direction.NORTH, tiles)
                        mostRecentTile = mostRecentRowHeader
                        mostRecentRowHeader
                    }

                    else -> {
                        mostRecentTile =
                            mostRecentTile.findAndOrientNeighbor(Direction.EAST, Direction.WEST, tiles)
                        mostRecentTile
                    }
                }
            }
        }
    }

    private fun findTopCorner(): Tile =
        tiles.first { tile -> tile.sharedSideCount(tiles) == 2 }
            .orientations()
            .first {
                it.isSideShared(Direction.SOUTH, tiles) && it.isSideShared(Direction.EAST, tiles)
            }

    private class Tile(val id: Long, var body: Array<CharArray>) {

        private val sides: Set<String> = Direction.entries.map { sideFacing(it) }.toSet()
        private val sidesReversed = sides.map { it.reversed() }.toSet()

        override fun toString(): String = "Tile($id)"

        fun sharedSideCount(tiles: List<Tile>): Int =
            sides.sumOf { side ->
                tiles.filterNot { it.id == id }
                    .count { tile -> tile.hasSide(side) }
            }

        fun isSideShared(dir: Direction, tiles: List<Tile>): Boolean =
            tiles.filterNot { it.id == id }
                .any { tile -> tile.hasSide(sideFacing(dir)) }

        fun findAndOrientNeighbor(mySide: Direction, theirSide: Direction, tiles: List<Tile>): Tile {
            val mySideValue = sideFacing(mySide)
            return tiles.filterNot { it.id == id }
                .first { it.hasSide(mySideValue) }
                .also { it.orientToSide(mySideValue, theirSide) }
        }

        fun insetRow(row: Int): String =
            body[row].drop(1).dropLast(1).joinToString("")

        fun maskIfFound(mask: List<Point2d>): Boolean {
            var found = false
            val maxWidth = mask.maxByOrNull { it.y }!!.y
            val maxHeight = mask.maxByOrNull { it.x }!!.x
            (0..(body.size - maxHeight)).forEach { x ->
                (0..(body.size - maxWidth)).forEach { y ->
                    val lookingAt = Point2d(x, y)
                    val actualSpots = mask.map { it + lookingAt }
                    if (actualSpots.all { body[it.x][it.y] == '#' }) {
                        found = true
                        actualSpots.forEach { body[it.x][it.y] = '0' }
                    }
                }
            }
            return found
        }

        fun orientations(): Sequence<Tile> = sequence {
            repeat(2) {
                repeat(4) {
                    yield(this@Tile.rotateClockwise())
                }
                this@Tile.flip()
            }
        }

        private fun hasSide(side: String): Boolean =
            side in sides || side in sidesReversed

        private fun flip(): Tile {
            body = body.map { it.reversed().toCharArray() }.toTypedArray()
            return this
        }

        private fun rotateClockwise(): Tile {
            body = body.mapIndexed { x, row ->
                row.mapIndexed { y, _ ->
                    body[y][x]
                }.reversed().toCharArray()
            }.toTypedArray()
            return this
        }

        private fun sideFacing(dir: Direction): String =
            when (dir) {
                Direction.NORTH -> body.first().joinToString("")
                Direction.SOUTH -> body.last().joinToString("")
                Direction.WEST -> body.map { row -> row.first() }.joinToString("")
                Direction.EAST -> body.map { row -> row.last() }.joinToString("")
            }

        private fun orientToSide(side: String, direction: Direction) =
            orientations().first { it.sideFacing(direction) == side }

    }

    private fun parseInput(input: String): List<Tile> =
        input.split(System.lineSeparator() + System.lineSeparator()).map { it.lines() }.map { tileText ->
            val id = tileText.first().substringAfter(" ").substringBefore(":").toLong()
            val body = tileText.drop(1).map { it.toCharArray() }.toTypedArray()
            Tile(id, body)
        }

}
