package com.parohy.myfridge.api.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.parohy.myfridge.api.model.Food

@Database(entities = [Food::class], version = 1, exportSchema = false)
@TypeConverters(EnumTypeConverter::class)
abstract class Database: RoomDatabase() {
    abstract fun foodDAO(): FoodDAO
}