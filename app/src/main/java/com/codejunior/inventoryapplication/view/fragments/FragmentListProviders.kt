package com.codejunior.inventoryapplication.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codejunior.inventoryapplication.R


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentListProviders.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentListProviders : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_providers, container, false)
    }
}