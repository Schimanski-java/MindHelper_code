package com.example.mindhelper


import android.content.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)
        setupWithNavController(bottomNavigationView, navController)

        sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isAlarmSet = sharedPrefs.getBoolean("isAlarmSet", false)

        val updateCheckboxesIntent = Intent("com.example.mindhelper.UPDATE_CHECKBOXES")
        LocalBroadcastManager.getInstance(this)
            .sendBroadcast(updateCheckboxesIntent)

        if (!isAlarmSet) {
            val alarmServiceIntent = Intent(this, AlarmService::class.java)
            startService(alarmServiceIntent)
            sharedPrefs.edit().putBoolean("isAlarmSet", true).apply()
        }
    }
}
