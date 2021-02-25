package com.kekadoc.tools.content.ui

open class SimpleContent : ContentUI {

    private var shown = false

    protected open fun onShown() {}
    protected open fun onHide() {}

    override fun isShown() = shown
    override fun show() {
        if (shown) return
        shown = true
        onShown()
    }
    override fun hide() {
        if (!shown) return
        shown = false
        onHide()
    }

}