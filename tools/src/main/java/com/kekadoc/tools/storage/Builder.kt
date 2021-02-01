package com.kekadoc.tools.storage

object Builder {

    interface LongBuilding<T> {
        fun getLong(target: T): Long
    }
    interface IntBuilding<T> {
        fun getInt(target: T): Int
    }

    @JvmStatic
    fun <T> buildLong(collection: Collection<T>, builder: LongBuilding<T>) {
        var long = 0L
        for (t in collection) long += builder.getLong(t)
    }
    @JvmStatic
    fun <T> buildInt(collection: Collection<T>, builder: IntBuilding<T>) {
        var int = 0
        for (t in collection) int += builder.getInt(t)
    }

}