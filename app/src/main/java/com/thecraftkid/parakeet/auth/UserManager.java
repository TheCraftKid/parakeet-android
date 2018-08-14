package com.thecraftkid.parakeet.auth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.thecraftkid.parakeet.BuildConfig;
import com.thecraftkid.parakeet.R;
import com.thecraftkid.parakeet.util.IntentConstants;

import java.util.Arrays;
import java.util.List;

/**
 * @version 1.0.0
 * @since v1.0.0 (11/23/2017)
 */
public final class UserManager {

    private static final String TAG = UserManager.class.getSimpleName();

    private static UserManager sInstance;

    private FirebaseAuth mAuth;

    public static UserManager getInstance() {
        if (sInstance == null) {
            sInstance = new UserManager();
        }
        return sInstance;
    }

    private UserManager() {
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Launches a sign-in flow that gives the user the choice how to sign in.
     * <p>
     * The available identity providers to authenticate are email or Google.
     * </p>
     *
     * @param activity The activity to return a result once the flow is completed
     */
    public void signIn(FragmentActivity activity) {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().setRequireName(true)
                        .build());
        Intent intent = AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.mipmap.ic_launcher)
                .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                // TODO: 11/23/2017 Set privacy policy URL
                .build();
        activity.startActivityForResult(intent, IntentConstants.RC_SIGN_IN);
    }

    /**
     * Returns if a user is signed in to the app.
     */
    public boolean isSignedIn() {
        return mAuth.getCurrentUser() != null;
    }

    /**
     * Signs out the currently signed in user if one exists.
     *
     * @return A {@link Task} representing the sign out flow
     */
    @NonNull
    public Task<Void> signOut(FragmentActivity activity) {
        return AuthUI.getInstance().signOut(activity)
                .continueWithTask(task -> {
                    Log.w(TAG, "Couldn't sign out", task.getException());
                    return task;
                });
    }

    /**
     * Returns the currently signed in user's unique identifier if one exists; null otherwise.
     */
    @Nullable
    public String getUserId() {
        return mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : null;
    }
}
