package com.github.rloic.api

import com.github.rloic.core.LatexContent
import com.github.rloic.core.LatexPackage
import com.github.rloic.packages.tikz.Tikz
import com.github.rloic.XColor

interface IPreamble {
    val tikz: Tikz
    val xcolor: XColor

    operator fun LatexContent.unaryPlus()
    operator fun String.unaryPlus()
    operator fun String.not() = LatexContent(this)

    fun <P : LatexPackage<PreambleInit, DocumentInit>, PreambleInit, DocumentInit> usePackage(
        pkg: P,
        init: PreambleInit.() -> Unit = {}
    )
}