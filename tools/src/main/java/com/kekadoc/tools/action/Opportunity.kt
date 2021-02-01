package com.kekadoc.tools.action

/**
 * Возможность
 */
interface Opportunity<S, U> {

    /**
     * Успешно
     */
    fun onSatisfactorily(success: S)
    /**
     * Неудача
     */
    fun onUnsatisfactory(fail: U)

}