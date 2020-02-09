package com.parohy.myfridge.ui.toc

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.parohy.myfridge.R
import com.parohy.myfridge.api.model.Food

class FoodListAdapter(private val context: Context) : RecyclerView.Adapter<FoodViewHolderRow>() {
    private var foodList: List<Food> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolderRow =
        FoodViewHolderRow(
            LayoutInflater.from(context).inflate(
                R.layout.view_food_row,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = foodList.size

    override fun onBindViewHolder(holder: FoodViewHolderRow, position: Int) {
        holder.setData(context, foodList[position])
    }

    public fun updateList(newList: List<Food>) {
        foodList = newList
        notifyDataSetChanged()
    }
}