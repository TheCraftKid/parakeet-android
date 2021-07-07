package co.cubeflow.parakeet

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import co.cubeflow.parakeet.model.Assignment
import co.cubeflow.parakeet.ui.AssignmentDetailFragment
import co.cubeflow.parakeet.util.IntentConstants
import kotlinx.android.synthetic.main.activity_assignment_creation.*
import java.util.Objects.requireNonNull

/**
 * An [android.app.Activity] that displays details for a singular
 * [Assignment].
 *
 * @version 1.0.0
 * @since v0.1.0 (11/19/2017)
 */
class AssignmentDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        val classId = requireNonNull(intent.getStringExtra(IntentConstants.EXTRA_CLASSROOM_ID))
        val assignmentId = requireNonNull(intent.getStringExtra(IntentConstants.EXTRA_ASSIGNMENT_ID))

        if (assignmentId != null) {
            loadAssignment(classId, assignmentId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_assignment_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit -> {
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadAssignment(classId: String, assignmentId: String) {
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container,
                        AssignmentDetailFragment.newInstance(classId, assignmentId))
                .commit()
    }

    private fun editAssignment() {
        // TODO: 11/19/2017 Allow editing of assignments
    }
}
