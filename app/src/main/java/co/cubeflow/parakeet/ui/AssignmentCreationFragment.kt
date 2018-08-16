package co.cubeflow.parakeet.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.cubeflow.parakeet.R
import co.cubeflow.parakeet.model.Assignment
import co.cubeflow.parakeet.ui.viewmodel.AssignmentCreationViewModel
import kotlinx.android.synthetic.main.fragment_assignment_creation.*
import org.joda.time.DateTime
import java.util.*

/**
 * A [Fragment] that provides options for creating a
 * [Assignment].
 *
 * @version 1.0.0
 * @since 1.0.0
 */
class AssignmentCreationFragment : Fragment() {

    private lateinit var viewModel: AssignmentCreationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_assignment_creation, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AssignmentCreationViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        assignment_due_date_label.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(context, { _, y, m, d ->
                val dueDate = DateTime(y, m + 1, d, 0, 0).millis
                viewModel.dueDate.setValue(dueDate)
            }, year, month, day).show()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.dueDate.observe(this, Observer{ dueDate ->
            val dateTime = DateTime(dueDate)
            assignment_due_date_label!!.text = dateTime.toString() // TODO: 11/23/2017 Localize date string
        })

        viewModel.notes.observe(this, Observer {
            notes -> assignment_notes_content.setText(notes)
        })

        assignment_notes_content.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // no-op
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //                getViewModel().getNotes().setValue(String.valueOf(charSequence));
            }

            override fun afterTextChanged(editable: Editable) {
                // no-op
            }
        })

        assignment_points_content.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // no-op
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                viewModel.totalPoints.setValue(Integer.valueOf(charSequence.toString()))
            }

            override fun afterTextChanged(editable: Editable) {
                // no-op
            }
        })
    }
}
