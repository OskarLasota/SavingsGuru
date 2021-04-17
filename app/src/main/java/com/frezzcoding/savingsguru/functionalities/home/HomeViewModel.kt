package com.frezzcoding.savingsguru.functionalities.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.data.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repo: HomeRepo) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _scenarios = MutableLiveData<List<Scenario>>()
    val scenarios: LiveData<List<Scenario>> = _scenarios

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error

    fun getScenarios() {
        compositeDisposable.add(
            repo.getScenarios()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _loading.value = true }
                .subscribe({ list ->
                    _scenarios.value = list
                    _loading.value = false
                }, {
                    _error.value = it.toString()
                })
        )
    }

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