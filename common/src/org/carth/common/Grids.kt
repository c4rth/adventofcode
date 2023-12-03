package org.carth.common

class GridString(private val input: String) {
    val data = input.split(System.lineSeparator())
    val lines = data
    val width = data[0].length
    val height = data.size

    operator fun get(p: Point2d) = data[p.y][p.x]
    operator fun get(x: Int, y: Int) = data[y][x]
    operator fun get(y: Int) = data[y]

    fun adjacent(x: Int, y: Int) = adjacent(Point2d(x,y))

    fun adjacent(point: Point2d) = point.adjacent().filter { it.x >=0 && it.y >= 0 && it.x < width && it.y < height }
}
