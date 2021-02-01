package com.kekadoc.tools.storage.collections.list

interface ListObserver<T> {
    fun onListItemRemoved(list: List<T?>, item: T, position: Int) {}
    fun onListItemAdded(list: List<T?>, item: T, position: Int) {}
    fun onListSorted(list: List<T?>) {}
}