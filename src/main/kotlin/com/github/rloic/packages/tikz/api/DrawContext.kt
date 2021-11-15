package com.github.rloic.packages.tikz.api

import com.github.rloic.core.Length
import com.github.rloic.packages.tikz.graphicalobjects.*

interface DrawContext {

    operator fun Point.rangeTo(other: Point) = OpenPath(listOf(this, Line, other))
    fun Point.circle(radius: Length) = OpenPath(listOf(this, Circle(radius)))
    fun Point.arc(startAngle: Number, endAngle: Number, radius: Length) =
        OpenPath(listOf(this, Arc(startAngle, endAngle, radius)))

}