package com.frezzcoding.savingsguru.data.repository

import com.frezzcoding.savingsguru.data.models.Scenario

interface NewScenarioRepo {

    fun addScenario(scenario : Scenario)

}