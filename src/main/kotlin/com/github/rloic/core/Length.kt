package com.github.rloic.core

import com.github.rloic.core.Length.Unit

data class Length(val measure: Number, val unit: Unit) {
    enum class Unit(val repr: String) {
        PT("pt"),
        MM("mm"),
        CM("cm"),
        IN("in"),
        EX("ex"),
        EM("em"),
        SP("sp"),
        DEFAULT("")
    }

    override fun toString() = "$measure${unit.repr}"
    operator fun unaryMinus() = Length(-measure.toDouble(), unit)
    operator fun times(k: Number) = Length(measure.toDouble() * k.toDouble(), unit)
    operator fun plus(k: Number) = Length(measure.toDouble() + k.toDouble(), unit)
    operator fun minus(k: Number) = Length(measure.toDouble() - k.toDouble(), unit)
}

operator fun Number.times(l: Length) = l.times(this)
operator fun Number.plus(l: Length) = l.plus(this)

val Number.pt get() = Length(this, Unit.PT)
val Number.cm get() = Length(this, Unit.CM)
val Number.mm get() = Length(this, Unit.MM)
val Number.inch get() = Length(this, Unit.IN)
val Number.ex get() = Length(this, Unit.EX)
val Number.em get() = Length(this, Unit.EM)
val Number.sp get() = Length(this, Unit.SP)
fun Number.asLength() = Length(this, Unit.DEFAULT)