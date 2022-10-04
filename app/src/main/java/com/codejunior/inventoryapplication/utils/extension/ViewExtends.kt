package com.codejunior.inventoryapplication.utils.extension

import android.app.Activity
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.codejunior.inventoryapplication.view.MainView

fun Activity.toastMessage(message:String){
    val toast = Toast.makeText(this,message, android.widget.Toast.LENGTH_LONG)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}
 fun Activity.intentActivityMain() :Intent{
     return Intent(this, MainView::class.java)
 }