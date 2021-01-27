package com.qeapp.tools.data

interface ListDataProvider<D> {
    fun getListData(): List<D>
}