package com.frezzcoding.savingsguru.data.repository

import com.frezzcoding.savingsguru.data.models.Scenario
import io.reactivex.Completable
import io.reactivex.Single

interface NewScenarioRepo {

    fun addScenario(scenario : Scenario) : Completable

}