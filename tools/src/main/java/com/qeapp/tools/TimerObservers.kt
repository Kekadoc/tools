package com.qeapp.tools

import java.util.*

/**
 * Класс для создания переодического действия с указанными объектами
 *
 */
abstract class TimerObservers<Observer> {

    private val delay: Long
    private val period: Long
    private val timer = Timer()
    private val task: TimerTask = object : TimerTask() {
        override fun run() {
            for (target in observers) {
                update(target)
            }
        }
    }
    private val observers: MutableCollection<Observer> = ArrayList()

    constructor(delay: Long, period: Long) {
        this.delay = delay
        this.period = period
    }
    constructor(period: Long) : this(0, period)

    protected abstract fun update(observer: Observer)

    fun addObserver(observer: Observer) {
        observers.add(Objects.requireNonNull(observer))
    }
    fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

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
        observers.clear()
    }

}