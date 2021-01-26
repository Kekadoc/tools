package com.qeapp.tools.storage

object CheckableIteration {

    @JvmStatic
    fun <T> check(list: List<T>, check: SingleIndexing<T>): Boolean {
        for (i in list.indices) {
            if (!check.iteration(list[i], i)) return false
        }
        return true
    }

    @JvmStatic
    fun <T> check(array: Array<out T>, check: SingleIndexing<T>): Boolean {
        for (i in array.indices) {
            if (!check.iteration(array[i], i)) return false
        }
        return true
    }

    @JvmStatic
    @SafeVarargs
    inline fun <reified T> check(check: SingleIndexing<T>, vararg array: T): Boolean {
        return check(array, check)
    }

    @JvmStatic
    fun <T> check(collection: Collection<T>, check: Single<T>): Boolean {
        for (t in collection) {
            if (!check.iteration(t)) return false
        }
        return true
    }

    @JvmStatic
    fun <T> check(array: Array<out T>, check: Single<T>): Boolean {
        for (t in array) {
            if (!check.iteration(t)) return false
        }
        return true
    }

    @JvmStatic
    @SafeVarargs
    fun <T> check(check: Single<T>, vararg array: T): Boolean {
        return check(array, check)
    }

    @JvmStatic
    fun <K, V> check(map: Map<K, V>, check: Twin<K, V>): Boolean {
        for ((key, value) in map) {
            if (!check.iteration(key, value)) return false
        }
        return true
    }

    interface SingleIndexing<T> {
        fun iteration(item: T, index: Int): Boolean
    }

    interface Single<T> {
        fun iteration(item: T): Boolean
    }

    interface Twin<A, B> {
        fun iteration(key: A, `val`: B): Boolean
    }

}