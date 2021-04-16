package com.frezzcoding.savingsguru.functionalities.newscenario

import androidx.lifecycle.ViewModel
import com.frezzcoding.savingsguru.data.models.Scenario
import com.frezzcoding.savingsguru.data.repository.NewScenarioRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewScenarioViewModel @Inject constructor(var repo : NewScenarioRepo) : ViewModel() {

    fun addScenario(scenario : Scenario){
        repo.addScenario(scenario)
    }


}