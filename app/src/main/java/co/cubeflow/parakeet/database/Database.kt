package co.cubeflow.parakeet.database

import co.cubeflow.parakeet.model.Assignment
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

/**
 * A class that manages upload and download of data from the backend server.
 *
 * @version 1.0.0
 * @since v1.0.0 (11/19/2017)
 */
class Database private constructor() {

    private val database = FirebaseFirestore.getInstance()

    private fun getClassReference(classId: String): DocumentReference {
        return database.collection(PATH_CLASSES).document(classId)
    }

    fun upload(classId: String, assignment: Assignment): Task<Void> {
        return getClassReference(classId).collection(PATH_ASSIGNMENTS)
                .document().set(assignment)
    }

    companion object {

        private const val PATH_CLASSES = "classes"

        private const val PATH_ASSIGNMENTS = "assignments"

        private var INSTANCE: Database? = null

        val instance: Database
            get() {
                if (INSTANCE == null) {
                    INSTANCE = Database()
                }
                return INSTANCE as Database
            }
    }
}
