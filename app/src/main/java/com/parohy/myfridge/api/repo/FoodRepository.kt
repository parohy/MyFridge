package com.parohy.myfridge.api.repo

import com.parohy.myfridge.api.model.Food
import io.reactivex.Completable
import io.reactivex.Observable

interface FoodRepository {
    fun source(): Observable<List<Food>>
    fun addFoodCompletable(food: Food): Completable
    fun addFood(food: Food)
    fun removeFood(food: Food)
}