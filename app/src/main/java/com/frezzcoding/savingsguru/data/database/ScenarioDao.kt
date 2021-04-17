package com.frezzcoding.savingsguru.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.frezzcoding.savingsguru.data.models.Scenario
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ScenarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(scenario: Scenario) : Completable

    @Query("SELECT * FROM table_scenarios")
    fun getScenarios() : Single<List<Scenario>>

    @Query("SELECT * FROM table_scenarios WHERE id = :id")
    fun getScenario(id : Int) : Single<Scenario>

}