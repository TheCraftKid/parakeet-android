package co.cubeflow.parakeet.livedata

import co.cubeflow.parakeet.model.Assignment
import com.google.firebase.firestore.FirebaseFirestore

/**
 * @version 1.0.0
 * @since v1.0.0 (11/19/2017)
 */
class AssignmentLiveData(classId: String, assignmentId: String) :
        SnapshotLiveData<Assignment>(
                Assignment::class.java,
                FirebaseFirestore.getInstance().collection("classes")
                        .document(classId).collection("assignments").document(assignmentId)
        )
