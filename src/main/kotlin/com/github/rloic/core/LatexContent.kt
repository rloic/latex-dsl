package com.github.rloic.core

import com.github.rloic.OPTION

data class LatexContent(val rawLatex: String) : OPTION<Nothing> {
    override fun toLatex() = this
    override fun toString() = rawLatex
}