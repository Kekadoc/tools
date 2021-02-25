package com.kekadoc.tools.content.ui

import com.kekadoc.tools.fraction.Fraction
import com.kekadoc.tools.fraction.FractionObserver

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

    }

}