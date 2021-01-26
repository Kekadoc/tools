package com.qeapp.tools

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