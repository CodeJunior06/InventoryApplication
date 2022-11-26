package com.codejunior.inventoryapplication.view.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.codejunior.inventoryapplication.databinding.FragmentDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDialog constructor(onResponse: (Boolean) -> Unit): DialogFragment() {
    private lateinit var binding: FragmentDialogBinding
    private lateinit var dialog: AlertDialog.Builder
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogBinding.inflate(layoutInflater)

        dialog = AlertDialog.Builder(requireContext())

        dialog.setView(binding.root)

        dialog.setCancelable(true)

        val dialogCreate = dialog.create()
        dialogCreate.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialogCreate
    }

}