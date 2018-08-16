package co.cubeflow.parakeet.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import co.cubeflow.parakeet.R;
import co.cubeflow.parakeet.model.Assignment;
import co.cubeflow.parakeet.util.IntentConstants;
import co.cubeflow.parakeet.viewmodel.AssignmentViewModel;

/**
 * A {@link Fragment} that displays {@link Assignment} data to the
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
        args.putString(IntentConstants.EXTRA_CLASSROOM_ID, classId);
        args.putString(IntentConstants.EXTRA_ASSIGNMENT_ID, assignmentId);
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
        String classId = Objects.requireNonNull(getArguments().getString(IntentConstants.EXTRA_CLASSROOM_ID));
        String assignmentId = Objects.requireNonNull(getArguments().getString(IntentConstants.EXTRA_ASSIGNMENT_ID));
        AssignmentViewModel viewModel = ViewModelProviders.of(this)
                .get(AssignmentViewModel.class);
        viewModel.getAssignment(classId, assignmentId).observe(this, assignment -> {
            // TODO: 11/19/2017 Update UI
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_assignment_detail, container, false);
    }
}
