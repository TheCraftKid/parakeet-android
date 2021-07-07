package co.cubeflow.parakeet.util.logging

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

/**
 * A [Timber.Tree] for logging events to Firebase Crashlytics.
 */
class CrashlyticsTree : Timber.Tree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority >= Log.WARN
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        Crashlytics.log(priority, tag, message)
        t?.let {
            Crashlytics.logException(it)
        }
    }
}