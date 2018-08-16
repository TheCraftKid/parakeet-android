package co.cubeflow.parakeet.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import co.cubeflow.parakeet.livedata.AssignmentLiveData;
import co.cubeflow.parakeet.model.Assignment;

/**
 * @version 1.0.0
 * @since v1.0.0 (11/19/2017)
 */
public class AssignmentViewModel extends ViewModel {

    private LiveData<Assignment> assignment;

    /**
     *
     * @param classId
     * @param assignmentId
     * @return
     */
    public LiveData<Assignment> getAssignment(String classId, String assignmentId) {
        if (assignment == null) {
            assignment = new AssignmentLiveData(classId, assignmentId);
        }
        return assignment;
    }
}
