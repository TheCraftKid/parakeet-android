package com.thecraftkid.parakeet

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thecraftkid.parakeet.model.Classroom

/**
 * A [DialogFragment] used to create a new [Classroom].
 *
 * @since 1.0.0
 * @version 1.0.0
 */
class ClassCreationFragment : DialogFragment() {

    private lateinit var callback: ClassCreationCallback

    companion object {
        @JvmStatic
        val TAG = ClassCreationFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = run {
            DialogFragment()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is ClassCreationCallback) {
            this.callback = context
        } else {
            throw RuntimeException(context.toString() + " must implement ClassCreationCallback")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(this.activity!!)
                .setTitle(R.string.title_add_classroom)
                // TODO: Initialize views
                .create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_create_class, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    interface ClassCreationCallback {

        /**
         * Called when a [ClassCreationFragment] creates a new [Classroom].
         *
         * @param classroom A new non-null classroom
         */
        fun onCreation(classroom: Classroom)
    }
}
