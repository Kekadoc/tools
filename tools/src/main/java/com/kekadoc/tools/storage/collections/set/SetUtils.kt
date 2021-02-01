package com.kekadoc.tools.storage.collections.set

object SetUtils {

    @JvmStatic
    fun <E, R> build(array: Array<E>, builder: (element: E) -> R): Set<R> {
        return build(array, mutableSetOf(), builder)
    }
    @JvmStatic
    fun <E, R, ListResult : MutableSet<R>> build(array: Array<E>, listResult: ListResult, builder: (element: E) -> R): ListResult {
        for (e in array)
            listResult.add(builder.invoke(e))
        return listResult
    }

    @JvmStatic
    fun <E, R> build(collection: Collection<E>, builder: (element: E) -> R): Set<R> {
        return build(collection, mutableSetOf(), builder)
    }
    @JvmStatic
    fun <E, R, ListResult : MutableSet<R>> build(list: Collection<E>, listResult: ListResult, builder: (element: E) -> R): Set<R> {
        for (e in list) listResult.add(builder.invoke(e))
        return listResult
    }

    @JvmStatic
    fun <K, V, R> build(map: Map<K, V>, builder: (key: K, value: V) -> R): Set<R> {
        return build(map, mutableSetOf(), builder)
    }
    @JvmStatic
    fun <K, V, R, ListResult : MutableSet<R>> build(map: Map<K, V>, listResult: ListResult, builder: (key: K, value: V) -> R): Set<R> {
        for (entry in map)
            listResult.add(builder.invoke(entry.key, entry.value))
        return listResult
    }

}