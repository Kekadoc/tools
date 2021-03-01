package com.kekadoc.tools.ui

interface Visible {

    interface Mutable : Visible {
        fun setVisible(visible: Boolean)
    }

    fun isVisible(): Boolean
}