package com.github.rloic.packages.tikz

import com.github.rloic.OPTION
import com.github.rloic.core.LatexContent
import com.github.rloic.core.LatexRepresentable
import com.github.rloic.core.Length
import com.github.rloic.core.Length.Unit.DEFAULT

interface Draw
interface Scope
interface DrawOrScope: Scope, Draw

interface LineThickness: OPTION<DrawOrScope>

open class RawLatex(private val rawLatex: String) : LatexRepresentable {
    override fun toLatex() = LatexContent(rawLatex)
}

fun lineWidth(measure: Number) = LineWidth(Length(measure, DEFAULT))
fun lineWidth(length: Length) = LineWidth(length)
class LineWidth(length: Length): RawLatex("line width = $length"), LineThickness

object thick : RawLatex("thick"), LineThickness
object very_thick : RawLatex("very thick"), LineThickness
object ultra_thick : RawLatex("ultra thick"), LineThickness

object thin : RawLatex("thin"), LineThickness
object very_thin : RawLatex("very thin"), LineThickness
object ultra_thin : RawLatex("ultra thin"), LineThickness

interface LineStyle : OPTION<Draw>

object dotted : RawLatex("dotted"), LineStyle
object loosely_dotted : RawLatex("loosely dotted"), LineStyle
object densely_dotted : RawLatex("densely dotter"), LineStyle
object dashed : RawLatex("dashed"), LineStyle
object loosely_dashed : RawLatex("loosely dashed"), LineStyle
object densely_dashed : RawLatex("densely dashed"), LineStyle

interface ArrowHead : OPTION<Draw>

interface Color: OPTION<DrawOrScope>

class Fill(inner: Color) : RawLatex("fill = ${inner.toLatex()}"), OPTION<Draw>
open class NamedColor(repr: String): RawLatex(repr), Color

object black : NamedColor("black")
object gray : NamedColor("gray")
object red : NamedColor("red")
object green : NamedColor("green")


