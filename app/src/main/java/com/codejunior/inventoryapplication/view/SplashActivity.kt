package com.codejunior.inventoryapplication.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codejunior.inventoryapplication.databinding.ActivitySplashBinding
import com.codejunior.inventoryapplication.utils.extension.intentActivityLogin
import com.codejunior.inventoryapplication.utils.extension.intentActivityMain
import com.codejunior.inventoryapplication.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var _binding: ActivitySplashBinding
    private val binding get() = _binding
    private lateinit var context: Context
    private val splashViewModel:SplashViewModel by viewModels()
    private var bool:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this;
        bool = splashViewModel.getInitSession()
        startTimer()

    }

    private fun startTimer() {
        object : CountDownTimer(5200, 1500) {
            override fun onTick(p0: Long) {
                println("Timer Splash $p0")
            }

            override fun onFinish() {
                if(bool){
                    startActivity(intentActivityMain())
                    finish()
                    return
                }
                val i = Intent(context, LoginView::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                i.flags =  Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(i)
                finish()
            }

        }.start()
    }
}