package com.github.rloic.core

interface LatexPackage<PI, DI> : PreambleInit<PI>, DocumentInit<DI> {
    fun install()
}

interface PreambleInit<PkgInit> {
    fun  asPreambleRef(): PkgInit
}
interface DocumentInit<PkgInit> {
    fun asDocRef(): PkgInit
}

