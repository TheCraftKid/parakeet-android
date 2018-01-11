package com.thecraftkid.parakeet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.thecraftkid.parakeet.util.IntentConstants

/**
 * @since 1.0.0
 * @version 1.0.0
 */
class ClassCreationActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context, userId: String?) {
            val intent = Intent(context, ClassCreationActivity::class.java)
            intent.putExtra(IntentConstants.EXTRA_USER_ID, userId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ClassCreationFragment.newInstance()
                .show(supportFragmentManager, ClassCreationFragment.TAG)
    }

}
