package com.frezzcoding.savingsguru.functionalities.home

import androidx.lifecycle.ViewModel
import com.frezzcoding.savingsguru.data.repository.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repo : HomeRepo) : ViewModel() {



}