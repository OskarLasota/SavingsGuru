package com.frezzcoding.savingsguru.functionalities.settings

import androidx.lifecycle.ViewModel
import com.frezzcoding.savingsguru.common.NotificationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(val notificationManager: NotificationManager): ViewModel() {

    fun activateNotifications(){

    }

    fun disableNotifications(){

    }

}