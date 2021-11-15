package com.github.rloic.packages.tikz.graphicalobjects

import com.github.rloic.core.LatexContent
import com.github.rloic.packages.tikz.api.Path

data class OpenPath(val elements: List<Path>) : Path {
    override fun toLatex() = LatexContent(elements.joinToString(" ") { it.toLatex().rawLatex })
    operator fun rangeTo(controls: Controls) = WaitingPath(elements + controls)
    operator fun rangeTo(point: Point) = OpenPath(elements + Line + point)
    operator fun rangeTo(path: OpenPath) = OpenPath(elements + Line + path.elements)
}