package com.codejunior.inventoryapplication.utils.extension

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.codejunior.inventoryapplication.view.*

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

fun Activity.intentLoginFromActivity(): Intent {
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

fun Activity.intentCategoryFromActivity(): Intent {
    return Intent(this, CategoryView::class.java)
}

fun Fragment.intentCategoryFromFragment(): Intent {
    return Intent(requireActivity(), CategoryView::class.java)
}

fun Activity.intentRegisterFromActivity(): Intent {
    return Intent(this, RegisterView::class.java)
}


fun Activity.intentKardexFromActivity(): Intent {
    return Intent(this, KardexView::class.java)
}