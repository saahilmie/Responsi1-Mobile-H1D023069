package com.example.responsi1mobileh1d023069

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023069.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val splashDuration = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.ivSplashLogo.alpha = 0f
        binding.tvAppName.alpha = 0f

        binding.ivSplashLogo.animate()
            .alpha(1f)
            .setDuration(1000)
            .start()

        binding.tvAppName.animate()
            .alpha(1f)
            .setDuration(1000)
            .setStartDelay(300)
            .start()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, splashDuration)
    }
}