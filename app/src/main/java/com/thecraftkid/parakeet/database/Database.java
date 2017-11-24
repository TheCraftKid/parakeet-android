package com.thecraftkid.parakeet.database;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.thecraftkid.parakeet.model.Assignment;

/**
 * A class that manages upload and download of data from the backend server.
 *
 * @version 1.0.0
 * @since v1.0.0 (11/19/2017)
 */
public class Database {

    private static final String PATH_CLASSES = "classes";

    private static final String PATH_ASSIGNMENTS = "assignments";

    private static Database INSTANCE;

    private FirebaseFirestore mDatabase;

    private Database() {
        mDatabase = FirebaseFirestore.getInstance();
    }

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    @NonNull
    private DocumentReference getClassReference(@NonNull String classId) {
        return mDatabase.collection(PATH_CLASSES).document(classId);
    }

    public Task<Void> upload(String classId, Assignment assignment) {
        return getClassReference(classId).collection(PATH_ASSIGNMENTS)
                .document().set(assignment);
    }
}
