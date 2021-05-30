package com.frezzcoding.savingsguru.functionalities.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frezzcoding.savingsguru.common.AbstractViewModel
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.data.repository.ScenarioRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ScenarioRepo
) : AbstractViewModel() {

    private val _scenarios = MutableLiveData<List<Scenario>>()
    val scenarios: LiveData<List<Scenario>> = _scenarios

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getScenarios() {
        launch {
            repo.getScenarios()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _loading.value = true }
                .subscribe({ list ->
                    (list as ArrayList).add(
                        Scenario(
                            0,
                            "",
                            0,
                            0,
                            0,
                            0
                        )
                    ) //add an empty scenario just for display
                    _scenarios.value = list
                    _loading.value = false
                }, {
                    _error.value = it.toString()
                })
        }
    }

    fun removeScenario(id: Int) {
        launch {
            repo.removeScenario(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getScenarios()
                }, {
                    _error.value = it.toString()
                })
        }
    }

}