package com.frezzcoding.savingsguru.functionalities.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import com.frezzcoding.savingsguru.common.NotificationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(val notificationManager: NotificationManager, val context : Context): ViewModel() {

    fun activateNotifications(){
        notificationManager.setupNewNotification(context)
    }

    fun disableNotifications(){

    }

}