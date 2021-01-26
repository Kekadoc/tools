package com.qeapp.tools.action

interface ObservableSimpleAction : SimpleAction {
    fun addSimpleActionObserver(observer: SimpleActionObserver)
    fun removeSimpleActionObserver(observer: SimpleActionObserver?)
}