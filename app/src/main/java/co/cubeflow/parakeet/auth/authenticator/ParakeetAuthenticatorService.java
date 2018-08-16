package co.cubeflow.parakeet.auth.authenticator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

/**
 * @version 1.0.0
 * @since v1.0.0 (11/24/2017)
 */
public class ParakeetAuthenticatorService extends Service {

    private ParakeetAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
        mAuthenticator = new ParakeetAuthenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
