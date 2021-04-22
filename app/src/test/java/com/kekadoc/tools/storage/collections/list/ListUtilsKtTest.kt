package com.kekadoc.tools.storage.collections.list

import org.junit.Test

import org.junit.Assert.*

class ListUtilsKtTest {

    @Test
    fun addToListWithCompare() {

        val comparator = Comparator<Int> { o1, o2 -> o1.compareTo(o2) }
        val list = arrayListOf<Int>(0, 2, 6, 7)

        list.add(1, comparator)
        list.add(10, comparator)

        println(list)

        assert(list[1] == 1 && list[5] == 10)

    }

}