package com.bonial.brochures.di.component

import android.content.Context
import com.bonial.brochures.di.module.ViewModelModule
import com.bonial.brochures.presentation.common.AppViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }

    fun getAppViewModelFactory(): AppViewModelFactory
}