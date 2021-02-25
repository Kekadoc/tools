package com.kekadoc.tools.content.ui

class ContentService(adapter: Adapter? = null) : ContentUI {

    var adapter: Adapter? = adapter
        set(value) {
            field?.invokeOnDetach(this)
            field = value
            value?.invokeOnAttach(this)
        }

    override fun content(code: Int): ContentUI.Content {
        return adapter?.invokeOnContent(code) ?: ContentUI.Content.Empty
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

        protected abstract fun onContent(code: Int): ContentUI.Content
        protected abstract fun onMessage(code: Int): ContentUI.Message
        protected abstract fun onLoading(code: Int): ContentUI.Loading
        protected abstract fun onNotify(code: Int)

        protected open fun onAttach(service: ContentService) {}
        protected open fun onDetach(service: ContentService) {}
        protected open fun onClear(service: ContentService) {}

        internal fun invokeOnContent(code: Int): ContentUI.Content {
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
        internal fun invokeOnAttach(service: ContentService) {
            onAttach(service)
        }
        internal fun invokeOnDetach(service: ContentService) {
            onDetach(service)
        }
        internal fun invokeOnClear(service: ContentService) {
            onClear(service)
        }

    }

}