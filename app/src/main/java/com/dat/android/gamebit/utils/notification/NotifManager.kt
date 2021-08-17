package com.dat.android.gamebit.utils.notification

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

object NotifManager {
    private const val FOUR_HOURS = 14400000L
    private const val TEST_DIFF = 20_000L

    fun setAlarm(context: Context?) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent
            .getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        val alarmManager = context?.getSystemService(Activity.ALARM_SERVICE) as AlarmManager

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            getFirstTime(),
            AlarmManager.INTERVAL_HOUR,
            pendingIntent
        )
    }

    private fun getFirstTime(): Long {
        var presentTime = Calendar.getInstance().timeInMillis
        var premierTime = presentTime + TEST_DIFF
        return premierTime
    }
}