package com.kekadoc.tools.observable

open class MutableData<T> (value: T) : ObservableData<T>(value), Mutable<T> {

    companion object {
        fun <T> T.toMutable(): MutableData<T> = MutableData(this)
    }

    public override fun notifyValue() {
        super.notifyValue()
    }
    public override fun setValue(value: T): Boolean {
        return super.setValue(value)
    }
    public override fun updateValue(value: T) {
        super.updateValue(value)
    }

}