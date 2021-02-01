package com.kekadoc.tools.data

interface RefreshData<T> {
    fun onRefresh(data: T?)
}