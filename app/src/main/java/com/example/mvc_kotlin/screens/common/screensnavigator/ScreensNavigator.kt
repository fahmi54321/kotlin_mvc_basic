package com.example.mvc_kotlin.screens.common.screensnavigator

import android.content.Context
import com.example.mvc_kotlin.screens.questiondetails.QuestionDetailsActivity

class ScreensNavigator(
    val context: Context
) {
    fun toDialogDetails(id: String){
        QuestionDetailsActivity.Companion.start(context, id)
    }
}