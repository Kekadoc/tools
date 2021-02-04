package com.kekadoc.tools.data

abstract class DataAggregate {

    fun notifyData(kept: Int, desire: Int) {
        when {
            kept == desire -> return
            kept > desire -> {
                for (i in (kept - 1) downTo  desire)
                    removeData(i)
            }
            kept < desire -> {
                for (i in kept until desire)
                    createData(i)
            }
        }
    }

    abstract fun createData(index: Int)
    abstract fun removeData(index: Int)

}