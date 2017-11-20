package com.thecraftkid.parakeet.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.thecraftkid.parakeet.livedata.AssignmentLiveData;
import com.thecraftkid.parakeet.model.Assignment;

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
