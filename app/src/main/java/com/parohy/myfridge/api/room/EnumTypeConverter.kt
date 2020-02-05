package com.parohy.myfridge.api.room

import androidx.room.TypeConverter
import com.parohy.myfridge.api.model.FoodType
import com.parohy.myfridge.api.model.Measurement

class EnumTypeConverter {
    @TypeConverter
    fun restoreFoodTypeEnum(foodType: String): FoodType = FoodType.valueOf(foodType)
    @TypeConverter
    fun saveFoodTypeEnumToString(foodType: FoodType) = foodType.name

    @TypeConverter
    fun restoreMeasurementEnum(measurement: String): Measurement = Measurement.valueOf(measurement)
    @TypeConverter
    fun saveMeasurementEnumToString(measurement: Measurement) = measurement.name
}