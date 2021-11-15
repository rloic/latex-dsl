package com.github.rloic.packages.tikz

import com.github.rloic.OPTION
import com.github.rloic.api.IDocument
import com.github.rloic.core.*
import com.github.rloic.packages.tikz.api.DrawContext
import com.github.rloic.packages.tikz.api.ITikz
import com.github.rloic.packages.tikz.api.Libraries
import com.github.rloic.packages.tikz.api.Path

class Tikz(val packageScope: PreamblePackageScope, val document: Document) :
    LatexPackage<ITikz.Preamble, ITikz.Document>, ITikz.Preamble, ITikz.Document, DrawContext {
    override fun asPreambleRef() = this
    override fun asDocRef() = this

    private val installedLibs = mutableSetOf<Libraries>()

    override fun install() = packageScope.usePackage("tikz")

    override fun addTikzLibrary(libName: Libraries) {
        if (libName !in installedLibs) {
            installedLibs += libName
            packageScope.scoped {
                this += LatexContent("\\usetikzlibrary{${libName.latex}}")
            }
        }
    }

    override fun draw(vararg option: OPTION<out Draw>?, path: DrawContext.() -> Path) {
        document += LatexContent(
            "\\draw${
                option.filterNotNull().map(OPTION<out Draw>::toLatex).map(LatexContent::rawLatex)
            } " + path().toLatex().rawLatex + ";"
        )
    }

    override fun fill(vararg option: OPTION<out Draw>?, path: DrawContext.() -> Path) {
        document += LatexContent(
            "\\fill${
                option.filterNotNull().map(OPTION<out Draw>::toLatex).map(LatexContent::rawLatex)
            } " + path().toLatex().rawLatex + ";"
        )
    }

    override fun scope(vararg options: OPTION<out Scope>, init: ITikz.Document.() -> Unit) {
        document += LatexContent("\\begin{scope}[${options.joinToString(",") { it.toLatex().rawLatex }}]")
        document.scoped {
            init()
        }
        document += LatexContent("\\end{scope}")
    }

    override fun node(x: Length, y: Length, vararg options: OPTION<out Draw>, init: IDocument.() -> Unit) {
        document += LatexContent("\\node [${options.joinToString(",") { it.toLatex().rawLatex }}] at ($x, $y) {")
        document.init()
        document += LatexContent("};")
    }
}

fun Shift(x: Number, y: Number) = Shift(x.asLength(), y.asLength())
fun Shift(x: Number, y: Length) = Shift(x.asLength(), y)
fun Shift(x: Length, y: Number) = Shift(x, y.asLength())
class Shift(x: Length, y: Length) : RawLatex("shift = {($x, $y)}"), OPTION<Scope>