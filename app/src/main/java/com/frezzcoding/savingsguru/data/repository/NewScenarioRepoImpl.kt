package com.frezzcoding.savingsguru.data.repository

import com.frezzcoding.savingsguru.data.database.ScenarioDao
import com.frezzcoding.savingsguru.data.models.Scenario
import io.reactivex.Completable
import javax.inject.Inject

class NewScenarioRepoImpl @Inject constructor(var dao : ScenarioDao) : NewScenarioRepo {


    override fun addScenario(scenario: Scenario) : Completable{
        return dao.insert(scenario)
    }

}