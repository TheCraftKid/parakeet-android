package com.thecraftkid.parakeet.ui;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.thecraftkid.parakeet.R;
import com.thecraftkid.parakeet.ui.viewmodel.AssignmentCreationViewModel;

import org.joda.time.DateTime;

import java.util.Calendar;

/**
 * A {@link Fragment} that provides options for creating a
 * {@link com.thecraftkid.parakeet.model.Assignment}.
 *
 * @version 1.0.0
 * @since 1.0.0
 */
public class AssignmentCreationFragment extends Fragment {

    private TextView mDueDateView;

    private TextView mGroupMembersView;

    private EditText mPointsView;

    private EditText mNotesView;

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
        mDueDateView = view.findViewById(R.id.assignment_due_date_label);
        mGroupMembersView = view.findViewById(R.id.group_members_content);
        mPointsView = view.findViewById(R.id.assignment_points_content);
        mNotesView = view.findViewById(R.id.assignment_notes_content);

        mDueDateView.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            //noinspection ConstantConditions Context won't be null.
            new DatePickerDialog(getContext(), (DatePicker picker, int y, int m, int d) -> {
                long dueDate = new DateTime(y, m + 1, d, 0, 0).getMillis();
                getViewModel().getDueDate().setValue(dueDate);
            }, year, month, day).show();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getViewModel().getDueDate().observe(this, dueDate -> {
            DateTime dateTime = new DateTime(dueDate);
            mDueDateView.setText(dateTime.toString()); // TODO: 11/23/2017 Localize date string
        });

        getViewModel().getNotes().observe(this, notes -> {
            mNotesView.setText(notes);
        });

        mNotesView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // no-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                getViewModel().getNotes().setValue(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // no-op
            }
        });

        mPointsView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // no-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getViewModel().getTotalPoints().setValue(Integer.valueOf(String.valueOf(charSequence)));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // no-op
            }
        });
    }

    @NonNull
    private AssignmentCreationViewModel getViewModel() {
        return ViewModelProviders.of(this).get(AssignmentCreationViewModel.class);
    }
}
