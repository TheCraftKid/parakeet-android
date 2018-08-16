package co.cubeflow.parakeet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import co.cubeflow.parakeet.R
import kotlinx.android.synthetic.main.fragment_assignment_list.*

/**
 * @version 1.0.0
 * @since v1.0.0 (11/19/2017)
 */
class AssignmentListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_assignment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initList()
    }

    private fun initList() {
        // TODO: 11/20/2017 Initialize list
        list_assignments.layoutManager = LinearLayoutManager(context)
    }

    class ListViewModel : ViewModel()

    companion object {

        fun newInstance(): AssignmentListFragment {
            val args = Bundle()
            val fragment = AssignmentListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
