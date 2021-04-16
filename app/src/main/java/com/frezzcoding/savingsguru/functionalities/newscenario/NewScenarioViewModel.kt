package com.frezzcoding.savingsguru.functionalities.newscenario

import androidx.lifecycle.ViewModel
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.data.repository.NewScenarioRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewScenarioViewModel @Inject constructor(var repo : NewScenarioRepo) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun addScenario(scenario : Scenario){
        compositeDisposable.add(
            repo.addScenario(scenario)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("success")
                },{
                    println("handle error")
                })
        )
    }


}