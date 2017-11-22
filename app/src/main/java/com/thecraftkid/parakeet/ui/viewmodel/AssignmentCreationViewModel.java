package com.thecraftkid.parakeet.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * @version 1.0.0
 * @since v1.0.0 (11/21/2017)
 */
public class AssignmentCreationViewModel extends ViewModel {

    private LiveData<String> name;

    private LiveData<String> notes;

    private LiveData<Long> dueDate;

    private LiveData<Integer> totalPoints;

    public LiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
        }
        return name;
    }

    public LiveData<String> getNotes() {
        if (notes == null) {
            notes = new MutableLiveData<>();
        }
        return notes;
    }

    public LiveData<Long> getDueDate() {
        if (dueDate == null) {
            dueDate = new MutableLiveData<>();
        }
        return dueDate;
    }

    public LiveData<Integer> getTotalPoints() {
        if (totalPoints == null) {
            totalPoints = new MutableLiveData<>();
        }
        return totalPoints;
    }
}
