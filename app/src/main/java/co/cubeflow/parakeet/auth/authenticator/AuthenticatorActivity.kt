package co.cubeflow.parakeet.auth.authenticator

import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import co.cubeflow.parakeet.R
import co.cubeflow.parakeet.auth.UserManager
import co.cubeflow.parakeet.util.IntentConstants
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.snackbar.Snackbar

/**
 * @version 1.0.0
 * @since v1.0.0 (11/24/17)
 */
class AuthenticatorActivity : AppCompatActivity() {

    private var mAccountAuthenticatorResponse: AccountAuthenticatorResponse? = null
    private val mResultBundle: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAccountAuthenticatorResponse = intent.getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE)
        if (mAccountAuthenticatorResponse != null) {
            mAccountAuthenticatorResponse!!.onRequestContinued()
        }

        val manager = UserManager.instance
        if (manager.isSignedIn) {
            Toast.makeText(this, R.string.error_only_one_account, Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        manager.signIn(this)
    }

    protected override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IntentConstants.RC_SIGN_IN -> {
                val response = IdpResponse.fromResultIntent(data)
                if (resultCode == Activity.RESULT_OK) {
                    //                    setResult(RESULT_OK, );
                    if (mAccountAuthenticatorResponse != null) {
                        // send the result bundle back if set, otherwise send an error.
                        if (mResultBundle != null) {
                            mAccountAuthenticatorResponse!!.onResult(mResultBundle)
                        } else {
                            mAccountAuthenticatorResponse!!.onError(AccountManager.ERROR_CODE_CANCELED,
                                    "canceled")
                        }
                        mAccountAuthenticatorResponse = null
                    }
                } else if (response != null && response.error != null) {
                    if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                        showSnackbar(R.string.error_no_network)
                    }
                    if (response.error!!.errorCode == ErrorCodes.UNKNOWN_ERROR) {
                        showSnackbar(R.string.error_unknown)
                    }
                    finish()
                }
            }
        }
    }

    private fun showSnackbar(@StringRes text: Int) {
        Snackbar.make(findViewById<View>(android.R.id.content), text, Snackbar.LENGTH_LONG)
    }

    companion object {

        const val KEY_AUTH_TOKEN_TYPE = "fullAccess"

        const val ARG_IS_ADDING_NEW_ACCOUNT = "addingNewAccount"
    }
}
