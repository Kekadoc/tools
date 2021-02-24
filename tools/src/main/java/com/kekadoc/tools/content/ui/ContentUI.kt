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
     * Показать контент
     * Вызывается что бы представить имеющийся контент
     * @param code Unique code
     */
    fun show(code: Int = EMPTY_CODE)
    /**
     * Вывод ошибки контента
     * @param code Unique code
     */
    fun error(code: Int = EMPTY_CODE)
    /**
     * Start loading in content with code
     * @param code Unique code
     * @return Load object to manage
     */
    fun loading(code: Int = EMPTY_CODE): Loading

    /**
     * Notify content with code
     *
     * @param code Unique code
     */
    fun notify(code: Int = EMPTY_CODE)

    interface Loading : Fraction.Mutable {

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

}