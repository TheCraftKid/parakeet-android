package com.thecraftkid.parakeet.model

/**
 * @since v1.0.0 (11/19/2017)
 * @version 1.0.0
 */
data class Classroom(var name: String, var teachers: Map<String, Boolean>?, var startTime: Long,
                     var endTime: Long, var notes: String?, var room: String?) {
    constructor(): this("", null, 0, 0, null, null)
}