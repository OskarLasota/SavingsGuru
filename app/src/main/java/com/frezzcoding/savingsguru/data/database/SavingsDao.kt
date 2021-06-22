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
    fun insert(savings: EstimatedSavings): Completable

    @Query("SELECT * FROM table_savings")
    fun getSavings(): Single<List<EstimatedSavings>>

    @Query("UPDATE table_savings SET lastEntry =:status WHERE id=:id")
    fun updateEntryStatus(id: Int, status: Boolean): Completable

    @Query("UPDATE table_savings SET amount =:amount WHERE id=:id")
    fun updateSavingsAmount(id: Int, amount: Int): Completable

    @Query("DELETE from table_savings")
    fun clearTable(): Completable

    @Query("DELETE from table_savings WHERE id =:id")
    fun removeSavings(id: Int): Completable
}