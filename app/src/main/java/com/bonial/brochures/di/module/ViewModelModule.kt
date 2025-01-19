package com.bonial.brochures.di.module

import androidx.lifecycle.ViewModel
import com.bonial.brochures.di.annotation.ViewModelKey
import com.bonial.brochures.presentation.home.HomeViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModelImpl::class)
    fun bindHomeViewModel(impl: HomeViewModelImpl): ViewModel
}