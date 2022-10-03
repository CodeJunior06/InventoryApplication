package com.codejunior.inventoryapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codejunior.inventoryapplication.databinding.ActivityRegisterBinding

class RegisterView : AppCompatActivity() {

    private lateinit var _registerBinding: ActivityRegisterBinding
    private val registerBinding get() = _registerBinding

    //private val viewModelStart: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)
    }
}