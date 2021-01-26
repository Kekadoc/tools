package com.qeapp.tools.storage.collections

import com.qeapp.tools.QeObjects
import com.qeapp.tools.text.QeString
import java.util.*

object QeCollections {
    private const val TAG = "QeCollections-TAG"

    fun <T> collectionToString(c: Collection<T>?): String? {
        if (c == null) return "null"
        val builder = StringBuilder()
                .append(QeObjects.toSimpleString(c)).append(" size: ").append(c.size).append('\n')
        for (obj in c) {
            builder.append(obj.toString())
            builder.append('\n')
        }
        return builder.toString()
    }
    @JvmStatic
    fun <T> nonNull(collection: Collection<T>?) : Collection<T> {
        return collection ?: emptyList()
    }
    @JvmStatic
    fun <T> copy(collection: Collection<T>?) : Collection<T> {
        return if (collection == null || collection.isEmpty()) emptyList() else ArrayList<T>(collection)
    }

    @JvmStatic
    fun <T> toSimpleString(collection: Collection<T>?): String {
        if (collection == null) return QeString.NULL
        val builder = StringBuilder(QeObjects.toSimpleString(collection))
        builder.append("=").append("(").append(collection.size).append(")")
        builder.append("={")
        val iterator = collection.iterator()
        while (iterator.hasNext()) {
            val t = iterator.next()
            builder.append(QeObjects.toSimpleString(t))
            if (iterator.hasNext()) builder.append(", ")
        }
        builder.append("}")
        return builder.toString()
    }
    @JvmStatic
    fun <T> getFirstItem(collection: Collection<T>?): T? {
        if (collection == null) return null
        if (collection.isEmpty()) return null
        return if (collection is List<*>) (collection as List<T>)[0] else ArrayList(collection)[0]
    }
    @JvmStatic
    fun <T> isEmpty(c: Collection<T>?): Boolean {
        return c == null || c.isEmpty()
    }
    @JvmStatic
    @SafeVarargs
    fun <I> getWithoutIt(list: Collection<I>, vararg item: I): Collection<I> {
        val readyList = ArrayList(list)
        readyList.removeAll(listOf(*item))
        return readyList
    }
    @JvmStatic
    fun <T> copy(arr: Array<T>, copier: (obj: T) -> Boolean): Collection<T> {
        val list: MutableList<T> = ArrayList()
        for (t in arr) if (copier.invoke(t)) list.add(t)
        return list
    }
    @JvmStatic
    fun <T> copy(collection: Collection<T>, copier: (obj: T) -> Boolean): Collection<T> {
        val list: MutableList<T> = ArrayList()
        for (t in collection) if (copier.invoke(t)) list.add(t)
        return list
    }

}