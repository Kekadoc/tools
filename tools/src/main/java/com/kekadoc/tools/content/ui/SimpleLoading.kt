package com.kekadoc.tools.content.ui

import com.kekadoc.tools.value.ValueUtils

open class SimpleLoading : SimpleContent(), ContentUI.Loading {

    private var progress = 0.0

    protected open fun onComplete() {}
    protected open fun onProgress(old: Double, progress: Double) {}

    override fun complete() {
        setFraction(1.0)
        onComplete()
    }

    override fun getFraction(): Double = progress

    override fun setFraction(fraction: Double) {
        ValueUtils.setValueInRange(0.0, 1.0, fraction, object : ValueUtils.RangeChangeEvents<Double> {
            override fun onChange(oldValue: Double, newValue: Double) {
                progress = newValue
                onProgress(oldValue, newValue)
            }
            override fun onMax() {
                complete()
            }
            override fun onMin() {}
            override fun onOverflow(overflow: Double) {}
        })
    }

}