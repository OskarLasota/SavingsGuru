package com.frezzcoding.savingsguru.data.repository

import com.frezzcoding.savingsguru.data.models.Scenario
import io.reactivex.Single

interface HomeRepo {

    fun getScenarios() : Single<List<Scenario>>

    fun getScenario() : Single<Scenario>

}