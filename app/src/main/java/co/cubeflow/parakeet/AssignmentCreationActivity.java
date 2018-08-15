package co.cubeflow.parakeet;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.thecraftkid.parakeet.R;

import co.cubeflow.parakeet.database.Database;
import co.cubeflow.parakeet.model.Assignment;
import co.cubeflow.parakeet.ui.viewmodel.AssignmentCreationViewModel;

import java.util.Objects;

/**
 * An {@link android.app.Activity} that allows the user to create a new {@link
 * Assignment}
 *
 * @version 1.0.0
 * @since v1.0.0 (11/22/17)
 */
public class AssignmentCreationActivity extends AppCompatActivity {

    private static final String TAG = AssignmentCreationActivity.class.getSimpleName();

    public static void start(Context context) {
        Intent starter = new Intent(context, AssignmentCreationActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_creation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getViewModel().getDueDate().setValue(0L);

        EditText assignmentNameView = findViewById(R.id.input_assignment_name);
        assignmentNameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // no-op
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getViewModel().getName().setValue(String.valueOf(charSequence));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // no-op
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_assignment_creation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_finish:
                if (validateFields()) {
                    createAssignment();
                }
                return true;
        }
        return false;
    }

    @NonNull
    private AssignmentCreationViewModel getViewModel() {
        return ViewModelProviders.of(this).get(AssignmentCreationViewModel.class);
    }

    /**
     * Validates and informs the user of input errors.
     *
     * @return False if one or more fields need to be corrected.
     */
    private boolean validateFields() {
        if (TextUtils.isEmpty(getViewModel().getName().getValue())) {
            Toast.makeText(this, "Please provide a title.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (getViewModel().getDueDate().getValue() == 0L) {
            Toast.makeText(this, "Please choose a due date.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void createAssignment() {
        Assignment newAssignment = generateAssignment();
//        String classId = getViewModel().getClassId().getValue();
        String classId = "AAGM7Iu84mbsCxLmdFZE"; // TODO: Remove this once auth is set up
        upload(classId, newAssignment).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "createAssignment: Assignment uploaded successfully");
            } else {
                Log.w(TAG, "createAssignment: Couldn't create assignment", task.getException());
                Toast.makeText(this, R.string.error_creating_assignment, Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }

    private Assignment generateAssignment() {
        String name = Objects.requireNonNull(getViewModel().getName().getValue());
        //noinspection ConstantConditions
        long dueDate = getViewModel().getDueDate().getValue();
        String notes = getViewModel().getNotes().getValue();
        Assignment assignment = new Assignment();
        assignment.setName(name);
        assignment.setDueDate(dueDate);
        assignment.setNotes(notes);
        // TODO: 11/22/2017 Handle group members
        return assignment;
    }

    private Task<Void> upload(String classId, Assignment assignment) {
        return Database.getInstance().upload(classId, assignment);
    }
}
