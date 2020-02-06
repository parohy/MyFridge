package com.parohy.myfridge.ui.toc

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.parohy.myfridge.api.model.Food
import com.parohy.myfridge.api.repo.FoodDataSource
import com.parohy.myfridge.appComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class TableOfContentViewModel constructor(
    application: Application
) : AndroidViewModel(application) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mutableFoodList: MutableLiveData<List<Food>> = MutableLiveData()
    val foodList: LiveData<List<Food>>
        get() = mutableFoodList

    @Inject
    lateinit var foodRepository: FoodDataSource

    init {
        application.appComponent().injectViewModel(this)
        compositeDisposable.add(
            foodRepository
                .foodListSubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { mutableFoodList.postValue(it) }
        )
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}