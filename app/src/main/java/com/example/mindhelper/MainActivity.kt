package com.example.mindhelper


import android.Manifest
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.*
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val PERMISSION_POST_NOTIFICATIONS = Manifest.permission.POST_NOTIFICATIONS
    private val REQUEST_CODE_POST_NOTIFICATIONS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
        setupWithNavController(bottomNavigationView, navController)

        val updateCheckboxesIntent = Intent("com.example.mindhelper.UPDATE_CHECKBOXES")
        LocalBroadcastManager.getInstance(this)
            .sendBroadcast(updateCheckboxesIntent)

        val alarmServiceIntent = Intent(this, AlarmService::class.java)
        startService(alarmServiceIntent)

        requestPostNotificationsPermission()

        startService(Intent(this, AlarmService::class.java))
    }

    private fun requestPostNotificationsPermission() {
        if (ContextCompat.checkSelfPermission(this, PERMISSION_POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(PERMISSION_POST_NOTIFICATIONS), REQUEST_CODE_POST_NOTIFICATIONS)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_POST_NOTIFICATIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val notificationUtils = NotiUtils(this)
                val notification = notificationUtils.getNotificationBuilder().build()
                notificationUtils.getManager().notify(101, notification)
            } else {
                val message = "Povolení k notifikacím zamítnuto."
                val duration = android.widget.Toast.LENGTH_SHORT
                val toast = android.widget.Toast.makeText(applicationContext, message, duration)
                toast.show()
            }
        }
    }

}
