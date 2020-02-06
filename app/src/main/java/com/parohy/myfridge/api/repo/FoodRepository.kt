package com.parohy.myfridge.api.repo

import com.parohy.myfridge.api.model.Food
import io.reactivex.Completable

interface FoodRepository {
    fun addFoodCompletable(food: Food): Completable
    fun addFood(food: Food)
    fun removeFood(food: Food)
}