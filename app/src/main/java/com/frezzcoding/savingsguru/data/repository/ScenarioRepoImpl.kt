package com.frezzcoding.savingsguru.data.repository

import com.frezzcoding.savingsguru.data.database.ScenarioDao
import com.frezzcoding.savingsguru.data.models.Scenario
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ScenarioRepoImpl @Inject constructor(private val dao: ScenarioDao) : ScenarioRepo {
    override fun getScenarios(): Single<List<Scenario>> {
        return Single.defer {
            return@defer dao.getScenarios()
        }
    }

    override fun getScenario(id : Int): Single<Scenario> {
        return Single.defer{
            return@defer dao.getScenario(id)
        }
    }

    override fun removeScenario(id: Int): Completable {
        return Completable.defer {
            return@defer dao.removeScenario(id)
        }
    }


}