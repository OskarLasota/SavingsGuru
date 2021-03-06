package com.frezzcoding.savingsguru.functionalities.newscenario

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frezzcoding.savingsguru.common.AbstractViewModel
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.data.repository.ScenarioRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewScenarioViewModel @Inject constructor(
    var repo: ScenarioRepo
) : AbstractViewModel() {

    private val _success = MutableLiveData<Scenario>()
    val success: LiveData<Scenario> = _success

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun addScenario(scenario: Scenario) {
        launch {
            repo.addScenario(scenario)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _success.value = scenario
                }, {
                    Timber.d("ViewModel: %s %s", this, it.toString())
                })
        }
    }

    fun sendErrorMessage(message: String) {
        _error.postValue(message)
    }

}