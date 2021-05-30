package com.frezzcoding.savingsguru.functionalities.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frezzcoding.savingsguru.common.AbstractViewModel
import com.frezzcoding.savingsguru.common.NotificationManager
import com.frezzcoding.savingsguru.data.database.SavingsDao
import com.frezzcoding.savingsguru.data.database.ScenarioDao
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val notificationManager: NotificationManager,
    private val savingsDao: SavingsDao,
    private val scenarioDao: ScenarioDao
) : AbstractViewModel() {

    private val _clearSuccess = MutableLiveData<Boolean>()
    val clearSuccess: LiveData<Boolean> = _clearSuccess

    fun activateNotifications() {
        notificationManager.setupNewNotification()
    }

    fun disableNotifications() {
        notificationManager.disableNotifications()
    }

    fun clearRoomCache() {
        launch {
            savingsDao.clearTable()
                .mergeWith(scenarioDao.clearTable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _clearSuccess.postValue(true)
                }, {
                    Timber.d("ViewModel: %s %s", this, it.toString())
                })
        }
    }

}