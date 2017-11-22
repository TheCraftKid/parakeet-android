package com.thecraftkid.parakeet.model

/**
 * @since v1.0.0 (11/19/2017)
 * @version 1.0.0
 */
data class Teacher(var name: String, var email: String?, var website: String?) {
    constructor() : this("", null, null)
}