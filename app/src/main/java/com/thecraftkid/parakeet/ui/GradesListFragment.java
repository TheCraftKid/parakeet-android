package com.thecraftkid.parakeet.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thecraftkid.parakeet.R;

import java.util.Objects;

import static com.thecraftkid.parakeet.util.IntentConstants.EXTRA_USER_ID;

/**
 * A {@link android.support.v4.app.Fragment} that displays a list of
 * {@link com.thecraftkid.parakeet.model.Assignment} grades.
 *
 * @version 1.0.0
 * @since v1.0.0 (11/19/2017)
 */
public class GradesListFragment extends Fragment {

    /**
     * Required public empty constructor
     */
    public GradesListFragment() {

    }

    public static GradesListFragment newInstance(String userId) {
        Bundle args = new Bundle();
        args.putString(EXTRA_USER_ID, userId);
        GradesListFragment fragment = new GradesListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getArguments());
        String userId = Objects.requireNonNull(getArguments().getString(EXTRA_USER_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_class_grades, container, false);
    }
}
