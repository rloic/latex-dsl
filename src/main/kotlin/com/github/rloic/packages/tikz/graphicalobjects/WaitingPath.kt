package com.github.rloic.packages.tikz.graphicalobjects

import com.github.rloic.packages.tikz.api.Path

data class WaitingPath(val elements: List<Path>) {
    operator fun rangeTo(controls: Controls) = WaitingPath(elements + controls)
    operator fun rangeTo(point: Point) = OpenPath(elements + point)
}