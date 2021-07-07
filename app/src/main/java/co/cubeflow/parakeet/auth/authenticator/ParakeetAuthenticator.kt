package co.cubeflow.parakeet.auth.authenticator

import android.accounts.*
import android.accounts.AccountManager.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils

/**
 * @version 1.0.0
 * @since v1.0.0 (11/24/2017)
 */
class ParakeetAuthenticator(private val mContext: Context) : AbstractAccountAuthenticator(mContext) {

    @Throws(NetworkErrorException::class)
    override fun addAccount(response: AccountAuthenticatorResponse,
                            accountType: String,
                            authTokenType: String,
                            requiredFeatures: Array<String>,
                            options: Bundle): Bundle {
        val intent = Intent(mContext, AuthenticatorActivity::class.java)
        intent.putExtra(KEY_ACCOUNT_TYPE, accountType)
        intent.putExtra(KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        intent.putExtra(AuthenticatorActivity.KEY_AUTH_TOKEN_TYPE, authTokenType)
        intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true)

        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
        return bundle
    }

    @Throws(NetworkErrorException::class)
    override fun getAuthToken(response: AccountAuthenticatorResponse, account: Account,
                              authTokenType: String, options: Bundle): Bundle {

        // Extract the username and password from the Account Manager, and ask
        // the server for an appropriate AuthToken.
        val am = AccountManager.get(mContext)
        val authToken = am.peekAuthToken(account, authTokenType)
        val authData = Bundle()

        // Ensure authentication token has not expired
        if (!TextUtils.isEmpty(authToken)) {
            authData.putString(KEY_ACCOUNT_NAME, account.name)
            authData.putString(KEY_ACCOUNT_TYPE, account.type)
            authData.putString(KEY_AUTHTOKEN, authToken)

            //            try {
            //                authData = Tasks.await(Tasks.forResult(authData).continueWithTask(new SignInTask(true)));
            //            } catch (Exception e) {
            //                e.printStackTrace();
            //                authData.clear();
            //            }
        }

        // If we get an authToken - we return it
        if (authData.containsKey(KEY_AUTHTOKEN)) {
            return authData
        }

        // If we get here, then we couldn't access the user's password - so we
        // need to re-prompt them for their credentials. We do that by creating
        // an intent to display our AuthenticatorActivity.
        val intent = Intent(mContext, AuthenticatorActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        intent.putExtra(KEY_ACCOUNT_NAME, account.name)
        intent.putExtra(AuthenticatorActivity.KEY_AUTH_TOKEN_TYPE, authTokenType)
        intent.putExtra(KEY_ACCOUNT_TYPE, account.type)
        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
        return bundle
    }

    override fun getAuthTokenLabel(authTokenType: String): String {
        return authTokenType
    }

    override fun editProperties(response: AccountAuthenticatorResponse, accountType: String): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun confirmCredentials(response: AccountAuthenticatorResponse, account: Account,
                                    options: Bundle): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun updateCredentials(response: AccountAuthenticatorResponse, account: Account,
                                   authTokenType: String, options: Bundle): Bundle? {
        return null
    }

    @Throws(NetworkErrorException::class)
    override fun hasFeatures(response: AccountAuthenticatorResponse, account: Account,
                             features: Array<String>): Bundle {
        val result = Bundle()
        result.putBoolean(KEY_BOOLEAN_RESULT, false)
        return result
    }
}
