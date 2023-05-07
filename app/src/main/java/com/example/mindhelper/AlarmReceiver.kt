package com.example.mindhelper

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.edit
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationUtils = NotiUtils(context)
        val notification = notificationUtils.getNotificationBuilder().build()
        notificationUtils.getManager().notify(101, notification)

        val sharedPrefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPrefs.edit {
            putBoolean("checkbox1", false).apply()
            putBoolean("checkbox2", false).apply()
            putBoolean("checkbox3", false).apply()
        }

        val updateCheckboxesIntent = Intent("com.example.mindhelper.UPDATE_CHECKBOXES")
        LocalBroadcastManager.getInstance(context)
            .sendBroadcast(updateCheckboxesIntent)
    }
}
