package com.parohy.myfridge.api.dagger

import android.app.Application
import com.parohy.myfridge.App
import com.parohy.myfridge.api.repo.FoodDataSource
import com.parohy.myfridge.api.repo.FoodRepository
import com.parohy.myfridge.ui.MainActivity
import com.parohy.myfridge.api.room.FoodDAO
import com.parohy.myfridge.ui.toc.TableOfContentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DatabaseModule::class])
interface AppComponent {
    fun injectApp(application: App)
    fun injectMainActivity(activity: MainActivity)

    fun injectViewModel(injectableViewModel: TableOfContentViewModel)

    fun application(): Application
    fun foodDAO(): FoodDAO
    fun foodRepository(): FoodRepository
}