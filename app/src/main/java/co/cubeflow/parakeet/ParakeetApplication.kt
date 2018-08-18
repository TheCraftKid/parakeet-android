package co.cubeflow.parakeet

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

/**
 * @version 1.0.0
 * @since 1.0.0
 */
class ParakeetApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}