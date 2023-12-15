package org.carth.common

@Suppress("unused")
class GridString(val lines: List<String>) {

    constructor(input: String): this(input.lines())

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

    fun rotate() = GridString(List(lines.first().length) { i -> lines.joinToString("") { it[i].toString() } })

    fun take(n: Int) = lines.take(n)
    fun drop(n: Int) = lines.drop(n)
}

@Suppress("unused")
class MutableGridString(val lines: MutableList<CharArray>) {

    constructor(input: String): this(input.lines().map { it.toCharArray() }.toMutableList())

    val width = lines[0].size
    val height = lines.size
    val size = width to height

    operator fun get(p: Point2d) = lines[p.y][p.x]
    operator fun get(x: Int, y: Int) = lines[y][x]
    operator fun get(y: Int) = lines[y]
    operator fun set(p:Point2d, c: Char) { lines[p.y][p.x] = c }
    operator fun set(x: Int, y: Int, c: Char) { lines[y][x] = c }

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

    fun rotate() = GridString(List(lines.first().size) { i -> lines.joinToString("") { it[i].toString() } })

    fun take(n: Int) = lines.take(n)
    fun drop(n: Int) = lines.drop(n)

    override fun toString() : String {
        return lines.joinToString(System.lineSeparator()) { line -> line.joinToString("") }
    }
}