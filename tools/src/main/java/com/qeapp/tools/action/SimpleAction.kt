package com.qeapp.tools.action

interface SimpleAction {

    interface Observable : SimpleAction {
        fun addSimpleActionObserver(observer: Observer)
        fun removeSimpleActionObserver(observer: Observer?)
    }

    interface Observer {
        fun onStart()
        fun onFinish()
    }

    fun run()

}