package com.qeapp.tools.data

interface RefreshData<T> {
    fun onRefresh(data: T?)
}