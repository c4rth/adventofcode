package org.carth.common

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

interface Point<T> {

    fun adjacent(): Set<T>
}

@Suppress("unused")
data class Point2d(val x: Int, val y: Int) : Point<Point2d> {
    operator fun plus(p: Point2d) = Point2d(x + p.x, y + p.y)
    operator fun minus(p: Point2d) = Point2d(x - p.x, y - p.y)
    operator fun times(n: Int) = Point2d(x * n, y * n)
    fun isInside(width: Int, height: Int): Boolean =
        (x in 0 until width) && (y in 0 until height)

    fun isInside(grid: Grid): Boolean = (x in grid.widthIndices) && (y in grid.heightIndices)

    fun manhattan(other: Point2d) = abs(x - other.x) + abs(y - other.y)

    override fun adjacent() = setOf(this + N, this + NE, this + NW, this + S, this + SE, this + SW, this + W, this + E)

    fun cardinalAdjacent() = setOf(this + N, this + S, this + W, this + E)

    fun min(other: Point2d) = Point2d(min(this.x, other.x), min(this.y, other.y))
    fun max(other: Point2d) = Point2d(max(this.x, other.x), max(this.y, other.y))

    companion object {
        val ZERO = Point2d(0, 0)
        val LEFT = Point2d(-1, 0)
        val RIGHT = Point2d(1, 0)
        val UP = Point2d(0, -1)
        val DOWN = Point2d(0, 1)

        val N = Point2d(0, -1)
        val NE = Point2d(1, -1)
        val NW = Point2d(-1, -1)
        val S = Point2d(0, 1)
        val SE = Point2d(1, 1)
        val SW = Point2d(-1, 1)
        val W = Point2d(-1, 0)
        val E = Point2d(1, 0)
    }
}

data class Point3d(val x: Int, val y: Int, val z: Int) : Point<Point3d>, Comparable<Point3d> {

    constructor(xyz: List<String>): this(xyz[0].toInt(), xyz[1].toInt(), xyz[2].toInt())

    fun directAdjacent() = listOf(
        Point3d(x + 1, y, z),
        Point3d(x - 1, y, z),
        Point3d(x, y + 1, z),
        Point3d(x, y - 1, z),
        Point3d(x, y, z + 1),
        Point3d(x, y, z - 1)
    )

    override fun adjacent() =
        (x - 1..x + 1).flatMap { dx ->
            (y - 1..y + 1).flatMap { dy ->
                (z - 1..z + 1).mapNotNull { dz ->
                    Point3d(dx, dy, dz).takeUnless { it == this }
                }
            }
        }.toSet()


    operator fun plus(other: Point3d) = Point3d(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Point3d) = Point3d(x - other.x, y - other.y, z - other.z)

    infix fun distanceTo(other: Point3d) = abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

    fun allRotations(): Set<Point3d> {
        return setOf(
            Point3d(x, y, z),
            Point3d(x, -z, y),
            Point3d(x, -y, -z),
            Point3d(x, z, -y),
            Point3d(-x, -y, z),
            Point3d(-x, -z, -y),
            Point3d(-x, y, -z),
            Point3d(-x, z, y),
            Point3d(-z, x, -y),
            Point3d(y, x, -z),
            Point3d(z, x, y),
            Point3d(-y, x, z),
            Point3d(z, -x, -y),
            Point3d(y, -x, z),
            Point3d(-z, -x, y),
            Point3d(-y, -x, -z),
            Point3d(-y, -z, x),
            Point3d(z, -y, x),
            Point3d(y, z, x),
            Point3d(-z, y, x),
            Point3d(z, y, -x),
            Point3d(-y, z, -x),
            Point3d(-z, -y, -x),
            Point3d(y, -z, -x),
        )
    }

    override fun compareTo(other: Point3d): Int =
        if (this.x <= other.x && this.y <= other.y && this.z <= other.z) -1 else 1
}


data class Point4d(val x: Int, val y: Int, val z: Int, val w: Int) : Point<Point4d> {

    override fun adjacent() =
        (x - 1..x + 1).flatMap { dx ->
            (y - 1..y + 1).flatMap { dy ->
                (z - 1..z + 1).flatMap { dz ->
                    (w - 1..w + 1).mapNotNull { dw ->
                        Point4d(dx, dy, dz, dw).takeUnless { it == this }
                    }
                }
            }
        }.toSet()
}
