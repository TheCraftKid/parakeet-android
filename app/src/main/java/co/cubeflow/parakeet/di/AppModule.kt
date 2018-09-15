package co.cubeflow.parakeet.di

import co.cubeflow.parakeet.ParakeetApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: ParakeetApplication) = application.applicationContext
}