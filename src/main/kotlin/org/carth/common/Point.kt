package org.carth.common

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Point(val x: Int, val y: Int) {
    operator fun plus(p: Point) = Point(x + p.x, y + p.y)
    operator fun minus(p: Point) = Point(x - p.x, y - p.y)
    fun isInside(width: Int, height: Int): Boolean =
        (x in 0 until width) && (y in 0 until height)

    fun manhattan(other: Point) = abs(x - other.x) + abs(y - other.y)

    fun adjacents() = setOf(this + N, this + NE, this + NW, this + S, this + SE, this + SW, this + W, this + E)

    fun min(other: Point) = Point(min(this.x, other.x), min(this.y, other.y))
    fun max(other: Point) = Point(max(this.x, other.x), max(this.y, other.y))

    companion object {
        val ZERO = Point(0, 0)
        val LEFT = Point(-1, 0)
        val RIGHT = Point(1, 0)
        val UP = Point(0, -1)
        val DOWN = Point(0, 1)

        val N = Point(0, -1)
        val NE = Point(1, -1)
        val NW = Point(-1, -1)
        val S = Point(0, 1)
        val SE = Point(1, 1)
        val SW = Point(-1, 1)
        val W = Point(-1, 0)
        val E = Point(1, 0)
    }
}

data class Point3d(val x: Int, val y: Int, val z: Int) {
    fun adjacents() = listOf(
        Point3d(x + 1, y, z),
        Point3d(x - 1, y, z),
        Point3d(x, y + 1, z),
        Point3d(x, y - 1, z),
        Point3d(x, y, z + 1),
        Point3d(x, y, z - 1)
    )
}