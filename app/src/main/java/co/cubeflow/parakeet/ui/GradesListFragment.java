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
import androidx.recyclerview.widget.RecyclerView;
import co.cubeflow.parakeet.R;
import co.cubeflow.parakeet.model.Assignment;
import co.cubeflow.parakeet.ui.viewmodel.GradeListViewModel;
import co.cubeflow.parakeet.util.IntentConstants;

/**
 * A {@link Fragment} that displays a list of
 * {@link Assignment} grades.
 *
 * @version 1.0.0
 * @since v1.0.0 (11/19/17)
 */
public class GradesListFragment extends Fragment {

    /**
     * Required public empty constructor
     */
    public GradesListFragment() {

    }

    public static GradesListFragment newInstance(String userId) {
        Bundle args = new Bundle();
        args.putString(IntentConstants.EXTRA_USER_ID, userId);
        GradesListFragment fragment = new GradesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getArguments());
        String userId = Objects.requireNonNull(getArguments().getString(IntentConstants.EXTRA_USER_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_class_grades, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView gradesList = view.findViewById(R.id.list_grades);
        GradeListViewModel viewModel = ViewModelProviders.of(this).get(GradeListViewModel.class);
        viewModel.getAssignments().observe(this, assignments -> {
            // TODO: 11/22/2017 Update displayed data
        });
    }
}
