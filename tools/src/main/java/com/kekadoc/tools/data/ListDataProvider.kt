package com.kekadoc.tools.data

interface ListDataProvider<D> {
    fun getListData(): List<D>
}