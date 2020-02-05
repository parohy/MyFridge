package com.parohy.myfridge.api.room

import androidx.room.*
import com.parohy.myfridge.api.model.Food
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

@Dao
abstract class FoodDAO {
    @Query("SELECT * FROM food_table")
    abstract fun getAllFoodObservable(): Observable<List<Food>>

    @Query("SELECT * FROM food_table WHERE name=:name")
    abstract fun getFoodSingle(name: String): Single<Food>

    @Query("SELECT * FROM food_table WHERE :name")
    abstract fun getFood(name: String): Food?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insertFoodCompletable(food: Food): Completable

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insertFood(food: Food)

    @Update(onConflict = OnConflictStrategy.ABORT)
    abstract fun updateFood(update: Food)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateFoodCompletable(update: Food): Completable

    @Query("UPDATE food_table SET amount=:amount WHERE name=:name")
    abstract fun updateFoodAmountCompletable(name: String, amount: Double): Completable

    open fun changeFoodAmount(food: Food, change: Int) {
        getFoodSingle(food.name)
            .subscribeOn(Schedulers.io())
            .flatMapCompletable { updateFoodAmountCompletable(it.name, when (change) {
                0 -> it.amount + food.amount
                else -> (it.amount - food.amount).let { a -> a.takeIf { a >= 0 } ?: 0.0 }
            }) }
            .onErrorResumeNext { insertFoodCompletable(food.apply { if (change == 1) amount = 0.0 }) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}