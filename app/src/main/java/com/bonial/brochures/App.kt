package com.bonial.brochures

import android.app.Application
import com.bonial.brochures.di.component.AppComponent
import com.bonial.brochures.di.component.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent
            .factory()
            .create(this)
    }
}