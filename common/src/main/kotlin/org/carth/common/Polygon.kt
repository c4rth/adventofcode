package org.carth.common

import kotlin.math.absoluteValue

fun List<Point2d>.shoelaceArea() =
    (this.zipWithNext() + listOf(this.last() to this.first()))
        .sumOf { (current, next) -> current.x.toLong() * next.y - next.x.toLong() * current.y }
        .absoluteValue / 2L