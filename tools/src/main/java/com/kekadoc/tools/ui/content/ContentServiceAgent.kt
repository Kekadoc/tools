package com.kekadoc.tools.ui.content

class ContentServiceAgent(adapter: Adapter? = null) : ContentService {

    var adapter: Adapter? = adapter
        set(value) {
            field?.invokeOnDetach(this)
            field = value
            value?.invokeOnAttach(this)
        }

    override fun content(code: Int): Content {
        return adapter?.invokeOnContent(code) ?: Content.Empty
    }
    override fun message(code: Int): Content.Message {
        return adapter?.invokeOnMessage(code) ?: Content.Message.Empty
    }
    override fun loading(code: Int): Content.Progress {
        return adapter?.invokeOnLoading(code) ?: Content.Progress.Empty
    }
    override fun notify(code: Int) {
        adapter?.invokeOnNotify(code)
    }

    fun clear() {
        adapter?.invokeOnClear(this)
        this.adapter = null
    }

    abstract class Adapter {

        protected abstract fun onContent(code: Int): Content
        protected abstract fun onMessage(code: Int): Content.Message
        protected abstract fun onLoading(code: Int): Content.Progress
        protected abstract fun onNotify(code: Int)

        protected open fun onAttach(service: ContentServiceAgent) {}
        protected open fun onDetach(service: ContentServiceAgent) {}
        protected open fun onClear(service: ContentServiceAgent) {}

        internal fun invokeOnContent(code: Int): Content {
            return onContent(code)
        }
        internal fun invokeOnMessage(code: Int): Content.Message {
            return onMessage(code)
        }
        internal fun invokeOnLoading(code: Int): Content.Progress {
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