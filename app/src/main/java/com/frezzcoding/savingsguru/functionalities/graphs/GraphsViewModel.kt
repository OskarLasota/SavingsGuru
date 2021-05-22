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

    private val _initialSavings = MutableLiveData<List<EstimatedSavings>>()
    val initialSavings: LiveData<List<EstimatedSavings>> = _initialSavings

    private val _updatedSavings = MutableLiveData<List<EstimatedSavings>>()
    val updatedSavings: LiveData<List<EstimatedSavings>> = _updatedSavings

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getInitialSavings() {
        compositeDisposable.add(
            repo.getSavings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _loading.postValue(true)
                }.subscribe({
                    _initialSavings.postValue(it)
                    _loading.postValue(false)
                }, {
                    _error.postValue(it.toString())
                    _loading.postValue(false)
                })
        )
    }

    fun addSavings(amount: Int) {
        val newEntry = EstimatedSavings(0, amount = amount, 0, false)
        compositeDisposable.add(
            repo.addSavingsEntry(newEntry)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _loading.postValue(true)
                }.subscribe({
                    getUpdatedSavings()
                    _loading.postValue(false)
                }, {
                    _loading.postValue(false)
                })
        )
    }

    fun updateEstimatedSavingsAmount(estimatedSavings: EstimatedSavings) {
        compositeDisposable.add(
            repo.updateSavingsEntry(estimatedSavings)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _loading.postValue(true)
                }.subscribe({
                    getUpdatedSavings()
                    _loading.postValue(false)
                }, {
                    _loading.postValue(false)
                })
        )
    }

    private fun getUpdatedSavings() {
        compositeDisposable.add(
            repo.getSavings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _loading.postValue(true)
                }.subscribe({
                    _updatedSavings.postValue(it)
                    _loading.postValue(false)
                }, {
                    _error.postValue(it.toString())
                    _loading.postValue(false)
                })
        )
    }

    fun removeEstimatedSavings(savings: EstimatedSavings) {
        compositeDisposable.add(
            repo.removeSavingsEntry(savings)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _loading.postValue(true)
                }.doOnComplete{
                    getUpdatedSavings()
                }.subscribe({
                    _loading.postValue(false)
                }, {
                    _error.postValue(it.toString())
                    _loading.postValue(true)
                })
        )
    }

}