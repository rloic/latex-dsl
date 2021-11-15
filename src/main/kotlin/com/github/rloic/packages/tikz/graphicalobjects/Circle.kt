package com.github.rloic.packages.tikz.graphicalobjects

import com.github.rloic.core.LatexContent
import com.github.rloic.core.Length
import com.github.rloic.packages.tikz.api.Path

data class Circle(val radius: Length) : Path {
    override fun toLatex() = LatexContent("circle ($radius)")
}
