package com.frezzcoding.savingsguru.common.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.frezzcoding.savingsguru.common.HintManager
import com.frezzcoding.savingsguru.common.NotificationManager
import com.frezzcoding.savingsguru.common.SPManager
import com.frezzcoding.savingsguru.data.database.AppDatabase
import com.frezzcoding.savingsguru.data.database.SavingsDao
import com.frezzcoding.savingsguru.data.database.ScenarioDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : AppDatabase{
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "database").build()
    }

    //check if this causes memory leak
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context) = context

    @Provides
    @Singleton
    fun provideSPManager(context: Context, sharedPreferences: SharedPreferences) = SPManager(context, sharedPreferences)

    @Provides
    @Singleton
    fun provideHintManager(context: Context) = HintManager(context)

    @Provides
    fun provideScenarioDao(database: AppDatabase) : ScenarioDao{
        return database.scenarioDao()
    }

    @Provides
    fun provideSavingsDao(database: AppDatabase) : SavingsDao{
        return database.savingsDao()
    }

    @Provides
    fun provideCompositeDisposable() : CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    fun provideNotificationManager(context: Context) : NotificationManager {
        return NotificationManager(context)
    }

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

}