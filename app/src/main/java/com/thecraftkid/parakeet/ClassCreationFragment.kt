package com.thecraftkid.parakeet

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A placeholder fragment containing a simple view.
 */
class ClassCreationFragment : DialogFragment() {

    companion object {
        @JvmStatic
        val TAG = ClassCreationFragment::class.java.simpleName

        @JvmStatic
        fun newInstance(context: Context) = run {
            DialogFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_create_class, container, false)
}
