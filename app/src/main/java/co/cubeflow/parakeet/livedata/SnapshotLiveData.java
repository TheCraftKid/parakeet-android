package co.cubeflow.parakeet.livedata;

import android.arch.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

/**
 * @version 1.0.0
 * @since v0.1.0 (11/19/2017)
 */
class SnapshotLiveData<T> extends LiveData<T> implements
        EventListener<DocumentSnapshot> {

    private Class<T> tClass;

    private DocumentReference reference;
    private ListenerRegistration registration;

    public SnapshotLiveData(Class<T> clazz, DocumentReference ref) {
        tClass = clazz;
        reference = ref;
        registration = ref.addSnapshotListener(this);
    }

    @Override
    protected void onActive() {
        super.onActive();
        registration.remove();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        reference.addSnapshotListener(this);
    }

    @Override
    public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
        setValue(documentSnapshot.toObject(tClass));
    }
}
