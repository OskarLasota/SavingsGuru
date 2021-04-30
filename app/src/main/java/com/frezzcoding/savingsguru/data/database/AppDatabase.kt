package com.frezzcoding.savingsguru.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.frezzcoding.savingsguru.data.models.EstimatedSavings
import com.frezzcoding.savingsguru.data.models.Scenario


@Database(entities = [Scenario::class, EstimatedSavings::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun scenarioDao() : ScenarioDao
    abstract fun savingsDao() : SavingsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context : Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database"
                    ).build()
                }
                return instance
            }
        }
    }

}