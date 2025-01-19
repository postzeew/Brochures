package com.bonial.brochures.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class AppViewModelFactory @Inject constructor(
    private val viewModelsProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider = viewModelsProviders[modelClass]
            ?: throw IllegalArgumentException("ViewModelProvider for $modelClass wasn't found.")
        @Suppress("UNCHECKED_CAST")
        return viewModelProvider.get() as T
    }
}