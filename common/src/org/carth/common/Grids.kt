package org.carth.common

interface Grid {
    fun isInside(point: Point2d): Boolean
    val width: Int
    val height: Int
    val heightIndices: IntRange
    val widthIndices: IntRange

}

@Suppress("unused")
class GridChar(val lines: List<CharArray>) : Grid {

    constructor(input: String) : this(input.lines().map { it.toCharArray() })

    override val width = lines[0].size
    override val height = lines.size

    operator fun get(p: Point2d) = lines[p.y][p.x]
    operator fun get(x: Int, y: Int) = lines[y][x]
    operator fun get(y: Int) = lines[y]
    operator fun set(p: Point2d, c: Char) {
        lines[p.y][p.x] = c
    }

    fun last() = lines[height - 1]

    operator fun set(x: Int, y: Int, c: Char) {
        lines[y][x] = c
    }

    operator fun get(char: Char): Point2d? {
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c == char) return Point2d(x, y)
            }
        }
        return null
    }

    override val heightIndices = lines.indices
    override val widthIndices = lines[0].indices

    override fun isInside(point: Point2d) = (point.x in 0 until width) && (point.y in 0 until height)

    fun adjacent(x: Int, y: Int) = adjacent(Point2d(x, y))

    fun cardinalAdjacent(point: Point2d) = point.cardinalAdjacent().filter { it.x >= 0 && it.y >= 0 && it.x < width && it.y < height }
    fun adjacent(point: Point2d) = point.adjacent().filter { it.x >= 0 && it.y >= 0 && it.x < width && it.y < height }

    fun rotate() = GridChar(List(lines.first().size) { i -> lines.joinToString("") { it[i].toString() }.toCharArray() })

    fun take(n: Int) = lines.take(n)
    fun drop(n: Int) = lines.drop(n)

    override fun toString(): String {
        return lines.joinToString(System.lineSeparator()) { line -> line.joinToString("") }
    }
}

@Suppress("unused")
class InfiniteGridChar(val lines: List<CharArray>) : Grid {

    constructor(input: String) : this(input.lines().map { it.toCharArray() })
    constructor(grid: GridChar) : this(grid.toString())

    override val width = lines[0].size
    override val height = lines.size

    operator fun get(p: Point2d) = lines[p.y][p.x]

    operator fun get(char: Char): Point2d? {
        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c == char) return Point2d(x, y)
            }
        }
        return null
    }

    override val heightIndices = lines.indices
    override val widthIndices = lines[0].indices

    override fun isInside(point: Point2d) = true

    fun cardinalAdjacent(point: Point2d) = point.cardinalAdjacent().map { p ->
        var x = p.x % width
        var y = p.y % height
        if (x < 0) x += width
        if (y < 0) y += height
        Point2d(x, y)
    }

    override fun toString(): String {
        return lines.joinToString(System.lineSeparator()) { line -> line.joinToString("") }
    }
}

@Suppress("unused")
class GridInt(val lines: List<IntArray>) : Grid {

    constructor(width: Int, height: Int) : this(Array(height) { IntArray(width) }.toList())

    override val width = lines[0].size
    override val height = lines.size

    override val heightIndices = lines.indices
    override val widthIndices = lines[0].indices

    operator fun get(p: Point2d) = lines[p.y][p.x]
    operator fun get(x: Int, y: Int) = lines[y][x]
    operator fun get(y: Int) = lines[y]
    operator fun set(p: Point2d, i: Int) {
        lines[p.y][p.x] = i
    }

    operator fun set(x: Int, y: Int, i: Int) {
        lines[y][x] = i
    }

    override fun isInside(point: Point2d) = (point.x in 0 until width) && (point.y in 0 until height)

}

@Suppress("unused")
class GridBoolean(val lines: List<BooleanArray>) : Grid {

    constructor(width: Int, height: Int) : this(Array(height) { BooleanArray(width) }.toList())

    override val width = lines[0].size
    override val height = lines.size

    override val heightIndices = lines.indices
    override val widthIndices = lines[0].indices

    operator fun get(p: Point2d) = lines[p.y][p.x]
    operator fun get(x: Int, y: Int) = lines[y][x]
    operator fun get(y: Int) = lines[y]
    operator fun set(p: Point2d, b: Boolean) {
        lines[p.y][p.x] = b
    }

    operator fun set(x: Int, y: Int, b: Boolean) {
        lines[y][x] = b
    }

    override fun isInside(point: Point2d) = (point.x in 0 until width) && (point.y in 0 until height)

}