package com.frezzcoding.savingsguru.data.repository

import com.frezzcoding.savingsguru.data.models.Scenario
import io.reactivex.Completable
import io.reactivex.Single

interface ScenarioRepo {

    fun getScenarios(): Single<List<Scenario>>

    fun getScenario(id: Int): Single<Scenario>

    fun removeScenario(id: Int): Completable

    fun addScenario(scenario: Scenario): Completable
}