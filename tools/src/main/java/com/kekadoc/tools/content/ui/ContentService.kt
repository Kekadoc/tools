package com.kekadoc.tools.content.ui

import com.kekadoc.tools.value.ValueUtils

class ContentService(adapter: Adapter? = null) : ContentUI {

    enum class State {
        NULL, ERROR, CONTENT, LOADING
    }

    var state = State.NULL
        private set

    var adapter: Adapter? = adapter
        set(value) {
            field?.invokeOnDetach(this)
            field = value
            value?.invokeOnAttach(this)
        }

    override fun show(code: Int) {
        state = State.CONTENT
        adapter?.invokeOnShowContent(code)
    }
    override fun error(code: Int) {
        state = State.ERROR
        adapter?.invokeOnError(code)
    }
    override fun loading(code: Int): ContentUI.Loading {
        state = State.LOADING
        return if (adapter == null) SimpleLoading.emptyInstance else adapter!!.invokeOnLoading(code)
    }
    override fun notify(code: Int) {
        adapter?.invokeOnNotify(code)
    }

    fun clear() {
        this.adapter = null
    }

    abstract class Adapter {

        protected abstract fun onShowContent(code: Int)
        protected abstract fun onError(code: Int)
        protected abstract fun onLoading(code: Int): ContentUI.Loading
        protected abstract fun onNotify(code: Int)

        protected open fun onAttach(service: ContentService) {}
        protected open fun onDetach(service: ContentService) {}
        protected open fun onClear(service: ContentService) {}

        internal fun invokeOnShowContent(code: Int) {
            onShowContent(code)
        }
        internal fun invokeOnError(code: Int) {
            onError(code)
        }
        internal fun invokeOnLoading(code: Int): ContentUI.Loading {
            return onLoading(code)
        }
        internal fun invokeOnNotify(code: Int) {
            onNotify(code)
        }
        internal fun invokeOnAttach(service: ContentService) {
            onAttach(service)
        }
        internal fun invokeOnDetach(service: ContentService) {
            onDetach(service)
        }

    }

    private class SimpleLoading(val observer: ContentUI.Loading.Observer? = null) : ContentUI.Loading {

        companion object {
            @JvmStatic internal val emptyInstance = SimpleLoading()
        }

        private var active = false
        private var progress = 0.0

        override fun isActive(): Boolean = active
        override fun show() {
            active = true
            observer?.onShow()
        }
        override fun remove() {
            active = false
            observer?.onRemove()
        }
        override fun complete() {
            setFraction(1.0)
        }

        override fun getFraction(): Double {
            TODO("Not yet implemented")
        }

        override fun setFraction(fraction: Double) {
            ValueUtils.setValueInRange(0.0, 1.0, fraction, object : ValueUtils.RangeChangeEvents<Double> {
                override fun onChange(oldValue: Double, newValue: Double) {
                    progress = newValue
                    observer?.onFractionChange(this@SimpleLoading, oldValue, progress)
                }
                override fun onMax() {
                    complete()
                }
                override fun onMin() {}
                override fun onOverflow(overflow: Double) {}
            })
        }

    }

}