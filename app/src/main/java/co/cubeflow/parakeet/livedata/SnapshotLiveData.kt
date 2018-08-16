package co.cubeflow.parakeet.livedata

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*

/**
 * @version 1.0.0
 * @since v0.1.0 (11/19/2017)
 */
abstract class SnapshotLiveData<T>(private val tClass: Class<T>,
                                   private val reference: DocumentReference) :
        LiveData<T>(), EventListener<DocumentSnapshot> {

    private val registration: ListenerRegistration = reference.addSnapshotListener(this)

    override fun onActive() {
        super.onActive()
        registration.remove()
    }

    override fun onInactive() {
        super.onInactive()
        reference.addSnapshotListener(this)
    }

    override fun onEvent(documentSnapshot: DocumentSnapshot?, e: FirebaseFirestoreException?) {
        value = documentSnapshot!!.toObject(tClass)
    }
}
