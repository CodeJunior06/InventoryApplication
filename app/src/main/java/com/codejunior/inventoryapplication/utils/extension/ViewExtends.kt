package com.codejunior.inventoryapplication.utils.extension

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codejunior.inventoryapplication.view.LoginView
import com.codejunior.inventoryapplication.view.MainView
import com.codejunior.inventoryapplication.view.ProductsView
import com.codejunior.inventoryapplication.view.ProviderView

fun Activity.toastMessage(message: String) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
    toast.show()
}

fun Fragment.toastMessage(message: String) {
    val toast = Toast.makeText(requireContext(), message, Toast.LENGTH_LONG)
    toast.show()
}

fun Activity.intentMainFromActivity(): Intent {
    return Intent(this, MainView::class.java)
}

fun Activity.intentActivityLogin(): Intent {
    return Intent(this, LoginView::class.java)
}

fun Fragment.intentMainFromFragment(): Intent {
    return Intent(requireActivity(), MainView::class.java)
}

fun Activity.intentProviderFromActivity(): Intent {
    return Intent(this, ProviderView::class.java)
}

fun Activity.intentProductFromActivity(): Intent {
    return Intent(this, ProductsView::class.java)
}