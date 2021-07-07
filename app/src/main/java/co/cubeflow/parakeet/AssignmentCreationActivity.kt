package co.cubeflow.parakeet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import co.cubeflow.parakeet.database.Database
import co.cubeflow.parakeet.model.Assignment
import co.cubeflow.parakeet.ui.viewmodel.AssignmentCreationViewModel
import com.google.android.gms.tasks.Task
import java.util.*

/**
 * An [android.app.Activity] that allows the user to create a new [ ]
 *
 * @version 1.0.0
 * @since v1.0.0 (11/22/17)
 */
class AssignmentCreationActivity : AppCompatActivity() {

    private val viewModel: AssignmentCreationViewModel
        @NonNull
        get() = ViewModelProviders.of(this).get(AssignmentCreationViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment_creation)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.dueDate.setValue(0L)

        val assignmentNameView = findViewById<EditText>(R.id.input_assignment_name)
        assignmentNameView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // no-op
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                viewModel.name.setValue(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable) {
                // no-op
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_assignment_creation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_finish -> {
                if (validateFields()) {
                    createAssignment()
                }
                return true
            }
        }
        return false
    }

    /**
     * Validates and informs the user of input errors.
     *
     * @return False if one or more fields need to be corrected.
     */
    private fun validateFields(): Boolean {
        if (TextUtils.isEmpty(viewModel.name.value)) {
            Toast.makeText(this, "Please provide a title.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (viewModel.dueDate.value == 0L) {
            Toast.makeText(this, "Please choose a due date.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun createAssignment() {
        val newAssignment = generateAssignment()
        //        String classId = getViewModel().getClassId().getValue();
        val classId = "AAGM7Iu84mbsCxLmdFZE" // TODO: Remove this once auth is set up
        upload(classId, newAssignment).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "createAssignment: Assignment uploaded successfully")
            } else {
                Log.w(TAG, "createAssignment: Couldn't create assignment", task.exception)
                Toast.makeText(this, R.string.error_creating_assignment, Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    private fun generateAssignment(): Assignment {
        val name = Objects.requireNonNull<String>(viewModel.name.value)

        val dueDate = viewModel.dueDate.value
        val notes = viewModel.notes.value
        val assignment = Assignment()
        assignment.name = name
        assignment.dueDate = dueDate!!
        assignment.notes = notes
        // TODO: 11/22/2017 Handle group members
        return assignment
    }

    private fun upload(classId: String, assignment: Assignment): Task<Void> {
        return Database.instance.upload(classId, assignment)
    }

    companion object {

        private val TAG = AssignmentCreationActivity::class.java.simpleName

        fun start(context: Context) {
            val starter = Intent(context, AssignmentCreationActivity::class.java)
            context.startActivity(starter)
        }
    }
}
