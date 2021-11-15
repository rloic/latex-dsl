package com.github.rloic.packages.tikz.graphicalobjects

import com.github.rloic.core.LatexContent
import com.github.rloic.core.Length
import com.github.rloic.core.asLength
import com.github.rloic.packages.tikz.RawLatex
import com.github.rloic.packages.tikz.api.Path

fun Point(x: Number, y: Number) = Point(x.asLength(), y.asLength())
fun Point(x: Number, y: Length) = Point(x.asLength(), y)
fun Point(x: Length, y: Number) = Point(x, y.asLength())
data class Point(val x: Length, val y: Length) : RawLatex("($x, $y)"), Path