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
import co.cubeflow.parakeet.ui.viewmodel.GradeListViewModel
import co.cubeflow.parakeet.util.IntentConstants
import kotlinx.android.synthetic.main.fragment_class_grades.*

/**
 * A [Fragment] that displays a list of
 * [Assignment] grades.
 *
 * @version 1.0.0
 * @since v1.0.0 (11/19/17)
 */
class GradesListFragment : Fragment() {

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val userId = it.getString(IntentConstants.EXTRA_USER_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_class_grades, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val gradesList = list_grades
        val viewModel = ViewModelProviders.of(this).get(GradeListViewModel::class.java)
        viewModel.assignments.observe(this, Observer { assignments ->
            // TODO: 11/22/2017 Update displayed data
        })
    }

    companion object {

        fun newInstance(userId: String): GradesListFragment {
            val args = Bundle()
            args.putString(IntentConstants.EXTRA_USER_ID, userId)
            val fragment = GradesListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
