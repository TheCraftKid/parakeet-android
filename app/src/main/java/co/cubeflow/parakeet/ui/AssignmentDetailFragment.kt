package co.cubeflow.parakeet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.cubeflow.parakeet.R
import co.cubeflow.parakeet.util.IntentConstants
import co.cubeflow.parakeet.viewmodel.AssignmentViewModel

/**
 * A [Fragment] that displays [Assignment] data to the
 * user.
 *
 * @version 1.0.0
 * @since v1.0.0 (11/19/2017)
 */
class AssignmentDetailFragment : Fragment() {

    companion object {

        fun newInstance(classId: String, assignmentId: String): AssignmentDetailFragment {
            val args = Bundle()
            args.putString(IntentConstants.EXTRA_CLASSROOM_ID, classId)
            args.putString(IntentConstants.EXTRA_ASSIGNMENT_ID, assignmentId)
            val fragment = AssignmentDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments == null) {
            throw RuntimeException("Assignment ID must be provided")
        }
        arguments?.let {
            val classId = it.getString(IntentConstants.EXTRA_CLASSROOM_ID)
            val assignmentId = it.getString(IntentConstants.EXTRA_ASSIGNMENT_ID)
            val viewModel = ViewModelProviders.of(this)
                    .get(AssignmentViewModel::class.java)
            viewModel.getAssignment(classId, assignmentId).observe(this, Observer { assignment ->
                // TODO: 11/19/2017 Update UI
            })
        }
    }

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_assignment_detail, container, false)
}
