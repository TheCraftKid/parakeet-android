package com.thecraftkid.parakeet.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thecraftkid.parakeet.R;

/**
 * A {@link Fragment} that provides options for creating a
 * {@link com.thecraftkid.parakeet.model.Assignment}.
 *
 * @version 1.0.0
 * @since v1.0.0 (11/22/17)
 */
public class AssignmentCreationFragment extends Fragment {


    /**
     * Required public empty constructor
     */
    public AssignmentCreationFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_assignment_creation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AssignmentCreationViewModel viewModel = ViewModelProviders.of(this)
                .get(AssignmentCreationViewModel.class);
    }

    private static class AssignmentCreationViewModel extends ViewModel {

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
}
