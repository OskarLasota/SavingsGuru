package com.frezzcoding.savingsguru.common

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.frezzcoding.savingsguru.MainActivity
import com.frezzcoding.savingsguru.R
import java.util.*

class NotificationManager : BroadcastReceiver() {

    private val TIME_IN_MONTH = 2_629_743_833L

    override fun onReceive(context: Context, intent: Intent?) {
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, "notification")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(context.getString(R.string.update_your_savings))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(
                    PendingIntent.getActivity(
                        context,
                        0,
                        Intent(context, MainActivity::class.java),
                        0
                    )
                )

        val notificationManager = NotificationManagerCompat.from(context)

        setupNewNotification(context)

        notificationManager.notify(200, builder.build())
    }


    fun setupNewNotification(context: Context) {
        val intent = Intent(context, NotificationManager::class.java)
        val pi =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        am.setRepeating(
            AlarmManager.RTC_WAKEUP,
            Calendar.getInstance().time.time + TIME_IN_MONTH,
            TIME_IN_MONTH,
            pi
        )
    }
}