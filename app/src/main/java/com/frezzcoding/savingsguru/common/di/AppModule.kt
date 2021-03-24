package com.frezzcoding.savingsguru.common.di

import android.content.Context
import androidx.room.Room
import com.frezzcoding.savingsguru.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : AppDatabase{
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "database").build()
    }

}