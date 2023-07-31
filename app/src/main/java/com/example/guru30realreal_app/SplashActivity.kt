package com.example.guru30realreal_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.guru30realreal_app.auth.IntroActivity
import com.example.guru30realreal_app.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed( {
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        },3000)
    }
}