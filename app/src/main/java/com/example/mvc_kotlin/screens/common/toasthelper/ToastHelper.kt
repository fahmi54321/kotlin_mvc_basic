package com.example.mvc_kotlin.screens.common.toasthelper

import android.content.Context
import android.widget.Toast

class ToastHelper(
    val context: Context
) {
    fun showUseCaseError(){
        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
    }
}