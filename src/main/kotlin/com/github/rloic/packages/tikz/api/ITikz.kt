package com.github.rloic.packages.tikz.api

import com.github.rloic.*
import com.github.rloic.api.IDocument
import com.github.rloic.core.Length
import com.github.rloic.core.asLength
import com.github.rloic.packages.tikz.*

interface ITikz {
    interface Preamble {
        fun addTikzLibrary(libName: Libraries)
    }

    interface Document {
        fun scope(vararg options: OPTION<out Scope>, init: Document.() -> Unit = {})
        fun draw(vararg option: OPTION<out Draw>?, path: DrawContext.() -> Path)
        fun fill(vararg option: OPTION<out Draw>?, path: DrawContext.() -> Path)
        fun node(x: Length, y: Length, vararg options: OPTION<out Draw>, init: IDocument.() -> Unit = {})
    }
}