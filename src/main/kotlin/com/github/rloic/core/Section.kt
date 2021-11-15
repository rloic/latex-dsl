package com.github.rloic.core

interface Part : Chapter {
    fun part(name: String, fn: Chapter.() -> Unit)
}

interface Chapter : Section {
    fun chapter(name: String, fn: Section.() -> Unit)
}

interface Section : SubSection {
    fun section(name: String, fn: SubSection.() -> Unit)
}

interface SubSection : SubSubSection {
    fun subSection(name: String, fn: SubSubSection.() -> Unit)
}

interface SubSubSection : Paragraph {
    fun subSubSection(name: String, fn: Paragraph.() -> Unit)
}

interface Paragraph : SubParagraph {
    fun paragraph(name: String, fn: SubParagraph.() -> Unit)
}

interface SubParagraph : TextNode {
    fun subparagraph(name: String, fn: TextNode.() -> Unit)
}

interface TextNode {
    operator fun LatexContent.unaryPlus()
    operator fun String.unaryPlus()
    operator fun String.not(): LatexContent
}