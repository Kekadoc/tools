package com.kekadoc.tools.storage.collections.list

import com.kekadoc.tools.data.ListDataProvider
import com.kekadoc.tools.storage.collections.list.ListUtils.getPrevious
import java.util.*

// TODO: 23.04.2021 Tests

class ClusteredList<T> : AbsClusteredList<List<T>, T>() {

    override fun getListData(holder: List<T>): List<T> {
        return holder
    }

}

class ClusteredListOfListDataProvider<Provider : ListDataProvider<T>, T> : AbsClusteredList<Provider, T>() {
    override fun getListData(holder: Provider): List<T> {
        return holder.getListData()
    }
}

abstract class AbsClusteredList<H, T> : MutableList<T> {

    val clusters: MutableList<H> = arrayListOf()

    fun findFirstIndex(holder: H): Int {
        if (getListData(holder).isNotEmpty()) return indexOf(getListData(holder).first())
        var prevHolder: H? = clusters.getPrevious(holder)
        while (prevHolder != null) {
            prevHolder = clusters.getPrevious(holder)
            prevHolder?.let {
                if (getListData(it).isNotEmpty()) return indexOf(getListData(it).last()) + 1
            }
        }
        return 0
    }

    protected abstract fun getListData(holder: H): List<T>

    fun clusterOf(item: T): H? {
        clusters.forEach {
            if (getListData(it).contains(item)) return it
        }
        return null
    }

    override val size: Int
        get() = if (clusters.isEmpty()) 0 else let {
            var size = 0
            clusters.forEach { size += getListData(it).size }
            size
        }

    override fun contains(element: T): Boolean {
        return let {
            clusters.forEach {
                if (getListData(it).contains(element)) return@let true
            }
            false
        }
    }
    override fun get(index: Int): T {
        var size = 0
        clusters.forEach {
            if (index <= (size + getListData(it).size) - 1) return getListData(it)[index - size]
            size += getListData(it).size
        }
        throw IndexOutOfBoundsException("index: $index")
    }
    override fun indexOf(element: T): Int {
        var size = 0
        clusters.forEach {
            val index = getListData(it).indexOf(element)
            if (index >= 0) return size + index
            size += getListData(it).size
        }
        return -1
    }
    override fun isEmpty(): Boolean {
        return size == 0
    }
    override fun lastIndexOf(element: T): Int {
        var size = 0
        (clusters.size - 1 downTo 0).forEach {
            val page = clusters[it]
            val index = getListData(page).lastIndexOf(element)
            if (index >= 0) return size + index
            size += getListData(page).size
        }
        return -1
    }
    override fun clear() {
        clusters.clear()
    }
    override fun iterator(): MutableIterator<T> {
        return Iterator()
    }
    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        val list = arrayListOf<T>()
        (fromIndex until toIndex).forEach { list.add(get(it)) }
        return list
    }

    @Deprecated(message = "Is not implemented", level = DeprecationLevel.ERROR)
    override fun listIterator(): MutableListIterator<T> {
        throw NotImplementedError()
    }
    @Deprecated(message = "Is not implemented", level = DeprecationLevel.ERROR)
    override fun listIterator(index: Int): MutableListIterator<T> {
        throw NotImplementedError()
    }
    @Deprecated(message = "Is not implemented", level = DeprecationLevel.ERROR)
    override fun containsAll(elements: Collection<T>): Boolean {
        throw NotImplementedError()
    }
    @Deprecated(message = "Is not implemented", level = DeprecationLevel.ERROR)
    override fun retainAll(elements: Collection<T>): Boolean {
        throw NotImplementedError()
    }
    @Deprecated(message = "Is not implemented", level = DeprecationLevel.ERROR)
    override fun set(index: Int, element: T): T {
        throw NotImplementedError()
    }
    @Deprecated(message = "Is not implemented", level = DeprecationLevel.ERROR)
    override fun removeAt(index: Int): T {
        throw NotImplementedError()
    }
    @Deprecated(message = "Is not implemented", level = DeprecationLevel.ERROR)
    override fun removeAll(elements: Collection<T>): Boolean {
        throw NotImplementedError()
    }
    @Deprecated(message = "Is not implemented", level = DeprecationLevel.ERROR)
    override fun remove(element: T): Boolean {
        throw NotImplementedError()
    }
    @Deprecated(message = "Is not implemented", level = DeprecationLevel.ERROR)
    override fun addAll(elements: Collection<T>): Boolean {
        throw NotImplementedError()
    }
    @Deprecated(message = "Is not implemented", level = DeprecationLevel.ERROR)
    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        throw NotImplementedError()
    }
    @Deprecated(message = "Is not implemented", level = DeprecationLevel.ERROR)
    override fun add(element: T): Boolean {
        throw NotImplementedError()
    }
    @Deprecated(message = "Is not implemented", level = DeprecationLevel.ERROR, replaceWith = ReplaceWith("ListOfList.clusters"))
    override fun add(index: Int, element: T) {
        throw NotImplementedError()
    }

    override fun toString(): String {
        val it = iterator()
        if (!it.hasNext()) return "[]"

        val sb = StringBuilder()
        sb.append('[')
        while (true) {
            val e: T = it.next()
            sb.append(if (e === this) "(this Collection)" else e)
            if (!it.hasNext()) return sb.append(']').toString()
            sb.append(',').append(' ')
        }
    }

    private inner class Iterator : MutableIterator<T> {

        private var index = 0

        override fun hasNext(): Boolean {
            return index < this@AbsClusteredList.size
        }
        override fun next(): T {
            return if (!this.hasNext()) {
                throw NoSuchElementException()
            } else {
                val item = this@AbsClusteredList[index]
                index ++
                item
            }
        }
        override fun remove() {
            throw UnsupportedOperationException("Operation is not supported for read-only collection")
        }

    }
}