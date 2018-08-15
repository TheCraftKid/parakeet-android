package co.cubeflow.parakeet.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.cubeflow.parakeet.R


/**
 * A [Fragment] that displays a mix of upcoming [Assignment]s, school events, and small
 * grade updates.
 *
 * @version 1.0.0
 * @since v1.0.0 (11/19/2017)
 */
class DashboardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_dashboard, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadDashboard()
    }

    private fun loadDashboard() {

    }

    companion object {
        @JvmStatic
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }
}
