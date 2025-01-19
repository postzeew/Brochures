package com.bonial.brochures.presentation.common

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import com.bonial.brochures.App

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.viewModels(): Lazy<VM> {
    return viewModels {
        App.appComponent.getAppViewModelFactory()
    }
}