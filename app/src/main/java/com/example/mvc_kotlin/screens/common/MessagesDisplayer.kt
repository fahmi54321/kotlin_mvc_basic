package com.example.mvc_kotlin.screens.common

import android.content.Context
import android.widget.Toast

class MessagesDisplayer(
    val context: Context
) {
    fun showUseCaseError(){
        Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
    }
}