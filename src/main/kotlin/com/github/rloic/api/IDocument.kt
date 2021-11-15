package com.github.rloic.api

import com.github.rloic.OPTION
import com.github.rloic.core.LatexContent
import com.github.rloic.core.Part
import com.github.rloic.core.sanitize
import com.github.rloic.packages.tikz.Scope
import com.github.rloic.packages.tikz.api.ITikz

interface IDocument : Part {

    override operator fun LatexContent.unaryPlus()
    override operator fun String.unaryPlus()
    override operator fun String.not() = LatexContent(this)

    fun tt(fn: IDocument.() -> Unit)
    fun tt(text: String) = LatexContent("\\texttt{${sanitize(text)}}")
    fun tt(text: LatexContent) = LatexContent("\\texttt{${text.rawLatex}}")
    fun bf(text: String) = LatexContent("\\textbf{${sanitize(text)}}")
    fun bf(text: LatexContent) = LatexContent("\\textbf{${text.rawLatex}}")
    fun it(text: String) = LatexContent("\\textit{${sanitize(text)}}")
    fun it(text: LatexContent) = LatexContent("\\textit{${text.rawLatex}}")
    fun tikz(vararg option: OPTION<out Scope>, fn: ITikz.Document.() -> Unit)
}