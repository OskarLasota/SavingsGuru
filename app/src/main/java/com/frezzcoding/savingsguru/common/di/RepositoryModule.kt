package com.frezzcoding.savingsguru.common.di

import com.frezzcoding.savingsguru.data.repository.GraphRepo
import com.frezzcoding.savingsguru.data.repository.GraphRepoImpl
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
    abstract fun bindScenarioRepo(repo: ScenarioRepoImpl): ScenarioRepo

    @Binds
    abstract fun bindGraphRepo(repo: GraphRepoImpl): GraphRepo

}