package org.carth.common

enum class Direction(val value: Int) {
    SOUTH(0), EAST(1), NORTH(2), WEST(3);

    fun turnLeft() = from((this.value - 1).mod(4))

    fun turnRight() = from((this.value + 1).mod(4))

    companion object {
        infix fun from(value: Int): Direction = Direction.values().firstOrNull { it.value == value }!!
    }
}