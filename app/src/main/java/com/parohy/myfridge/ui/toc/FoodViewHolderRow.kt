package com.parohy.myfridge.ui.toc

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.parohy.myfridge.api.model.Food
import com.parohy.myfridge.api.model.FoodType
import kotlinx.android.synthetic.main.view_food_row.view.*

class FoodViewHolderRow(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name = itemView.foodName
    private val amount = itemView.foodAmount
    private val suppliedDate = itemView.foodLastTime
    private val rawNotice = itemView.rawFoodNotice
    private val image = itemView.foodImage

    fun setData(context: Context, food: Food) {
        setImage(context, food.foodImageUri)
        name.text = food.name
        amount.text = food.getAmountString()
        suppliedDate.text = food.lastTimeSupplied
        rawNotice.visibility = if (food.type == FoodType.RAW_UNSAFE) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun setImage(context: Context, uri: Uri?) {
        Glide.with(context)
            .load(uri)
            .into(image)
    }
}