package co.cubeflow.parakeet.model

/**
 * @since 1.0.0
 * @version 1.0.0
 */
data class Classroom(var name: String, var teachers: Map<String, Boolean>?, var startTime: Long,
                     var endTime: Long, var notes: String?, var room: String?) {
    constructor() : this("", null, 0, 0, null, null)
}