package com.kekadoc.tools.process

/**
 * Abstract failable process
 */
interface FailableProcess : Process {

    interface Observer : Process.Observer {
        fun onProcessFailed(process: FailableProcess, exception: Exception?) {}
    }
    interface Observable : FailableProcess {
        fun addProcessObserver(observer: Observer)
        fun removeProcessObserver(observer: Observer?)
    }

    fun fail(exception: Exception?)

    fun getFail(): java.lang.Exception?

    /**
     * Process is failed
     */
    fun isFailed(): Boolean

}