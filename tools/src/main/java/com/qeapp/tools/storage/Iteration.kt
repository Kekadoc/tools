package com.qeapp.tools.storage

import com.qeapp.tools.storage.collections.list.QeList
import java.util.*
import kotlin.math.max

object Iteration {

    interface SingleIndexing<T> {
        fun iteration(item: T, index: Int)
    }
    interface Single<T> {
        fun iteration(item: T)
    }
    interface Twin<A, B> {
        fun iteration(key: A, `val`: B)
    }


    /** Парная итерация по двум листам.
     * @param iteration будет вызываться до тех пор пока у элемента одно из списка есть порядковый парный элимент из второго.
     */
    @JvmStatic
    fun <First, Second> twinsIteration(first: List<First?>, second: List<Second?>, iterator: (element_0: First?, element_1: Second?) -> Unit) {
        val count = max(first.size, second.size)
        for (i in 0 until count) {
            val itemFirst: First? = QeList.getItemOrNull(first, i)
            val itemSecond: Second? = QeList.getItemOrNull(second, i)
            iterator.invoke(itemFirst, itemSecond)
        }
    }

    /** Проходит по коллекции и проверяет нужно ли удалять данный элемент коллекции  */
    @JvmStatic
    fun <E> removeIterator(list: MutableCollection<E>, removeCheck: (e: E) -> Boolean) {
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (removeCheck.invoke(item)) iterator.remove()
        }
    }
    @JvmStatic
    fun <T> safetyIterator(collection: Collection<T>?, iterator: Single<T>, mustBeContain: Boolean) {
        if (collection == null) return
        val c: MutableList<T> = ArrayList(collection)
        for (i in c.indices) {
            val t = c[i]
            if (mustBeContain) {
                if (collection.contains(t)) iterator.iteration(t)
            } else iterator.iteration(t)
        }
        c.clear()
    }
    @JvmStatic
    fun <T> safetyIterator(collection: Collection<T>?, iterator: Single<T>) {
        safetyIterator(collection, iterator, false)
    }
    @JvmStatic
    fun <T> iterate(list: List<T>, iterator: SingleIndexing<T>) {
        for (i in list.indices) iterator.iteration(list[i], i)
    }
    @JvmStatic
    fun <T> iterate(array: Array<out T>, iterator: SingleIndexing<T>) {
        for (i in array.indices) iterator.iteration(array[i], i)
    }
    @JvmStatic
    @SafeVarargs
    inline fun <reified T> iterate(iterator: SingleIndexing<T>, vararg array: T) {
        iterate(array, iterator)
    }
    @JvmStatic
    fun <T> iterate(collection: Collection<T>, iterator: Single<T>) {
        for (t in collection) iterator.iteration(t)
    }
    @JvmStatic
    fun <T> iterate(array: Array<out T>, iterator: Single<T>) {
        for (t in array) iterator.iteration(t)
    }
    @JvmStatic
    @SafeVarargs
    fun <T> iterate(iterator: Single<T>, vararg array: T) {
        iterate(array, iterator)
    }
    @JvmStatic
    fun <K, V> iterate(map: Map<K, V>, iterator: Twin<K, V>) {
        for ((key, value) in map) iterator.iteration(key, value)
    }

}