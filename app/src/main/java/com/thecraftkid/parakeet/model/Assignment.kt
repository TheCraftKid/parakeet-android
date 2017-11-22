package com.thecraftkid.parakeet.model

/**
 * @since v1.0.0 (11/19/2017)
 * @version 1.0.0
 */
data class Assignment(var name: String, var notes: String?, var dueDate: Long,
                      var totalPoints: Int, var earnedPoints: Int) {
    constructor() : this("", null, 0, 0, 0)
}