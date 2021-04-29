package com.frezzcoding.savingsguru.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.frezzcoding.savingsguru.data.models.EstimatedSavings
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SavingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(savings: EstimatedSavings) : Completable

    @Query("SELECT * FROM table_savings")
    fun getSavings() : Single<List<EstimatedSavings>>

}