package co.cubeflow.parakeet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.thecraftkid.parakeet.R;

import co.cubeflow.parakeet.ui.AssignmentDetailFragment;

import co.cubeflow.parakeet.model.Assignment;
import co.cubeflow.parakeet.util.IntentConstants;

import static java.util.Objects.requireNonNull;

/**
 * An {@link android.app.Activity} that displays details for a singular
 * {@link Assignment}.
 *
 * @version 1.0.0
 * @since v0.1.0 (11/19/2017)
 */
public class AssignmentDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String classId = requireNonNull(intent.getStringExtra(IntentConstants.EXTRA_CLASSROOM_ID));
        String assignmentId = requireNonNull(intent.getStringExtra(IntentConstants.EXTRA_ASSIGNMENT_ID));

        if (assignmentId != null) {
            loadAssignment(classId, assignmentId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_assignment_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadAssignment(String classId, String assignmentId) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container,
                        AssignmentDetailFragment.newInstance(classId, assignmentId))
                .commit();
    }

    private void editAssignment() {
        // TODO: 11/19/2017 Allow editing of assignments
    }
}
