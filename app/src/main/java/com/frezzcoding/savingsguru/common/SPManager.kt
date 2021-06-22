package com.frezzcoding.savingsguru.common

import android.content.Context
import android.content.SharedPreferences

class SPManager(val context: Context, private val sharedPreferences: SharedPreferences) {

    companion object {
        const val HINT_GRAPH_REMOVE = "android.content.SharedPreferences.hintGraphRemove"
        const val NOTIFICATION_SWITCHED_ON =
            "android.content.SharedPreferences.notificationSwitchedOn"
    }

    fun hintGraphRemoveActivated() = saveBoolean(HINT_GRAPH_REMOVE, true)

    fun getHintGraphRemove() = getBoolean(HINT_GRAPH_REMOVE)

    fun setNotificationSwitchState(switched: Boolean) =
        saveBoolean(NOTIFICATION_SWITCHED_ON, switched)

    fun getNotificationIsSwitchedOn() = getBoolean(NOTIFICATION_SWITCHED_ON)

    private fun saveBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    private fun saveInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    private fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    private fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    private fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    private fun getString(key: String): String? = sharedPreferences.getString(key, null)

}