package com.frezzcoding.savingsguru.data.repository

import com.frezzcoding.savingsguru.data.database.SavingsDao
import com.frezzcoding.savingsguru.data.models.EstimatedSavings
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class GraphRepoImpl @Inject constructor(val dao : SavingsDao): GraphRepo {

    override fun getSavings(): Single<List<EstimatedSavings>> {
        return Single.defer{
            return@defer dao.getSavings()
        }
    }

    override fun addSavingsEntry(entry: EstimatedSavings): Completable {
        return Completable.defer{
            return@defer dao.insert(entry)
        }
    }
}