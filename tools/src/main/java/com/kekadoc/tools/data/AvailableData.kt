package com.kekadoc.tools.data

open class ReadAvailableData<T> @JvmOverloads constructor(private var data: T? = null, private val checker: (data: T?) -> Boolean) {

    fun isReadAvailable(): Boolean = checker.invoke(data)

    fun getDataOrNull(): T? {
        if (!isReadAvailable()) return null
        return data
    }
    fun getDataOrDefault(def: T?): T? {
        if (!isReadAvailable()) return def
        return data
    }
    fun requireData(): T? {
        return data
    }
    fun getData(): T? {
        require(isReadAvailable()) {"Data not available!"}
        return data
    }

    open fun setData(data: T?) {
        this.data = data
    }

}

open class ReadWriteAvailableData<T> @JvmOverloads constructor(
        data: T? = null,
        readChecker: (data: T?) -> Boolean,
        private val writeChecker: (oldData: T?, newData: T?) -> Boolean) : ReadAvailableData<T>(data, readChecker) {

    fun isWriteAvailable(newData: T?): Boolean = writeChecker.invoke(requireData(), newData)

    fun requireSetData(data: T?) {
        data.let {  }
        super.setData(data)
    }
    override fun setData(data: T?) {
        require(isWriteAvailable(data)) {"Data writing not possible!"}
        super.setData(data)
    }

}

fun <T> T.toReadAvailableData(checker: (data: T?) -> Boolean): ReadAvailableData<T> = ReadAvailableData(this, checker)
fun <T> T.toReadWriteAvailableData(checker: (data: T?) -> Boolean, writeChecker: (oldData: T?, newData: T?) -> Boolean):
        ReadAvailableData<T> = ReadWriteAvailableData(this, checker, writeChecker)