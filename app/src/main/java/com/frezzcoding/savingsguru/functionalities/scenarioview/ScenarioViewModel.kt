package com.frezzcoding.savingsguru.functionalities.scenarioview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.data.repository.ScenarioRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ScenarioViewModel @Inject constructor(val compositeDisposable : CompositeDisposable, val repo : ScenarioRepo): ViewModel() {

    private val _scenario = MutableLiveData<List<Scenario>>()
    val scenario: LiveData<List<Scenario>> = _scenario

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    fun getScenario(id: Int) {
        compositeDisposable.add(
            repo.getScenario(id)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { _loading.value = true }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ scenario ->
                    _loading.value = false
                }, {
                    _error.value = it.toString()
                })
        )
    }

}