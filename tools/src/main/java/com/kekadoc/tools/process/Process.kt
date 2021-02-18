package com.kekadoc.tools.process

import com.kekadoc.tools.fraction.Fraction
import com.kekadoc.tools.fraction.FractionValue

/**
 * Abstract process
 */
interface Process : Fraction {

    interface Observer {
        fun onProcessStarted(process: Process?) {}
        fun onProcessProgress(process: Process?, fraction: Double) {}
        fun onProcessSuccess(process: Process?) {}
        fun onProcessCancelled(process: Process?) {}
    }
    interface Observable : Process {
        fun addProcessObserver(observer: Observer)
        fun removeProcessObserver(observer: Observer?)
    }

    /**
     * Start process
     */
    fun start()

    /**
     * Cancel process
     */
    fun cancel()

    /**
     * Process is success
     */
    val isSuccess: Boolean

    /**
     * Process is active
     */
    val isActive: Boolean

    /**
     * Process is canceled
     */
    val isCanceled: Boolean

    /**
     * Current progress
     */
    override fun getFraction(): Double

}