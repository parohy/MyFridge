package com.parohy.myfridge.ui.toc

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.parohy.myfridge.api.model.Food
import com.parohy.myfridge.api.repo.FoodDataSource
import com.parohy.myfridge.api.repo.FoodRepository
import com.parohy.myfridge.appComponent
import com.parohy.myfridge.viewmodel.FoodBaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class TableOfContentViewModel constructor(
    application: Application
) : FoodBaseViewModel(application) {
    private val mutableFoodList: MutableLiveData<List<Food>> = MutableLiveData()
    val foodList: LiveData<List<Food>>
        get() = mutableFoodList


    init {
        addDisposable(
            foodRepository
                .source()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { mutableFoodList.postValue(it) }
        )
    }


}