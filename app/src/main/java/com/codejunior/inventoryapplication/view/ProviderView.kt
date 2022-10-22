package com.codejunior.inventoryapplication.view

import android.graphics.Typeface
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.databinding.ActivityProveedoresBinding
import com.codejunior.inventoryapplication.view.fragments.FragmentAddProviders
import com.codejunior.inventoryapplication.view.fragments.FragmentListProviders
import com.codejunior.inventoryapplication.viewmodel.ProveedoresViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProviderView : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityProveedoresBinding

    private val providersViewModel: ProveedoresViewModel by viewModels()

    private val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProveedoresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.viewModelProveedores = providersViewModel

        binding.addProviders.setOnClickListener {
            replaceFragment(FragmentAddProviders())
            binding.listProviders.typeface = Typeface.DEFAULT
            binding.addProviders.typeface = Typeface.DEFAULT_BOLD
        }

        binding.listProviders.setOnClickListener {
            replaceFragment(FragmentListProviders())
            binding.addProviders.typeface = Typeface.DEFAULT
            binding.listProviders.typeface = Typeface.DEFAULT_BOLD

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
}