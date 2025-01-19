package com.bonial.brochures.di.module

import com.bonial.brochures.presentation.common.Dispatchers
import com.bonial.brochures.presentation.common.DispatchersImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppModule {

    @Binds
    @Singleton
    fun bindDispatchers(impl: DispatchersImpl): Dispatchers
}