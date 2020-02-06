package com.parohy.myfridge.api.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class Food(
    @PrimaryKey
    val name: String,
    val type: FoodType,
    val measure: Measurement,
    var amount: Double,
    var foodImage: String? = null
) {
    @get:Ignore
    val foodImageUri: Uri?
        get() = Uri.parse(foodImage)

    override fun toString(): String {
        return "Food ID: $name with type $type and measurement as $measure, total amount ${getAmountString()}."
    }

    fun getAmountString(): String =
        String.format("%d%s", amount, when (measure) {
            Measurement.SOLID -> "p"
            else -> "l"
        })
}