package com.github.rloic.packages.tikz.graphicalobjects

import com.github.rloic.core.LatexContent
import com.github.rloic.packages.tikz.api.Path

object Line : Path {
    override fun toLatex() = LatexContent("--")
}