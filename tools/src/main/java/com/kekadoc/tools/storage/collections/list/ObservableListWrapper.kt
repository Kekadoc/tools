package com.kekadoc.tools.storage.collections.list

import java.util.*

class ObservableListWrapper<T> @JvmOverloads constructor(list: MutableList<T?> = ArrayList()) : ListWrapper<T?>(list), ObservableList<T?> {

    private var observers: MutableCollection<ListObserver<T?>>? = null

    override fun onItemAdded(item: T?, index: Int) {
        observers?.let {
            for (observer in it) observer.onListItemAdded(this, item!!, index)
        }
    }
    override fun onItemRemoved(item: T?, index: Int) {
        observers?.let {
            for (observer in it) observer.onListItemRemoved(this, item!!, index)
        }
    }
    override fun onSorted() {
        observers?.let {
            for (observer in it) observer.onListSorted(this)
        }
    }
    override fun addListObserver(listener: ListObserver<T?>) {
        if (observers == null) observers = LinkedHashSet()
        observers!!.add(listener)
    }
    override fun removeListObserver(listener: ListObserver<T?>) {
        observers?.remove(listener)
    }

}