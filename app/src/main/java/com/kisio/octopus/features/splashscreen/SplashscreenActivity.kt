package com.kisio.octopus.features.splashscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.kisio.octopus.R
import com.kisio.octopus.features.connection.presentation.ConnectionActivity
import com.kisio.octopus.features.create.presentation.CreateActivity
import java.util.*
import kotlin.concurrent.schedule

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.setStatusBarColor(ContextCompat.getColor(baseContext, R.color.red_color))

        val sharedPref = getSharedPreferences(getString(R.string.prefs_file_name), Context.MODE_PRIVATE)
        Timer().schedule(2000) {
            var intent: Intent = if (sharedPref.getBoolean(getString(R.string.create_done), false)) {
                Intent(this@SplashscreenActivity.baseContext, ConnectionActivity::class.java)
            } else {
                Intent(this@SplashscreenActivity.baseContext, CreateActivity::class.java)
            }
            startActivity(intent)
        }
    }
}