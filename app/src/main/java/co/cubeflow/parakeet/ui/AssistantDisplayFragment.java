package co.cubeflow.parakeet.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thecraftkid.parakeet.R;

import co.cubeflow.parakeet.model.Assignment;
import co.cubeflow.parakeet.util.IntentConstants;

/**
 * A {@link Fragment} that helps the user make decisions on what
 * {@link Assignment}s to complete and how to study in a more
 * useful manner.
 *
 * @version 1.0.0
 * @since 1.0.0
 */
public class AssistantDisplayFragment extends Fragment {

    /**
     * Required public empty constructor
     */
    public AssistantDisplayFragment() {

    }

    public static AssistantDisplayFragment newInstance(@Nullable String userId) {
        Bundle args = new Bundle();
        args.putString(IntentConstants.EXTRA_USER_ID, userId);
        AssistantDisplayFragment fragment = new AssistantDisplayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_assistant_display, container, false);
    }
}
