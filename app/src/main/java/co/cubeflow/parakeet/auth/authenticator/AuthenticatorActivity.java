package co.cubeflow.parakeet.auth.authenticator;

import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import co.cubeflow.parakeet.R;
import co.cubeflow.parakeet.auth.UserManager;
import co.cubeflow.parakeet.util.IntentConstants;

/**
 * @version 1.0.0
 * @since v1.0.0 (11/24/17)
 */
public class AuthenticatorActivity extends AppCompatActivity {

    public static final String KEY_AUTH_TOKEN_TYPE = "fullAccess";

    public static final String ARG_IS_ADDING_NEW_ACCOUNT = "addingNewAccount";

    private AccountAuthenticatorResponse mAccountAuthenticatorResponse = null;
    private Bundle mResultBundle = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountAuthenticatorResponse =
                getIntent().getParcelableExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE);
        if (mAccountAuthenticatorResponse != null) {
            mAccountAuthenticatorResponse.onRequestContinued();
        }

        UserManager manager = UserManager.getInstance();
        if (manager.isSignedIn()) {
            Toast.makeText(this, R.string.error_only_one_account, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        manager.signIn(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IntentConstants.RC_SIGN_IN:
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if (resultCode == RESULT_OK) {
//                    setResult(RESULT_OK, );
                    if (mAccountAuthenticatorResponse != null) {
                        // send the result bundle back if set, otherwise send an error.
                        if (mResultBundle != null) {
                            mAccountAuthenticatorResponse.onResult(mResultBundle);
                        } else {
                            mAccountAuthenticatorResponse.onError(AccountManager.ERROR_CODE_CANCELED,
                                    "canceled");
                        }
                        mAccountAuthenticatorResponse = null;
                    }
                } else if (response != null && response.getError() != null) {
                    if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                        showSnackbar(R.string.error_no_network);
                    }
                    if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                        showSnackbar(R.string.error_unknown);
                    }
                    finish();
                }
        }
    }

    private void showSnackbar(@StringRes int text) {
        Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG);
    }
}
