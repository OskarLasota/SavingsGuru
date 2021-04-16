package com.frezzcoding.savingsguru.data.repository

import com.frezzcoding.savingsguru.data.models.Scenario
import javax.inject.Inject

class NewScenarioRepoImpl @Inject constructor() : NewScenarioRepo {


    override fun addScenario(scenario: Scenario) {
        println("reached repo")
    }

}