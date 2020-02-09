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
    var lastTimeSupplied: String,
    var foodImage: String? = null
) {
    @get:Ignore
    val foodImageUri: Uri?
        get() = foodImage?.let { Uri.parse(it) }

    override fun toString(): String {
        return "Food ID: $name " +
                "with type $type " +
                "and measurement as $measure, " +
                "total amount ${getAmountString()} " +
                "was last time suppled $lastTimeSupplied."
    }

    fun getAmountString(): String = when (measure) {
        Measurement.SOLID -> String.format(
            "%dp", amount.toInt()
        )
        Measurement.LIQUID -> String.format(
            "%.2fp", amount
        )
        else -> "$amount"
    }
}