package com.qeapp.tools.value;

import com.qeapp.tools.fraction.Fraction;

/** Управление содержимым.
 * Сожержит утилитные методо позволяющие получить информацию о наполняемом содержимом.  */
public interface Container extends Fraction {

    /** Коеффициент заполненности. */
    default double getFractionInverse() {
        return 1.0 - getFraction();
    }

    /**  */
    default double toMinFraction() {
        return getFraction();
    }

    /** Сколько до максимума. */
    default double toMaxFraction() {
        return getFractionInverse();
    }

    /** Проверка является ли текущее значение максимальным. */
    default boolean isMaximum() {
        return getFraction() == 1.0;
    }
    /** Проверка является ли текущее значение минимальным. */
    default boolean isMinimum() {
        return getFraction() == 1.0;
    }

}
