package com.frezzcoding.savingsguru.common

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber


abstract class AbstractViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    init {
        Timber.d("ViewModel: %s", this)
    }

    fun launch(job: () -> Disposable) {
        disposables.add(job())
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        Timber.d("ViewModel: %s cleared", this)
    }


}