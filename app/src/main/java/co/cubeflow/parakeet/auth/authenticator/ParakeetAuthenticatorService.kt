package co.cubeflow.parakeet.auth.authenticator

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.annotation.Nullable

/**
 * @version 1.0.0
 * @since v1.0.0 (11/24/2017)
 */
class ParakeetAuthenticatorService : Service() {

    private var mAuthenticator: ParakeetAuthenticator? = null

    override fun onCreate() {
        super.onCreate()
        mAuthenticator = ParakeetAuthenticator(this)
    }

    @Nullable
    override fun onBind(intent: Intent): IBinder {
        return mAuthenticator!!.iBinder
    }
}
