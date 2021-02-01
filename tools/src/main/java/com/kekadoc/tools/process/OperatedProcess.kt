package com.kekadoc.tools.process

interface OperatedProcess : Process {

    interface Observer : Process.Observer {
        fun onProcessRunned(process: OperatedProcess?, runnedProgress: Double) {}
        fun onProcessStopped(process: OperatedProcess?, stoppedProgress: Double) {}
    }
    interface Observable : FailableProcess {
        fun addProcessObserver(observer: Observer)
        fun removeProcessObserver(observer: Observer?)
    }

    /**
     * Instant end process
     */
    fun end()

    /**
     * Pause process
     * @return current progress
     */
    fun stop(): Double

    /**
     * Continue process
     * @param progress Progress
     */
    fun run(progress: Double)

    /**
     * Is stopped
     */
    val isStopped: Boolean

}