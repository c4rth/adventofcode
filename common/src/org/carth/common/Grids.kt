package org.carth.common

class GridString(input: String) {
    val lines = input.split(System.lineSeparator())
    val width = lines[0].length
    val height = lines.size

    operator fun get(p: Point2d) = lines[p.y][p.x]
    operator fun get(x: Int, y: Int) = lines[y][x]
    operator fun get(y: Int) = lines[y]

    operator fun get(char : Char): Point2d? {
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if ( c == char) return Point2d(x,y)
            }
        }
        return null
    }

    fun isInside(point: Point2d) = (point.x in 0 until width) && (point.y in 0 until height)

    fun adjacent(x: Int, y: Int) = adjacent(Point2d(x,y))

    fun adjacent(point: Point2d) = point.adjacent().filter { it.x >=0 && it.y >= 0 && it.x < width && it.y < height }
}
