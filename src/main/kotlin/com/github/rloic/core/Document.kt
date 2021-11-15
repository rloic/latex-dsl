package com.github.rloic.core

import com.github.rloic.OPTION
import com.github.rloic.api.IDocument
import com.github.rloic.packages.tikz.Scope
import com.github.rloic.packages.tikz.api.ITikz


class Document(val file: LatexFile) : IDocument, LatexBuilder<Document> {

    private val builder = StringBuilder()
    private var indent = "  "

    override fun scoped(start: String?, end: String?, fn: Document.() -> Unit) {
        if (start != null) plusAssign(LatexContent(start))
        indent = "  $indent"
        fn()
        indent = indent.substring(2)
        if (end != null) plusAssign(LatexContent(end))
    }

    private fun plusAssign(content: String) {
        builder.append(indent)
        builder.appendLine(content)
    }

    override operator fun plusAssign(content: LatexContent) {
        builder.append(indent)
        builder.appendLine(content.rawLatex)
    }

    override fun build() = LatexContent(builder.toString())

    override fun LatexContent.unaryPlus() {
        plusAssign(rawLatex.replace("\n", "\n$indent"))
    }

    override fun String.unaryPlus() {
        plusAssign(sanitize(this).replace("\n", "\n$indent"))
    }

    fun <P : LatexPackage<PreambleInit, DocumentInit>, PreambleInit, DocumentInit> call(
        pkg: P,
        init: DocumentInit.() -> Unit
    ) {
        pkg.install()
        pkg.asDocRef().init()
    }

    override fun tt(fn: IDocument.() -> Unit) = scoped("{\\tt", "}", fn)

    override fun tikz(vararg option: OPTION<out Scope>, fn: ITikz.Document.() -> Unit) {
        scoped("\\begin{tikzpicture}[${option.joinToString(",") { it.toLatex().rawLatex }}]", "\\end{tikzpicture}") {
            call(file.preamble.tikz, fn)
        }
    }

    override fun part(name: String, fn: Chapter.() -> Unit) = scoped("\\part{$name}", fn = fn)

    override fun chapter(name: String, fn: Section.() -> Unit) = scoped("\\chapter{$name}", fn = fn)

    override fun section(name: String, fn: SubSection.() -> Unit) = scoped("\\section{$name}", fn = fn)

    override fun subSection(name: String, fn: SubSubSection.() -> Unit) = scoped("\\subsection{$name}", fn = fn)

    override fun subSubSection(name: String, fn: Paragraph.() -> Unit) = scoped("\\subsubsection{$name}", fn = fn)

    override fun paragraph(name: String, fn: SubParagraph.() -> Unit) = scoped("\\paragraph{$name}", fn = fn)

    override fun subparagraph(name: String, fn: TextNode.() -> Unit) = scoped("\\subparagraph{$name}", fn = fn)

}

fun sanitize(str: String): String {
    var copy = str
    for (symbol in charArrayOf('\\', '&', '%', '$', '#', '_', '{', '}', '~', '^')) {
        copy = copy.replace(symbol.toString(), "\\$symbol")
    }

    return copy
}