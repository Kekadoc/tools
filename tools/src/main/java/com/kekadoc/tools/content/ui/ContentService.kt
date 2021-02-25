package com.kekadoc.tools.content.ui

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
     * @return [ContentUI]
     */
    fun content(code: Int = EMPTY_CODE): ContentUI
    /**
     * Show message
     * @param code Unique code
     * @return [Message]
     */
    fun message(code: Int = EMPTY_CODE): ContentUI.Message
    /**
     * Start loading in content with code
     * @param code Unique code
     * @return [Loading]
     */
    fun loading(code: Int = EMPTY_CODE): ContentUI.Loading

    /**
     * Notify content with code
     *
     * @param code Unique code
     */
    fun notify(code: Int = EMPTY_CODE)

}