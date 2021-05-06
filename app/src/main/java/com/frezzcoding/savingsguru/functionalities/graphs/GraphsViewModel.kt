package com.frezzcoding.savingsguru.functionalities.graphs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frezzcoding.savingsguru.data.models.EstimatedSavings
import com.frezzcoding.savingsguru.data.repository.GraphRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class GraphsViewModel @Inject constructor(
    val repo: GraphRepo,
    val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val _savings = MutableLiveData<List<EstimatedSavings>>()
    val savings: LiveData<List<EstimatedSavings>> = _savings

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getSavings() {
        compositeDisposable.add(
            repo.getSavings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _loading.postValue(true)
                }.subscribe({
                    _savings.postValue(it)
                    _loading.postValue(false)
                }, {
                    _error.postValue(it.toString())
                    _loading.postValue(false)
                })
        )
    }

    fun addSavings(amount : Int) {
        compositeDisposable.add(
            repo.addSavingsEntry(EstimatedSavings(0, amount = amount, 0, false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _loading.postValue(true)
                }.subscribe({
                    _loading.postValue(false)
                },{
                    _loading.postValue(false)
                })
        )
    }

    fun updateEntryStatus(id : Int, last : Boolean){
        compositeDisposable.add(
            repo.updateEntryStatus(id, last)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _loading.postValue(true)
                }.subscribe({
                    _loading.postValue(false)
                },{
                    _loading.postValue(false)
                })
        )
    }


}