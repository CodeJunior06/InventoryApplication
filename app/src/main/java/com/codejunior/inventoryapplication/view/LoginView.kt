package com.codejunior.inventoryapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codejunior.inventoryapplication.R

class LoginView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()
        ManageSystemUI.hideSystemUI(window)
    }
}