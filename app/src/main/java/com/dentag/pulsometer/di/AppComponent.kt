package com.dentag.pulsometer.di

import android.app.Application
import com.dentag.pulsometer.ui.MainFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [Binds::class, AppModule::class]
)
interface AppComponent {

    fun inject(fragment: MainFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}