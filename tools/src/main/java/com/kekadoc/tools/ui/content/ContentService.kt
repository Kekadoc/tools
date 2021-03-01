package com.kekadoc.tools.ui.content

/**
 * Interface for abstract ui content
 */
interface ContentService {

    companion object {
        const val EMPTY_CODE = 0
    }

    /**
     * Show content
     * @param code Unique code
     * @return [Content]
     */
    fun content(code: Int = EMPTY_CODE): Content
    /**
     * Show message
     * @param code Unique code
     * @return [Message]
     */
    fun message(code: Int = EMPTY_CODE): Content.Message
    /**
     * Start loading in content with code
     * @param code Unique code
     * @return [Loading]
     */
    fun loading(code: Int = EMPTY_CODE): Content.Progress

    /**
     * Notify content with code
     *
     * @param code Unique code
     */
    fun notify(code: Int = EMPTY_CODE)

}