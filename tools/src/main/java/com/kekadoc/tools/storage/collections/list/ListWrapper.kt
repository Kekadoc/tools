package com.kekadoc.tools.storage.collections.list

import java.util.*

open class ListWrapper<T> @JvmOverloads constructor(private var list: MutableList<T?> = ArrayList()) : MutableList<T?> {

    protected open fun onItemAdded(item: T?, index: Int) {}
    protected open fun onItemRemoved(item: T?, index: Int) {}
    protected open fun onSorted() {}

    fun setWrappedList(list: MutableList<T?>) {
        this.list = list
    }
    fun getWrappedList() : MutableList<T?> {
        return this.list
    }

    override fun sort(c: Comparator<in T?>?) {
        Collections.sort(list, c)
        onSorted()
    }

    override val size: Int
        get() = list.size

    override fun isEmpty(): Boolean = list.isEmpty()
    override fun contains(element: T?): Boolean = list.contains(element)
    override fun iterator(): MutableIterator<T?> = list.iterator()
    override fun add(element: T?): Boolean {
        val b = list.add(element)
        if (b) onItemAdded(element, list.indexOf(element))
        return b
    }
    override fun remove(element: T?): Boolean {
        if (element == null) return false
        val index = list.indexOf(element)
        if (index < 0) return false
        val b = list.remove(element)
        if (b) onItemRemoved(element, index)
        return b
    }
    override fun containsAll(elements: Collection<T?>): Boolean = list.containsAll(elements)
    override fun addAll(elements: Collection<T?>): Boolean {
        val b = list.addAll(elements)
        if (b) for (t in elements) if (list.contains(t)) onItemAdded(t, list.indexOf(t))
        return b
    }
    override fun addAll(index: Int, elements: Collection<T?>): Boolean {
        val b = list.addAll(index, elements)
        if (b) for (t in elements) if (list.contains(t)) onItemAdded(t, list.indexOf(t))
        return b
    }
    override fun removeAll(elements: Collection<T?>): Boolean {
        val removed: MutableMap<Int, T?> = mutableMapOf()
        for (o in elements) if (list.contains(o)) removed[list.indexOf(o)] = o
        val b = list.removeAll(elements)

        if (b) for (entry in removed) {
            val t: T? = entry.value
            val index = entry.key
            if (!list.contains(t)) onItemRemoved(t, index)
        }
        return b
    }
    override fun retainAll(elements: Collection<T?>): Boolean = list.retainAll(elements)
    override fun clear() {
        for (i in 0 until size) onItemRemoved(get(i), i)
        list.clear()
    }

    override fun get(index: Int): T? = list[index]
    override fun set(index: Int, element: T?): T? {
        val old = list.set(index, element)
        onItemRemoved(old, index)
        onItemAdded(element, index)
        return old
    }
    override fun add(index: Int, element: T?) {
        list.add(index, element)
        onItemAdded(element, index)
    }

    override fun removeAt(index: Int): T? {
        val item: T? = list.removeAt(index)
        item?.let { onItemRemoved(it, index) }
        return item
    }
    override fun indexOf(element: T?): Int = list.indexOf(element)

    override fun lastIndexOf(element: T?): Int {
        return list.lastIndexOf(element)
    }
    override fun listIterator(): MutableListIterator<T?> {
        return list.listIterator()
    }
    override fun listIterator(index: Int): MutableListIterator<T?> {
        return list.listIterator(index)
    }
    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T?> {
        return list.subList(fromIndex, toIndex)
    }

    override fun equals(other: Any?): Boolean {
        return other is ListWrapper<*> && other.list == list
    }
    override fun toString(): String {
        return super.toString() + " ListWrapper: " + list.javaClass.simpleName + " count: " + list.size
    }

    override fun hashCode(): Int {
        return list.hashCode()
    }

}