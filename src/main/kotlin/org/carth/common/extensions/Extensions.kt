package org.carth.common.extensions

import org.carth.common.Point

operator fun List<String>.get(p: Point) = this[p.y][p.x]