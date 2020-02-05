package com.parohy.myfridge.api.repo

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class CompositeRepository {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    fun addDisposable(vararg d: Disposable) {
        compositeDisposable.addAll(*d)
    }
    fun clearDisposables() {
        compositeDisposable.clear()
    }
}