package com.github.rloic.packages.tikz.graphicalobjects

import com.github.rloic.core.LatexContent
import com.github.rloic.packages.tikz.api.Path

class Controls(point: Point, vararg points: Point) : Path {
    private val points = listOf(point, *points)

    override fun toLatex() =
        LatexContent(".. controls " + points.joinToString(" and ") { it.toLatex().rawLatex } + " ..")
}