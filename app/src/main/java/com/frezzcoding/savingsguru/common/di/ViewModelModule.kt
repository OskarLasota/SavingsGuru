package com.frezzcoding.savingsguru.common.di

import androidx.lifecycle.ViewModel
import com.frezzcoding.savingsguru.functionalities.home.HomeViewModel
import com.frezzcoding.savingsguru.functionalities.newscenario.NewScenarioViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(NewScenarioViewModel::class)
    abstract fun bindNewScenarioViewModel(viewModel : NewScenarioViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(vm : HomeViewModel) : ViewModel
}