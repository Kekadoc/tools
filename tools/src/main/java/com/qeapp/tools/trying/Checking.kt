package com.qeapp.tools.trying

import java.util.*

interface Checking<T> {

    class Processor<T> {

        private val target: T
        private val events: Events<T>?
        var isStopped = false
        private var failLog: String? = null
        private var failCode = 0
        private var successCode = 0

        @SafeVarargs
        constructor(target: T, vararg events: Events<T>) {
            this.target = target
            this.events = Events.Manager(*events)
        }
        constructor(target: T, events: Events<T>) {
            this.target = target
            this.events = events
        }

        fun stop(): Processor<T> {
            isStopped = true
            return this
        }

        @JvmOverloads
        fun fail(log: String?, code: Int = 0): Processor<T> {
            if (isStopped) return this
            failLog = log
            failCode = code
            return this
        }

        @JvmOverloads
        fun success(code: Int = 0): Processor<T> {
            if (isStopped) return this
            successCode = code
            return this
        }

        fun isSuccess(): Boolean = failLog == null

        /**
         * Apply
         * @return true if success, false if fail
         */
        fun apply(): Boolean {
            return if (failLog != null) {
                events?.onCheckingFail(target, failLog, failCode)
                false
            } else {
                events?.onCheckingSuccess(target, successCode)
                true
            }
        }

    }

    interface Events<T> {

        /**
         *
         */
        fun onCheckingFail(target: T?, log: String?, code: Int) {}
        /**
         *
         */
        fun onCheckingSuccess(target: T?, code: Int) {}

        class Manager<T> @SafeVarargs constructor(vararg events: Events<T>) : Events<T> {

            private val list: MutableList<Events<T>> = ArrayList()

            override fun onCheckingFail(target: T?, log: String?, code: Int) {
                for (event in list) event.onCheckingFail(target, log, code)
            }
            override fun onCheckingSuccess(target: T?, code: Int) {
                for (event in list) event.onCheckingSuccess(target, code)
            }

            init {
                list.addAll(listOf(*events))
            }

        }

    }

    /**
     *
     * Проверка условий
     *
     * @return true if success or false on fail
     */
    fun checking(target: T?, processor: Processor<T>): Boolean

}