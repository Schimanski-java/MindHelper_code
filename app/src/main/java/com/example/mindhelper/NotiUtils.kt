package com.example.mindhelper

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_MUTABLE
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotiUtils(base: Context) : ContextWrapper(base) {
    val MYCHANNEL_ID = "101"
    val MYCHANNEL_NAME = "App Alert Notification"
    val motivation: MutableList<String> = mutableListOf("S chutí do toho a půl je hotovo!", "Tajemstvím úspěchu je začít.", "Štěstí tvého života záleží na druhu tvých myšlenek.", "Pokud napravíte svou mysl, zbytek vašeho života se zařadí na správné místo.", "Věř, běž a dokážeš.")

    private var manager: NotificationManager? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels()
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannels() {
        val channel = NotificationChannel(MYCHANNEL_ID, MYCHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
        channel.enableVibration(true)

        getManager().createNotificationChannel(channel)
    }

    fun getManager() : NotificationManager {
        if (manager == null) manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return manager as NotificationManager
    }

    fun getNotificationBuilder(): NotificationCompat.Builder {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, FLAG_IMMUTABLE)
        return NotificationCompat.Builder(applicationContext, MYCHANNEL_ID)
            .setContentTitle("Nové úkoly")
            .setContentText(motivation.random())
            .setSmallIcon(R.drawable.mindhelper_noti)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }
}