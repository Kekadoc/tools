package com.qeapp.tools.builder

/**
 * Абстрактный строитель
 *
 * @param <Result> Результат строителя, то что возвращает [.build]
 * @param <Params> Параметры для построения котороы формируются в процессе и передаются в [.onCreateResult]
</Params></Result> */
abstract class QeBuilder<Result, Params> {

    interface OnBuildObserver<Builder : QeBuilder<Result, *>?, Result> {
        fun onBuild(builder: Builder, result: Result)
    }

    private var observer: OnBuildObserver<QeBuilder<Result, Params>, Result>? = null

    private val params: Params? by lazy { onCreateParams() }

    /**   */
    fun build(): Result {
        val result = onCreateResult(params)
        applyResult(result)
        if (observer != null) observer!!.onBuild(this, result)
        return result
    }

    fun setBuildObserver(observer: OnBuildObserver<QeBuilder<Result, Params>, Result>?) {
        this.observer = observer
    }

    private fun applyResult(result: Result) {}

    /**
     * Окончательное построение объекта.
     * */
    protected abstract fun onCreateResult(param: Params?): Result

    /**
     *
     * */
    protected abstract fun onCreateParams(): Params?

}