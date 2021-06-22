package com.frezzcoding.savingsguru.functionalities.settings

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.frezzcoding.savingsguru.common.AbstractViewModel
import com.frezzcoding.savingsguru.common.NotificationManager
import com.frezzcoding.savingsguru.common.NotificationManager.Companion.NOTIFICATION_ID
import com.frezzcoding.savingsguru.common.NotificationManager.Companion.TIME_IN_MONTH
import com.frezzcoding.savingsguru.data.database.SavingsDao
import com.frezzcoding.savingsguru.data.database.ScenarioDao
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val savingsDao: SavingsDao,
    private val scenarioDao: ScenarioDao
) : AbstractViewModel() {

    private val _clearSuccess = MutableLiveData<Boolean>()
    val clearSuccess: LiveData<Boolean> = _clearSuccess

    fun activateNotifications(context: Context) {
        //todo these methods need to be in a seperate class that is injected into the viewmodel
        val intent = Intent(context, NotificationManager::class.java)
        val pi =
            PendingIntent.getBroadcast(
                context,
                NOTIFICATION_ID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.setRepeating(
            AlarmManager.RTC_WAKEUP,
            Calendar.getInstance().time.time + TIME_IN_MONTH,
            TIME_IN_MONTH,
            pi
        )
    }

    fun disableNotifications(context : Context) {
        //todo these methods need to be in a seperate class that is injected into the viewmodel
        val intent = Intent(context, NotificationManager::class.java)
        var pi = PendingIntent.getBroadcast(
            context,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.cancel(pi)
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