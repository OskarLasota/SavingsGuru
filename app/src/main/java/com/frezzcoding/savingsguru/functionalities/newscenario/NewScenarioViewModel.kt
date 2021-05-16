package com.frezzcoding.savingsguru.functionalities.newscenario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.data.repository.NewScenarioRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class NewScenarioViewModel @Inject constructor(
    var repo: NewScenarioRepo,
    private var compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val _success = MutableLiveData<Scenario>()
    val success: LiveData<Scenario> = _success

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun addScenario(scenario: Scenario) {
        compositeDisposable.add(
            repo.addScenario(scenario)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _success.value = scenario
                }, {
                    sendErrorMessage(it.toString())
                })
        )
    }

    fun sendErrorMessage(message : String) {
        _error.postValue(message)
    }


}