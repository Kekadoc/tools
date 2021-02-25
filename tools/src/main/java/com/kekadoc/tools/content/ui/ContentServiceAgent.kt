package com.kekadoc.tools.content.ui

class ContentServiceAgent(adapter: Adapter? = null) : ContentService {

    var adapter: Adapter? = adapter
        set(value) {
            field?.invokeOnDetach(this)
            field = value
            value?.invokeOnAttach(this)
        }

    override fun content(code: Int): ContentUI {
        return adapter?.invokeOnContent(code) ?: ContentUI.Empty
    }
    override fun message(code: Int): ContentUI.Message {
        return adapter?.invokeOnMessage(code) ?: ContentUI.Message.Empty
    }
    override fun loading(code: Int): ContentUI.Loading {
        return adapter?.invokeOnLoading(code) ?: ContentUI.Loading.Empty
    }
    override fun notify(code: Int) {
        adapter?.invokeOnNotify(code)
    }

    fun clear() {
        adapter?.invokeOnClear(this)
        this.adapter = null
    }

    abstract class Adapter {

        protected abstract fun onContent(code: Int): ContentUI
        protected abstract fun onMessage(code: Int): ContentUI.Message
        protected abstract fun onLoading(code: Int): ContentUI.Loading
        protected abstract fun onNotify(code: Int)

        protected open fun onAttach(service: ContentServiceAgent) {}
        protected open fun onDetach(service: ContentServiceAgent) {}
        protected open fun onClear(service: ContentServiceAgent) {}

        internal fun invokeOnContent(code: Int): ContentUI {
            return onContent(code)
        }
        internal fun invokeOnMessage(code: Int): ContentUI.Message {
            return onMessage(code)
        }
        internal fun invokeOnLoading(code: Int): ContentUI.Loading {
            return onLoading(code)
        }
        internal fun invokeOnNotify(code: Int) {
            onNotify(code)
        }
        internal fun invokeOnAttach(service: ContentServiceAgent) {
            onAttach(service)
        }
        internal fun invokeOnDetach(service: ContentServiceAgent) {
            onDetach(service)
        }
        internal fun invokeOnClear(service: ContentServiceAgent) {
            onClear(service)
        }

    }

}