package com.kekadoc.tools.data

fun <T> T.toOffer(): Offer<T> = Offer.Instance(this)

interface Offer<T> {

    fun get(): T

    fun accept()

    fun reject()

    fun isReviewed(): Boolean

    open class Instance<T> (private val data: T) : Offer<T> {

        private var accepted = false

        protected open fun onAccept() {}
        protected open fun onReject() {}

        final override fun get(): T = data
        final override fun accept() {
            accepted = true
            onAccept()
        }
        final override fun reject() {
            accepted = false
            onReject()
        }
        final override fun isReviewed(): Boolean = accepted

    }

}