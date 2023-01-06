package org.carth.common.extensions

import org.carth.common.Point2d

operator fun List<String>.get(p: Point2d) = this[p.y][p.x]