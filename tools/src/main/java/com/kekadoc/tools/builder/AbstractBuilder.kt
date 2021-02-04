package com.kekadoc.tools.builder

/**
 * Abstract builder
 *
 * @param [Result] Result building [build]
 * @param [Params] Building params [onCreateResult]
 */
abstract class AbstractBuilder<Result, Params> {

    private var observer: ((result: Result) -> Unit)? = null
    private val params: Params by lazy { onCreateParams() }

    /**
     * Run building
     */
    open fun build(): Result {
        val result = onCreateResult(params)
        applyResult(result)
        observer?.invoke(result)
        return result
    }

    /**
     * OnBuild observing
     * Call after [onCreateResult]
     */
    fun onBuild(observer: ((result: Result) -> Unit)?) {
        this.observer = observer
    }

    /**
     * Call after [onCreateResult]
     */
    protected open fun applyResult(result: Result) {}

    /**
     * Building [Result]
     *
     * @param param - [Params]
     * @return [Result]
     * */
    protected abstract fun onCreateResult(param: Params): Result
    /**
     * Create [Params] for building [Result]
     */
    protected abstract fun onCreateParams(): Params

}