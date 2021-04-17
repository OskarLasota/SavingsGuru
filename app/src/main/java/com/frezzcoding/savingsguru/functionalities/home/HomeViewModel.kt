package com.frezzcoding.savingsguru.functionalities.home

import androidx.lifecycle.ViewModel
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.data.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repo : HomeRepo) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun getScenarios() {
        compositeDisposable.add(
            repo.getScenarios()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("scenarios $it")
                },{
                    println("error")
                })
        )
    }

    fun getScenario(id : Int){
        compositeDisposable.add(
            repo.getScenario(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                },{

                })
        )
    }
}