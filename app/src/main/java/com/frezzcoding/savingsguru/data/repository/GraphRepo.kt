package com.frezzcoding.savingsguru.data.repository

import com.frezzcoding.savingsguru.data.models.EstimatedSavings
import io.reactivex.Completable
import io.reactivex.Single

interface GraphRepo {

    fun getSavings() : Single<List<EstimatedSavings>>

    fun addSavingsEntry(entry : EstimatedSavings) : Completable

    fun updateEntryStatus(id : Int, entry : Boolean) : Completable

}