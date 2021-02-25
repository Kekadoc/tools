package com.kekadoc.tools.content.ui

import com.kekadoc.tools.fraction.Fraction
import com.kekadoc.tools.fraction.FractionObserver

/**
 * Interface for abstract ui content
 */
interface ContentUI {

    companion object {
        const val EMPTY_CODE = 0
    }

    /**
     * Show content
     * @param code Unique code
     * @return [Content]
     */
    fun content(code: Int = EMPTY_CODE): Content
    /**
     * Show message
     * @param code Unique code
     * @return [Message]
     */
    fun message(code: Int = EMPTY_CODE): Message
    /**
     * Start loading in content with code
     * @param code Unique code
     * @return [Loading]
     */
    fun loading(code: Int = EMPTY_CODE): Loading

    /**
     * Notify content with code
     *
     * @param code Unique code
     */
    fun notify(code: Int = EMPTY_CODE)

    interface Loading : Fraction.Mutable {

        object Empty : Loading {
            override fun isActive(): Boolean = false
            override fun show() {}
            override fun remove() {}
            override fun complete() {}
            override fun getFraction(): Double = 0.0
            override fun setFraction(fraction: Double) {}
        }

        interface Observer : FractionObserver {
            fun onShow()
            fun onRemove()
            fun onComplete()
            override fun onFractionChange(
                fraction: Fraction,
                oldFraction: Double,
                newFraction: Double
            )
        }

        /**
         * Check
         */
        fun isActive(): Boolean
        /**
         * Show
         */
        fun show()
        /**
         * Remove loading
         */
        fun remove()
        /**
         * Complete loading
         */
        fun complete()

        override fun getFraction(): Double
        override fun setFraction(fraction: Double)

    }

    interface Content {

        object Empty : Content {
            override fun isShown(): Boolean = false
            override fun show() {}
            override fun hide() {}
        }

        interface Observer {
            fun onShow()
            fun onHide()
            fun onComplete()
        }

        fun isShown(): Boolean
        fun show()
        fun hide()
    }

    interface Message {

        object Empty : Message {
            override fun isShown(): Boolean = false
            override fun show() {}
            override fun remove() {}
        }

        interface Observer {
            fun onShow()
            fun onRemove()
        }

        fun isShown(): Boolean
        fun show()
        fun remove()

    }

}