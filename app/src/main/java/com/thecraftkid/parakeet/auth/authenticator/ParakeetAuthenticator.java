package com.thecraftkid.parakeet.auth.authenticator;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import static android.accounts.AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE;
import static android.accounts.AccountManager.KEY_ACCOUNT_NAME;
import static android.accounts.AccountManager.KEY_ACCOUNT_TYPE;
import static android.accounts.AccountManager.KEY_AUTHTOKEN;
import static android.accounts.AccountManager.KEY_BOOLEAN_RESULT;

/**
 * @version 1.0.0
 * @since v1.0.0 (11/24/2017)
 */
public class ParakeetAuthenticator extends AbstractAccountAuthenticator {

    private Context mContext;

    public ParakeetAuthenticator(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response,
                             String accountType,
                             String authTokenType,
                             String[] requiredFeatures,
                             Bundle options) throws NetworkErrorException {
        Intent intent = new Intent(mContext, AuthenticatorActivity.class);
        intent.putExtra(KEY_ACCOUNT_TYPE, accountType);
        intent.putExtra(KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        intent.putExtra(AuthenticatorActivity.KEY_AUTH_TOKEN_TYPE, authTokenType);
        intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);

        Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account,
                               String authTokenType, Bundle options) throws NetworkErrorException {

        // Extract the username and password from the Account Manager, and ask
        // the server for an appropriate AuthToken.
        final AccountManager am = AccountManager.get(mContext);
        final String authToken = am.peekAuthToken(account, authTokenType);
        Bundle authData = new Bundle();

        // Ensure authentication token has not expired
        if (!TextUtils.isEmpty(authToken)) {
            authData.putString(KEY_ACCOUNT_NAME, account.name);
            authData.putString(KEY_ACCOUNT_TYPE, account.type);
            authData.putString(KEY_AUTHTOKEN, authToken);

//            try {
//                authData = Tasks.await(Tasks.forResult(authData).continueWithTask(new SignInTask(true)));
//            } catch (Exception e) {
//                e.printStackTrace();
//                authData.clear();
//            }
        }

        // If we get an authToken - we return it
        if (authData.containsKey(KEY_AUTHTOKEN)) {
            return authData;
        }

        // If we get here, then we couldn't access the user's password - so we
        // need to re-prompt them for their credentials. We do that by creating
        // an intent to display our AuthenticatorActivity.
        Intent intent = new Intent(mContext, AuthenticatorActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        intent.putExtra(KEY_ACCOUNT_NAME, account.name);
        intent.putExtra(AuthenticatorActivity.KEY_AUTH_TOKEN_TYPE, authTokenType);
        intent.putExtra(KEY_ACCOUNT_TYPE, account.type);
        Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return authTokenType;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account,
                                     Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account,
                                    String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account,
                              String[] features) throws NetworkErrorException {
        Bundle result = new Bundle();
        result.putBoolean(KEY_BOOLEAN_RESULT, false);
        return result;
    }
}
