package com.frezzcoding.savingsguru.functionalities.graphs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frezzcoding.savingsguru.common.AbstractViewModel
import com.frezzcoding.savingsguru.data.models.EstimatedSavings
import com.frezzcoding.savingsguru.data.repository.GraphRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class GraphsViewModel @Inject constructor(
    val repo: GraphRepo
) : AbstractViewModel() {

    private val _initialSavings = MutableLiveData<List<EstimatedSavings>>()
    val initialSavings: LiveData<List<EstimatedSavings>> = _initialSavings

    private val _updatedSavings = MutableLiveData<List<EstimatedSavings>>()
    val updatedSavings: LiveData<List<EstimatedSavings>> = _updatedSavings

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getInitialSavings() {
        launch {
            repo.getSavings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _loading.postValue(true)
                }.subscribe({
                    _initialSavings.postValue(it)
                    _loading.postValue(false)
                }, {
                    Timber.d("ViewModel: %s %s", this, it.toString())
                    _loading.postValue(false)
                })
        }
    }

    fun addSavings(amount: Int) {
        val newEntry = EstimatedSavings(0, amount = amount, 0, false)
        launch {
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
                    Timber.d("ViewModel: %s %s", this, it.toString())
                })
        }
    }

    fun updateEstimatedSavingsAmount(estimatedSavings: EstimatedSavings) {
        launch {
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
                    Timber.d("ViewModel: %s %s", this, it.toString())
                })
        }
    }

    private fun getUpdatedSavings() {
        launch {
            repo.getSavings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _loading.postValue(true)
                }.subscribe({
                    _updatedSavings.postValue(it)
                    _loading.postValue(false)
                }, {
                    _loading.postValue(false)
                    Timber.d("ViewModel: %s %s", this, it.toString())
                })
        }
    }

    fun removeEstimatedSavings(savings: EstimatedSavings) {
        launch {
            repo.removeSavingsEntry(savings)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _loading.postValue(true)
                }.doOnComplete {
                    getUpdatedSavings()
                }.subscribe({
                    _loading.postValue(false)
                }, {
                    _loading.postValue(false)
                    Timber.d("ViewModel: %s %s", this, it.toString())
                })
        }
    }

}