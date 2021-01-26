package com.qeapp.tools.builder

/**
 * Абстрактный строитель объекта
 *
 * @param <I> Объект который будет строится
 * @param <Self> Сам строитель
</Self></I> */
abstract class QeInstanceBuilder<I>(private val instance: I) {

    interface OnBuildObserver<Builder : QeInstanceBuilder<I>, I> {
        fun onBuild(builder: Builder, instance: I)
    }

    private var observer: OnBuildObserver<QeInstanceBuilder<I>, I>? = null

    protected fun onBuild(instance: I) {}

    fun build(): I {
        observer?.onBuild(this, instance)
        onBuild(instance)
        return instance
    }

    protected fun instance(): I {
        return instance
    }

}