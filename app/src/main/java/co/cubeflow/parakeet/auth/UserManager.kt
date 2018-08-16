package co.cubeflow.parakeet.auth

import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.FragmentActivity
import co.cubeflow.parakeet.BuildConfig
import co.cubeflow.parakeet.R
import co.cubeflow.parakeet.util.IntentConstants
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import java.util.*

/**
 * @version 1.0.0
 * @since v1.0.0 (11/23/2017)
 */
class UserManager private constructor() {

    private val mAuth = FirebaseAuth.getInstance()

    /**
     * Returns if a user is signed in to the app.
     */
    val isSignedIn: Boolean
        get() = mAuth.currentUser != null

    /**
     * Returns the currently signed in user's unique identifier if one exists; null otherwise.
     */
    val userId: String?
        @Nullable
        get() = if (mAuth.currentUser != null) mAuth.currentUser!!.uid else null

    /**
     * Launches a sign-in flow that gives the user the choice how to sign in.
     *
     *
     * The available identity providers to authenticate are email or Google.
     *
     *
     * @param activity The activity to return a result once the flow is completed
     */
    fun signIn(activity: FragmentActivity) {
        val providers = Arrays.asList(
                AuthUI.IdpConfig.GoogleBuilder().build(),
                AuthUI.IdpConfig.EmailBuilder().setRequireName(true)
                        .build())
        val intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.mipmap.ic_launcher)
                .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                // TODO: 11/23/2017 Set privacy policy URL
                .build()
        activity.startActivityForResult(intent, IntentConstants.RC_SIGN_IN)
    }

    /**
     * Signs out the currently signed in user if one exists.
     *
     * @return A [Task] representing the sign out flow
     */
    @NonNull
    fun signOut(activity: FragmentActivity): Task<Void> {
        return AuthUI.getInstance().signOut(activity)
                .continueWithTask { task ->
                    Log.w(TAG, "Couldn't sign out", task.exception)
                    task
                }
    }

    companion object {

        private val TAG = UserManager::class.java.simpleName

        private var sInstance: UserManager? = null

        val instance: UserManager
            get() {
                if (sInstance == null) {
                    sInstance = UserManager()
                }
                return sInstance as UserManager
            }
    }
}
