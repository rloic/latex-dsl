package com.github.rloic.core

fun latex(cls: String, init: LatexFile.() -> Unit): LatexFile {
    val res = LatexFile(LatexContent(cls))
    res.init()
    return res
}

fun main() {

}

