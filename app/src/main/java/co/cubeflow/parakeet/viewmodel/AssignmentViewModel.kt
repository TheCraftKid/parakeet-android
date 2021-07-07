package co.cubeflow.parakeet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import co.cubeflow.parakeet.livedata.AssignmentLiveData
import co.cubeflow.parakeet.model.Assignment

/**
 * @version 1.0.0
 * @since v1.0.0 (11/19/2017)
 */
class AssignmentViewModel : ViewModel() {

    private lateinit var assignment: LiveData<Assignment>

    /**
     *
     * @param classId
     * @param assignmentId
     * @return
     */
    fun getAssignment(classId: String, assignmentId: String): LiveData<Assignment> {
        if (assignment.value == null) {
            assignment = AssignmentLiveData(classId, assignmentId)
        }
        return assignment
    }
}
