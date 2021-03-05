package com.kekadoc.tools.observer

open class Mutable<T> (value: T) : Observable<T>(value) {

    companion object {
        fun <T> T.toMutable(): Mutable<T> = Mutable(this)
    }

    public override fun notifyValue() {
        super.notifyValue()
    }
    public override fun setValue(value: T) {
        super.setValue(value)
    }
    public override fun updateValue(value: T) {
        super.updateValue(value)
    }
}