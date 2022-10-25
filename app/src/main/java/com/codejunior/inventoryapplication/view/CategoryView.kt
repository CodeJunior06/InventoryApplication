package com.codejunior.inventoryapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.codejunior.inventoryapplication.R
import com.codejunior.inventoryapplication.databinding.ActivityCategoryViewBinding
import com.codejunior.inventoryapplication.utils.extension.intentMainFromActivity
import com.codejunior.inventoryapplication.utils.extension.toastMessage
import com.codejunior.inventoryapplication.viewmodel.CategoryViewModel
import com.codejunior.inventoryapplication.viewmodel.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryView : AppCompatActivity() {

    private val categoryViewModel: CategoryViewModel by viewModels()

    private var _binding: ActivityCategoryViewBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCategoryViewBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        CoroutineScope(Dispatchers.Main).launch {
            categoryViewModel.getDataProvider()
        }

        categoryViewModel.constraint.observe(this) {
            if (it) {
                binding!!.constraintCategory.visibility = View.VISIBLE
                binding!!.constraintLoading.visibility = View.GONE
            } else {
                binding!!.constraintCategory.visibility = View.GONE
                binding!!.constraintLoading.visibility = View.VISIBLE
            }
        }

        categoryViewModel.arrayProviderName.observe(this) {
            val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, it)
            binding!!.dropdown.setAdapter(arrayAdapter)
            categoryViewModel.constraint.postValue(true)
        }

        binding!!.buttonCategory.setOnClickListener {
            categoryViewModel.getDataView(
                binding!!.edtNameCategory.editText?.text.toString(),
                binding!!.edtProvider.editText?.text.toString()
            )
        }

        categoryViewModel.error.observe(this) {
            toastMessage(it)
        }

        categoryViewModel.success.observe(this) {
            toastMessage(it)
        }

        categoryViewModel.navigation.observe(this) {
            when (it) {
                Navigation.GO_MAIN_VIEW -> {
                    startActivity(intentMainFromActivity())
                    finish()
                }
                else -> println()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        ManageSystemUI.hideSystemUI(window)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}