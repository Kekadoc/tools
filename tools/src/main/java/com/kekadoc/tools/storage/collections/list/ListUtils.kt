package com.kekadoc.tools.storage.collections.list

import com.kekadoc.tools.storage.collections.CollectionUtils
import java.lang.RuntimeException
import kotlin.collections.ArrayList

object ListUtils {

    @JvmStatic
    fun <T> List<T>.isLast(item: T) : Boolean {
        if(!contains(item)) throw RuntimeException("Item is not contain in List!!!")
        return last()!! == item
    }
    @JvmStatic
    fun <T> List<T>.getPrevious(item: T) : T? {
        if (!contains(item) || indexOf(item) == 0) return null
        return get(indexOf(item) - 1)
    }
    @JvmStatic
    fun <T> List<T>.getNext(item: T) : T? {
        if (!contains(item) || last() == item) return null
        return get(indexOf(item) + 1)
    }

    @JvmStatic
    fun <T> nonNull(list: List<T>?) : List<T> {
        return list ?: emptyList()
    }
    @JvmStatic
    fun <T> copy(list: List<T>?) : List<T> {
        return if (list == null || list.isEmpty()) emptyList() else ArrayList<T>(list)
    }

    @JvmStatic
    fun <T> getItemOrNull(list: List<T?>?, index: Int) : T? {
        if (list == null) return null
        if (list.size >= index) return null
        return list[index]
    }

    @JvmStatic
    fun <T> toList(collection: Collection<T>?): List<T> {
        return collection?.let { ArrayList(it) } ?: emptyList()
    }
    @JvmStatic
    @SafeVarargs
    fun <T> listOf(vararg item: T): List<T> {
        return ArrayList(kotlin.collections.listOf(*item))
    }
    @JvmStatic
    @SafeVarargs
    fun <T> listOf(c: Collection<T>, vararg item: T): List<T> {
        val list: MutableList<T> = ArrayList(c)
        list.addAll(kotlin.collections.listOf(*item))
        return list
    }
    @JvmStatic
    fun <T> getLast(list: List<T>?): T? {
        return if (CollectionUtils.isEmpty(list)) null else list!!.last()
    }


    @JvmStatic
    fun <E, R> build(array: Array<E>, builder: (element: E) -> R): List<R> {
        return build(array, arrayListOf(), builder)
    }
    @JvmStatic
    fun <E, R, ListResult : MutableList<R>> build(array: Array<E>, listResult: ListResult, builder: (element: E) -> R): ListResult {
        for (e in array)
            listResult.add(builder.invoke(e))
        return listResult
    }

    @JvmStatic
    fun <E, R> build(list: Collection<E>, builder: (element: E) -> R): List<R> {
        return build(list, arrayListOf(), builder)
    }
    @JvmStatic
    fun <E, R, ListResult : MutableList<R>> build(list: Collection<E>, listResult: ListResult, builder: (element: E) -> R): List<R> {
        for (e in list) listResult.add(builder.invoke(e))
        return listResult
    }

    @JvmStatic
    fun <K, V, R> build(map: Map<K, V>, builder: (key: K, value: V) -> R): List<R>? {
        return build(map, arrayListOf(), builder)
    }
    @JvmStatic
    fun <K, V, R, ListResult : MutableList<R>> build(map: Map<K, V>, listResult: ListResult, builder: (key: K, value: V) -> R): List<R> {
        for (entry in map)
            listResult.add(builder.invoke(entry.key, entry.value))
        return listResult
    }

}