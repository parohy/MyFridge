package com.parohy.myfridge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.parohy.myfridge.api.model.Food
import com.parohy.myfridge.api.model.FoodType
import com.parohy.myfridge.api.model.Measurement
import com.parohy.myfridge.api.repo.FoodRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.random.Random

class MainActivity : AppCompatActivity(R.layout.activity_main), Observer<List<Food>> {

    private val foodList: List<Food> = arrayListOf(
        Food("Apple", FoodType.EDIBLE, Measurement.SOLID, 1.0),
        Food("Water", FoodType.EDIBLE, Measurement.LIQUID, 1.0),
        Food("Chicken", FoodType.RAW, Measurement.SOLID, 1.0),
        Food("Milk", FoodType.EDIBLE, Measurement.LIQUID, 0.5)
    )

    private fun addRandomFood(): Food = foodList[Random.nextInt(0, 3)]

    @Inject
    lateinit var foodRepository: FoodRepository

    lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        application.appComponent().injectMainActivity(this)

        foodRepository
            .foodListSubject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this)

        text.setOnClickListener {
            foodRepository.removeFood(addRandomFood())
        }
    }

    override fun onComplete() {
        Timber.d("Complete")
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onNext(t: List<Food>) {
        for (food in t) {
            Timber.d("$food")
        }
    }

    override fun onError(e: Throwable) {
        Timber.d("Error: ${e.message}")
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
