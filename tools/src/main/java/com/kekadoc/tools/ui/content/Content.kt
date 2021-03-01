package com.kekadoc.tools.ui.content

import com.kekadoc.tools.fraction.Fraction
import com.kekadoc.tools.fraction.FractionObserver
import com.kekadoc.tools.value.ValueUtils

interface Content {

    object Empty : Content {
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

    open class SimpleInstance(shown: Boolean = true) : Content {

        private var shown = shown

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

    interface Progress : Content, Fraction.Mutable {

        interface Observer : Content.Observer, FractionObserver {

            fun onComplete()

            override fun onFractionChange(
                    fraction: Fraction,
                    oldFraction: Double,
                    newFraction: Double
            )

        }

        object Empty : Progress {

            override fun isShown(): Boolean = false
            override fun hide() {}
            override fun show() {}
            override fun complete() {}
            override fun getFraction(): Double = 0.0
            override fun setFraction(fraction: Double) {}

        }

        /**
         * Complete loading
         */
        fun complete() {
            setFraction(1.0)
        }

        override fun getFraction(): Double
        override fun setFraction(fraction: Double)

        open class SimpleInstance(shown: Boolean = true) : Content.SimpleInstance(shown), Progress {

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
                ValueUtils.valueInRange(0.0, 1.0, fraction, object : ValueUtils.RangeChangeEvents<Double> {
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

    interface Message : Content {

        interface Observer : Content.Observer {
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

        open class SimpleInstance(var message: CharSequence? = null) : Content.SimpleInstance(), Message {

            override fun getText(): CharSequence? = message
            override fun setText(text: CharSequence?) {
                message = text
            }

        }

    }

}