package com.codejunior.inventoryapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.codejunior.inventoryapplication.databinding.ActivityRegisterBinding
import com.codejunior.inventoryapplication.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var _registerBinding: ActivityRegisterBinding
    private val registerBinding get() = _registerBinding
    private val viewModelStart: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)
    }
}