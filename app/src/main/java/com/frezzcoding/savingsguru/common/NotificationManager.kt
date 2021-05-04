package com.frezzcoding.savingsguru.common

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.frezzcoding.savingsguru.MainActivity

class NotificationManager : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var builder : NotificationCompat.Builder = NotificationCompat.Builder(context!!, "notification")
            //.setSmallIcon(R.drawable.transaprent_logo_small)
            .setContentTitle("Notification title")
            .setContentText("Context text")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(PendingIntent.getActivity(context, 0, Intent(context, MainActivity::class.java),0))

        var notificationManager = NotificationManagerCompat.from(context)

        setupNewNotification()

        notificationManager.notify(200, builder.build())
    }


    private fun setupNewNotification(){


    }
}