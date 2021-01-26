package com.qeapp.tools

interface ListDataProvider<D> {
    fun getListData(): List<D>?
}