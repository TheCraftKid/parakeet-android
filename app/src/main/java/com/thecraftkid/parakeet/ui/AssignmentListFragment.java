package com.thecraftkid.parakeet.ui;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @version 1.0.0
 * @since v1.0.0 (11/19/2017)
 */

public class AssignmentListFragment extends Fragment {

    private RecyclerView mList;

    /**
     * Required public empty constructor
     */
    public AssignmentListFragment() {

    }

    public static AssignmentListFragment newInstance() {
        Bundle args = new Bundle();
        AssignmentListFragment fragment = new AssignmentListFragment();
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
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static class ListViewModel extends ViewModel {

    }
}
