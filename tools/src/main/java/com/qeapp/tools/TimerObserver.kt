package com.qeapp.tools

import java.util.*

/**
 * Класс для создания переодического действия с указанными объектами
 *
 */
abstract class TimerObserver<Observer> {

    private val delay: Long
    private val period: Long
    private val timer = Timer()
    private val task: TimerTask = object : TimerTask() {
        override fun run() {
            if (observer != null) update(observer)
        }
    }

    var observer: Observer? = null

    constructor(period: Long, delay: Long) {
        this.period = period
        this.delay = delay
    }
    constructor(period: Long) : this(0, period)

    protected abstract fun update(observer: Observer?)

    fun start() {
        timer.schedule(task, delay, period)
        task.run()
    }
    fun cancel() {
        task.cancel()
        timer.purge()
        timer.cancel()
    }
    fun clear() {
        observer = null
    }

}