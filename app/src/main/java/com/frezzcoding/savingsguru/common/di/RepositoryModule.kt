package com.frezzcoding.savingsguru.common.di

import com.frezzcoding.savingsguru.data.repository.NewScenarioRepo
import com.frezzcoding.savingsguru.data.repository.NewScenarioRepoImpl
import com.frezzcoding.savingsguru.data.repository.ScenarioRepo
import com.frezzcoding.savingsguru.data.repository.ScenarioRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewScenarioRepo(repo : NewScenarioRepoImpl) : NewScenarioRepo

    @Binds
    abstract fun bindScenarioRepo(repo : ScenarioRepoImpl) : ScenarioRepo

}