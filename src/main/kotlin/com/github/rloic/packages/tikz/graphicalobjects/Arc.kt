package com.github.rloic.packages.tikz.graphicalobjects

import com.github.rloic.core.LatexContent
import com.github.rloic.core.Length
import com.github.rloic.packages.tikz.api.Path

data class Arc(val startAngle: Number, val endAngle: Number, val radius: Length) : Path {
    override fun toLatex() = LatexContent("arc ($startAngle:$endAngle:$radius)")
}