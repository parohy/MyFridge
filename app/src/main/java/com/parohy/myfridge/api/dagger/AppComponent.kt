package com.parohy.myfridge.api.dagger

import android.app.Application
import com.parohy.myfridge.App
import com.parohy.myfridge.MainActivity
import com.parohy.myfridge.api.room.FoodDAO
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent {
    fun injectApp(application: App)
    fun injectMainActivity(activity: MainActivity)

    fun application(): Application
    fun foodDAO(): FoodDAO
}