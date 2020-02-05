package com.parohy.myfridge.api.dagger

import android.app.Application
import androidx.room.Room
import com.parohy.myfridge.api.room.Database
import com.parohy.myfridge.api.room.FoodDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule constructor(application: Application){
    private val database: Database = Room.databaseBuilder(
        application.applicationContext,
        Database::class.java,
        "fridge-db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun providesDatabase(): Database = database

    @Singleton
    @Provides
    fun providesFoodDAO(): FoodDAO = database.foodDAO()
}