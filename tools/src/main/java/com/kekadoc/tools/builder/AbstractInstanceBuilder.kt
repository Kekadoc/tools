package com.kekadoc.tools.builder

/**
 * Abstract builder
 * @param [I] Instance
 */
abstract class AbstractInstanceBuilder<I>(private val instance: I) {

    private var observer: ((result: I?) -> Unit)? = null

    /**
     * OnBuild observing
     * Call after [onCreateResult]
     */
    fun onBuild(observer: ((result: I?) -> Unit)?) {
        this.observer = observer
    }

    protected open fun onBuild(instance: I) {}

    fun build(): I {
        observer?.invoke(instance)
        onBuild(instance)
        return instance
    }

    protected fun instance(): I {
        return instance
    }

}