package com.thecraftkid.parakeet.livedata;

import com.google.firebase.firestore.FirebaseFirestore;
import com.thecraftkid.parakeet.model.Assignment;

/**
 * @version 1.0.0
 * @since v1.0.0 (11/19/2017)
 */

public class AssignmentLiveData extends SnapshotLiveData<Assignment> {

    public AssignmentLiveData(String classId, String assignmentId) {
        super(Assignment.class, FirebaseFirestore.getInstance().collection("classes")
                .document(classId).collection("assignments").document(assignmentId));
    }
}
