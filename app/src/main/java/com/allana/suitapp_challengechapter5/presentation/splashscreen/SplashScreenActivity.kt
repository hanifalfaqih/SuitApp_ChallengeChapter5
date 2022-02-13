package com.allana.suitapp_challengechapter5.presentation.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.allana.suitapp_challengechapter5.databinding.ActivitySplashScreenBinding
import com.allana.suitapp_challengechapter5.preference.NamePlayerPreference
import com.allana.suitapp_challengechapter5.presentation.intro.IntroActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val namePlayerPreference: NamePlayerPreference by lazy {
        NamePlayerPreference(this)
    }

    private val timer: CountDownTimer by lazy {
        object: CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {
                namePlayerPreference.name = null
            }

            override fun onFinish() {
                val intent = Intent(this@SplashScreenActivity, IntroActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setTimer()
    }

    private fun setTimer() {
        timer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}