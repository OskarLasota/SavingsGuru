package com.frezzcoding.savingsguru.common.di

import com.frezzcoding.savingsguru.data.repository.HomeRepo
import com.frezzcoding.savingsguru.data.repository.HomeRepoImpl
import com.frezzcoding.savingsguru.data.repository.NewScenarioRepo
import com.frezzcoding.savingsguru.data.repository.NewScenarioRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNewScenarioRepo(repo : NewScenarioRepoImpl) : NewScenarioRepo

    @Binds
    abstract fun bindHomeRepo(repo : HomeRepoImpl) : HomeRepo

}