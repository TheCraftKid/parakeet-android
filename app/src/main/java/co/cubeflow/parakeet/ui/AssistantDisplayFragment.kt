package co.cubeflow.parakeet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import co.cubeflow.parakeet.R
import co.cubeflow.parakeet.util.IntentConstants

/**
 * A [Fragment] that helps the user make decisions on what
 * [Assignment]s to complete and how to study in a more
 * useful manner.
 *
 * @version 1.0.0
 * @since 1.0.0
 */
class AssistantDisplayFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_assistant_display, container, false)

    companion object {

        fun newInstance(@Nullable userId: String): AssistantDisplayFragment {
            val args = Bundle()
            args.putString(IntentConstants.EXTRA_USER_ID, userId)
            val fragment = AssistantDisplayFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
