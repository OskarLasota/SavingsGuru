package com.frezzcoding.savingsguru.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.frezzcoding.savingsguru.data.models.Scenario
import io.reactivex.Completable

@Dao
interface ScenarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(scenario: Scenario) : Completable



}