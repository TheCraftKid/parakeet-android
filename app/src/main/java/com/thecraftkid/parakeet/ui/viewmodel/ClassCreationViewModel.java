package com.thecraftkid.parakeet.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.thecraftkid.parakeet.model.Classroom;

/**
 * @version 1.0.0
 * @since v1.0.0 (11/25/2017)
 */
public class ClassCreationViewModel extends ViewModel {

    private MutableLiveData<Classroom> classroom = new MutableLiveData<>();

    public LiveData<Classroom> getClassroom() {
        if (classroom == null) {
            classroom = new MutableLiveData<>();
        }
        return classroom;
    }

    public void setClassName(String name) {
        classroom.getValue().setName(name);
    }
}
