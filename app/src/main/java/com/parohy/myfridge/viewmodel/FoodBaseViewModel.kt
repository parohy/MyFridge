package com.parohy.myfridge.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.parohy.myfridge.api.repo.FoodRepository
import com.parohy.myfridge.appComponent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class FoodBaseViewModel(application: Application): AndroidViewModel(application) {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    @Inject
    lateinit var foodRepository: FoodRepository

    init {
        application.appComponent().injectViewModel(this)
    }

    protected fun addDisposable(vararg d: Disposable) {
        compositeDisposable.addAll(*d)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}