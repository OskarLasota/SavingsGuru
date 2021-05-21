package com.frezzcoding.savingsguru.functionalities.settings

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frezzcoding.savingsguru.common.NotificationManager
import com.frezzcoding.savingsguru.data.database.SavingsDao
import com.frezzcoding.savingsguru.data.database.ScenarioDao
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val notificationManager: NotificationManager,
    val context: Context,
    val savingsDao: SavingsDao,
    val scenarioDao: ScenarioDao,
    var compositeDisposable: CompositeDisposable
) : ViewModel() {


    private val _clearSuccess = MutableLiveData<Boolean>()
    val clearSuccess : LiveData<Boolean> = _clearSuccess

    fun activateNotifications() {
        notificationManager.setupNewNotification(context)
    }

    fun disableNotifications() {

    }

    fun clearRoomCache() {
        compositeDisposable.add(
            savingsDao.clearTable()
                .mergeWith(scenarioDao.clearTable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _clearSuccess.postValue(true)
                },{

                })

        )
    }

}