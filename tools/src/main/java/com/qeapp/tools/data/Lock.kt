package com.qeapp.tools.data

interface Lock {

    fun isLocked(): Boolean
    fun setLock(lock: Boolean)
    fun unlock() {
        setLock(false)
    }
    fun lock() {
        setLock(true)
    }

}