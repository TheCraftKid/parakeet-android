package com.thecraftkid.parakeet.ui;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.thecraftkid.parakeet.R;
import com.thecraftkid.parakeet.ui.viewmodel.AssignmentCreationViewModel;

import java.util.Calendar;

/**
 * A {@link Fragment} that provides options for creating a
 * {@link com.thecraftkid.parakeet.model.Assignment}.
 *
 * @version 1.0.0
 * @since v1.0.0 (11/22/17)
 */
public class AssignmentCreationFragment extends Fragment implements DatePickerDialog.OnDateSetListener {


    private TextView mDueDateView;

    private TextView mGroupMembersView;

    private TextInputLayout mNotesView;

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
        mNotesView = view.findViewById(R.id.assignment_notes_content);

        mDueDateView.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            //noinspection ConstantConditions Context won't be null.
            new DatePickerDialog(getContext(), this, year, month, day).show();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        AssignmentCreationViewModel viewModel = ViewModelProviders.of(this)
                .get(AssignmentCreationViewModel.class);
        viewModel.getDueDate().observe(this, dueDate -> {
        });

        viewModel.getNotes().observe(this, notes -> {
            getNotesEditText().setText(notes);
        });

        getNotesEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // no-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.getNotes().setValue(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // no-op
            }
        });
    }

    @NonNull
    private TextView getNotesEditText() {
        //noinspection ConstantConditions Since the layout includes an EditText
        return mNotesView.getEditText();
    }

    @Override
    public void onDateSet(DatePicker picker, int year, int month, int day) {

    }
}
