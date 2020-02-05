package com.parohy.myfridge.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class Food(
    @PrimaryKey
    val name: String,
    val type: FoodType,
    val measure: Measurement,
    var amount: Double
) {
    override fun toString(): String {
        return "Food ID: $name with type $type and measurement as $measure, total amount $amount."
    }
}