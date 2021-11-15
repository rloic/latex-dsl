package com.github.rloic.core

import java.lang.StringBuilder

interface LatexBuilder<S> {

    fun scoped(start: String? = null, end: String? = null, fn: S.() -> Unit)

    operator fun plusAssign(content: LatexContent)

    fun build(): LatexContent

}

class PreamblePackageScope : LatexBuilder<PreamblePackageScope> {
    private val builder = StringBuilder()
    private var indent = ""
    private var packageInstallation = false

    override fun scoped(start: String?, end: String?, fn: PreamblePackageScope.() -> Unit) {
        if (start != null) plusAssign(LatexContent(start))
        indent = "  $indent"
        fn()
        indent = indent.substring(2)
        if (end != null) plusAssign(LatexContent(end))
    }

    override operator fun plusAssign(content: LatexContent) {
        builder.append(indent)
        builder.appendLine(content.rawLatex)
    }

    operator fun remAssign(content: LatexContent) {
        if (content.rawLatex !in builder) {
            plusAssign(content)
        }
    }

    fun usePackage(name: String) {
        if (!packageInstallation) {
            this += LatexContent("\\usepackage{$name}")
            packageInstallation = true
        }
    }

    override fun build() = LatexContent(builder.toString())
}