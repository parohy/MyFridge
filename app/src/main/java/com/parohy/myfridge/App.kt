package com.parohy.myfridge

import android.app.Application
import com.parohy.myfridge.api.dagger.AppComponent
import com.parohy.myfridge.api.dagger.AppModule
import com.parohy.myfridge.api.dagger.DaggerAppComponent
import com.parohy.myfridge.api.dagger.DatabaseModule
import timber.log.Timber

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .databaseModule(DatabaseModule(this))
            .build()
    }
}

fun Application.appComponent(): AppComponent = (this as App).appComponent