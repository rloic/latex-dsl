package com.github.rloic.core

import com.github.rloic.packages.tikz.Tikz
import com.github.rloic.XColor
import com.github.rloic.api.IPreamble


class Preamble(val file: LatexFile) : IPreamble, LatexBuilder<Preamble> {

    private val packageScopes = mutableListOf<PreamblePackageScope>()
    private val builder = StringBuilder()
    private var indent = ""

    override fun scoped(start: String?, end: String?, fn: Preamble.() -> Unit) {
        if (start != null) plusAssign(LatexContent(start))
        indent = "  $indent"
        fn()
        indent = indent.substring(2)
        if (end != null) plusAssign(LatexContent(end))
    }

    private fun newPackageScope(): PreamblePackageScope {
        val nextPackageScope = PreamblePackageScope()
        packageScopes += nextPackageScope
        return nextPackageScope
    }

    override operator fun plusAssign(content: LatexContent) {
        builder.append(indent)
        builder.appendLine(content.rawLatex)
    }

    override fun build() = LatexContent(buildString {
        for (pkgScope in packageScopes) {
            append(pkgScope.build().rawLatex)
        }
        append(builder.toString())
    })

    override fun LatexContent.unaryPlus() {
        plusAssign(LatexContent(rawLatex.replace("\n", "\n$indent")))
    }

    override fun String.unaryPlus() {
        plusAssign(LatexContent(sanitize(this).replace("\n", "\n$indent")))
    }

    override val tikz by lazy { Tikz(newPackageScope(), file.document) }
    override val xcolor by lazy { XColor(newPackageScope(), file.document) }

    override fun <P : LatexPackage<I, D>, I, D> usePackage(pkg: P, init: I.() -> Unit) {
        pkg.install()
        pkg.asPreambleRef().init()
    }

}



