package com.kekadoc.tools.content.ui

class SimpleMessage(var message: CharSequence? = null) : SimpleContent(), ContentUI.Message {

    override fun getText(): CharSequence? = message
    override fun setText(text: CharSequence?) {
        message = text
    }

}