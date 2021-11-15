package com.github.rloic.core

import com.github.rloic.api.IDocument
import com.github.rloic.api.IPreamble

class LatexFile(val cls: LatexContent) {
    val preamble = Preamble(this)
    val document = Document(this)

    fun preamble(fn: IPreamble.() -> Unit) {
        this.preamble.fn()
    }

    fun document(fn: IDocument.() -> Unit) {
        this.document.fn()
    }

    fun build() = buildString {
        appendLine("\\documentclass{${cls.rawLatex}}")
        append(preamble.build().rawLatex)
        appendLine("\\begin{document}")
        append(document.build().rawLatex)
        appendLine("\\end{document}")
    }

}