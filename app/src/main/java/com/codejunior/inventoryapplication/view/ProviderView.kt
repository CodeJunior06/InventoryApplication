package com.codejunior.inventoryapplication.view

import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.databinding.ActivityProveedoresBinding
import com.codejunior.inventoryapplication.utils.extension.intentMainFromActivity
import com.codejunior.inventoryapplication.view.fragments.FragmentAddProviders
import com.codejunior.inventoryapplication.view.fragments.FragmentListProviders
import com.codejunior.inventoryapplication.viewmodel.ProviderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProviderView : AppCompatActivity() {

    private var _binding: ActivityProveedoresBinding? = null
    private val binding get() = _binding
    private var onPressed = 1;

    private val providersViewModel: ProviderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityProveedoresBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.lifecycleOwner = this
        binding!!.viewModelProveedores = providersViewModel

        binding!!.addProviders.setOnClickListener {

            if (onPressed == 2) {
                replaceFragment(FragmentAddProviders())
                binding!!.listProviders.typeface = Typeface.DEFAULT
                binding!!.addProviders.typeface = Typeface.DEFAULT_BOLD
            }
            onPressed = 1
        }

        binding!!.listProviders.setOnClickListener {

            if (onPressed == 1) {
                replaceFragment(FragmentListProviders())
                binding!!.addProviders.typeface = Typeface.DEFAULT
                binding!!.listProviders.typeface = Typeface.DEFAULT_BOLD
            }
            onPressed = 2
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onStart() {
        super.onStart()
        ManageSystemUI.hideSystemUI(window)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(intentMainFromActivity())
        finish()
    }
}