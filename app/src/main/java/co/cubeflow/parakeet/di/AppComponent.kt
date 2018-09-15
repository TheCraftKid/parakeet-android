package co.cubeflow.parakeet.di

import co.cubeflow.parakeet.ParakeetApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * The main component of the app stored in the [ParakeetApplication].
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    FirestoreModule::class,
    RepositoryModule::class
])
interface AppComponent : AndroidInjector<ParakeetApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<ParakeetApplication>()
}