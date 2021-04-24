package com.kekadoc.tools.observable

open class SingleMutableData<T> (value: T) : SingleObservableData<T>(value), Mutable<T> {

    companion object {
        fun <T> T.toSingleMutable(): SingleMutableData<T> = SingleMutableData(this)
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