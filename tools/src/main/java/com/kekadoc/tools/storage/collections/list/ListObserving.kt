package com.kekadoc.tools.storage.collections.list

interface ListObserving<T> {
    fun addListObserver(listener: ListObserver<T>)
    fun removeListObserver(listener: ListObserver<T>)
}