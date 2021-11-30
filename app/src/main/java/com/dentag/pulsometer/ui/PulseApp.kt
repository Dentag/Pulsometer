package com.dentag.pulsometer.ui

import android.app.Application
import android.content.Context
import com.dentag.pulsometer.di.AppComponent
import com.dentag.pulsometer.di.DaggerAppComponent

class PulseApp : Application() {
    private var _appComponent: AppComponent? = null

    val appComponent: AppComponent
        get() = checkNotNull(_appComponent)

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.factory()
            .create(this)
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is PulseApp -> appComponent
        else -> (applicationContext as PulseApp).appComponent
    }