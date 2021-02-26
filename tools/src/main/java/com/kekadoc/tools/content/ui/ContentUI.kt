package com.kekadoc.tools.content.ui

import com.kekadoc.tools.fraction.Fraction
import com.kekadoc.tools.fraction.FractionObserver
import com.kekadoc.tools.value.ValueUtils

interface ContentUI {

    object Empty : ContentUI {
        override fun isShown(): Boolean = false
        override fun show() {}
        override fun hide() {}
    }

    interface Observer {
        fun onShow()
        fun onHide()
    }

    fun isShown(): Boolean
    fun show()
    fun hide()

    open class SimpleInstance : ContentUI {

        private var shown = false

        protected open fun onShown() {}
        protected open fun onHide() {}

        override fun isShown() = shown
        override fun show() {
            if (shown) return
            shown = true
            onShown()
        }
        override fun hide() {
            if (!shown) return
            shown = false
            onHide()
        }

    }

    interface Loading : ContentUI, Fraction.Mutable {

        object Empty : Loading {
            override fun isShown(): Boolean = false
            override fun hide() {}
            override fun show() {}
            override fun complete() {}
            override fun getFraction(): Double = 0.0
            override fun setFraction(fraction: Double) {}
        }

        interface Observer : ContentUI.Observer, FractionObserver {

            fun onComplete()

            override fun onFractionChange(
                    fraction: Fraction,
                    oldFraction: Double,
                    newFraction: Double
            )

        }

        /**
         * Complete loading
         */
        fun complete() {
            setFraction(1.0)
        }

        override fun getFraction(): Double
        override fun setFraction(fraction: Double)

        open class SimpleInstance : ContentUI.SimpleInstance(), Loading {

            private var progress = 0.0

            protected open fun onComplete() {}
            protected open fun onProgress(old: Double, progress: Double) {}

            override fun complete() {
                setFraction(1.0)
                onComplete()
            }

            override fun getFraction(): Double = progress

            override fun setFraction(fraction: Double) {
                if (progress == fraction) return
                ValueUtils.setValueInRange(0.0, 1.0, fraction, object : ValueUtils.RangeChangeEvents<Double> {
                    override fun onChange(oldValue: Double, newValue: Double) {
                        progress = newValue
                        onProgress(oldValue, newValue)
                    }
                    override fun onMax(max: Double) {
                        complete()
                    }
                    override fun onMin(min: Double) {}
                    override fun onOverflow(overflow: Double) {}
                })
            }

        }

    }

    interface Message : ContentUI {

        interface Observer : ContentUI.Observer {
            fun onChangeMessageText(text: CharSequence?)
        }

        object Empty : Message {

            override fun isShown(): Boolean = false
            override fun show() {}
            override fun hide() {}
            override fun getText(): CharSequence? {
                return null
            }
            override fun setText(text: CharSequence?) {

            }

        }

        fun getText(): CharSequence?
        fun setText(text: CharSequence?)

        open class SimpleInstance(var message: CharSequence? = null) : ContentUI.SimpleInstance(), Message {

            override fun getText(): CharSequence? = message
            override fun setText(text: CharSequence?) {
                message = text
            }

        }

    }

}