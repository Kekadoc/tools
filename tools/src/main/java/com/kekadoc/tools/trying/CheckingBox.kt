package com.kekadoc.tools.trying

import java.util.*

/** Механизм обработки объекта с возможностиью проверки на условия  */
abstract class CheckingBox<T> : Checking<T>, Checking.Events<T> {
    private var checking: MutableList<Checking<T>>? = null

    /** Добавить условия проверки  */
    fun addChecking(check: Checking<T>) {
        if (checking == null) checking = ArrayList()
        checking!!.add(check)
    }
    /** Добавить условия проверки  */
    fun removeChecking(check: Checking<T>?) {
        checking?.remove(check)
    }

    @JvmOverloads
    fun check(data: T, result: Checking.Events<T>? = null): Boolean {
        val processor = Checking.Processor(data, this, result!!)
        var check = checking(data, processor)
        if (processor.isStopped) {
            val apply = processor.apply()
            if (apply != check) throw RuntimeException()
            return check
        }
        checking?.let {
            for (checking in it) {
                check = checking.checking(data, processor)
                if (processor.isStopped) {
                    val apply = processor.apply()
                    if (apply != check) throw RuntimeException()
                    return check
                }
            }
        }
        val apply = processor.apply()
        if (apply != check) throw RuntimeException()
        return check
    }
    @JvmOverloads
    fun attempt(data: T, result: Checking.Events<T>? = null): Boolean {
        return check(data, object : Checking.Events<T> {
            override fun onCheckingFail(target: T?, log: String?, code: Int) {
                result?.onCheckingFail(target, log, code)
            }
            override fun onCheckingSuccess(target: T?, code: Int) {
                result?.onCheckingSuccess(target, code)
                action(data)
            }
        })
    }

    /** То что нужно попытаться сделать  */
    protected abstract fun action(data: T)

    companion object {
        private const val TAG = "Trying-TAG"
    }
}