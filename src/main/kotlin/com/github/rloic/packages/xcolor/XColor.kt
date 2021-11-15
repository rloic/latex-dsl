package com.github.rloic

import com.github.rloic.core.Document
import com.github.rloic.core.LatexContent
import com.github.rloic.core.LatexPackage
import com.github.rloic.core.PreamblePackageScope
import com.github.rloic.packages.tikz.NamedColor

interface IXColor {

    interface Preamble {

        fun rgb(name: String, r: Int, g: Int, b: Int): NamedColor
        fun html(name: String, hexa: Int): NamedColor

    }

    interface Document {}

}

class XColor(val packageScope: PreamblePackageScope, val document: Document) :
    LatexPackage<IXColor.Preamble, IXColor.Document>, IXColor.Preamble, IXColor.Document {
    override fun install() = packageScope.usePackage("xcolor")

    override fun asPreambleRef() = this
    override fun asDocRef() = this

    override fun rgb(name: String, r: Int, g: Int, b: Int): NamedColor {
        packageScope += LatexContent("\\definecolor{$name}{RGB}{$r,$g,$b}")
        return NamedColor(name)
    }

    override fun html(name: String, hexa: Int): NamedColor {
        packageScope += LatexContent("\\definecolor{$name}{HTML}{${Integer.toHexString(hexa).padStart(6, '0')}}")
        return NamedColor(name)
    }
}