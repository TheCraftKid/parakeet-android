package com.thecraftkid.parakeet.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.thecraftkid.parakeet.viewmodel.AssignmentViewModel;

import java.util.Objects;

import static com.thecraftkid.parakeet.util.IntentConstants.EXTRA_ASSIGNMENT_ID;
import static com.thecraftkid.parakeet.util.IntentConstants.EXTRA_CLASSROOM_ID;

/**
 * A {@link Fragment} that displays {@link com.thecraftkid.parakeet.model.Assignment} data to the
 * user.
 *
 * @version 1.0.0
 * @since v1.0.0 (11/19/2017)
 */
public class AssignmentDetailFragment extends Fragment {

    /**
     * Required public empty constructor
     */
    public AssignmentDetailFragment() {

    }

    public static AssignmentDetailFragment newInstance(String classId, String assignmentId) {
        Bundle args = new Bundle();
        args.putString(EXTRA_CLASSROOM_ID, classId);
        args.putString(EXTRA_ASSIGNMENT_ID, assignmentId);
        AssignmentDetailFragment fragment = new AssignmentDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() == null) {
            throw new RuntimeException("Assignment ID must be provided");
        }
        String classId = Objects.requireNonNull(getArguments().getString(EXTRA_CLASSROOM_ID));
        String assignmentId = Objects.requireNonNull(getArguments().getString(EXTRA_ASSIGNMENT_ID));
        AssignmentViewModel viewModel = ViewModelProviders.of(this)
                .get(AssignmentViewModel.class);
        viewModel.getAssignment(classId, assignmentId).observe(this, assignment -> {
            // TODO: 11/19/2017 Update UI
        });
    }
}
