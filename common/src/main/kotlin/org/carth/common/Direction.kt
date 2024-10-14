package org.carth.common

enum class Direction(val value: Int) {
    SOUTH(0), EAST(1), NORTH(2), WEST(3);

    fun turnLeft() = from((this.value - 1).mod(4))

    fun turnRight() = from((this.value + 1).mod(4))

    fun toPoint2d() = when (this) {
        SOUTH -> Point2d.DOWN
        NORTH -> Point2d.UP
        WEST -> Point2d.LEFT
        EAST -> Point2d.RIGHT
    }

    companion object {
        infix fun from(value: Int): Direction = entries.firstOrNull { it.value == value }!!
        val horizontal = listOf(WEST, EAST)
        val vertical = listOf(NORTH, SOUTH)
    }
}