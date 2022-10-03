package com.codejunior.inventoryapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.codejunior.inventoryapplication.databinding.ActivitySplashBinding


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        context = this;
        startTimer()

    }

    private fun startTimer() {
        object : CountDownTimer(5200, 1500) {
            override fun onTick(p0: Long) {
                println("Timer Splash $p0")
            }

            override fun onFinish() {
                val i = Intent(context, LoginView::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                i.flags =  Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(i)
                finish()
            }

        }.start()
    }
}