package com.parohy.myfridge.api.repo

import com.parohy.myfridge.api.model.Food
import com.parohy.myfridge.api.room.FoodDAO
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(private val foodDAO: FoodDAO): CompositeRepository() {
    companion object {
        const val ADD: Int = 0
        const val DEL: Int = 1
    }
    val foodListSubject: BehaviorSubject<List<Food>> = BehaviorSubject.create()

    init {
        foodDAO.getAllFoodObservable()
            .subscribeOn(Schedulers.io())
            .doOnDispose { Timber.d("Get all query disposed") }
            .subscribe(foodListSubject)
    }

    fun addFoodCompletable(food: Food): Completable = foodDAO.insertFoodCompletable(food)
    fun addFood(food: Food) {
        foodDAO.changeFoodAmount(food, ADD)
    }
    fun removeFood(food: Food) {
        foodDAO.changeFoodAmount(food, DEL)
    }
}