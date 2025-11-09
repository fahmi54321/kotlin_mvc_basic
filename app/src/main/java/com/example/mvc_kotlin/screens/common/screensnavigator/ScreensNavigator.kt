package com.example.mvc_kotlin.screens.common.screensnavigator

import android.content.Context
import com.example.mvc_kotlin.screens.questiondetails.QuestionDetailsActivity
import com.example.mvc_kotlin.screens.questionslist.listview.QuestionsListActivity
import com.example.mvc_kotlin.screens.questionslist.recycler.QuestionsRecyclerListActivity

class ScreensNavigator(
    val context: Context
) {
    fun toDialogDetails(id: String){
        QuestionDetailsActivity.Companion.start(context, id)
    }

    fun toQuestionsListClearTop() {
        QuestionsListActivity.startClearTop(context)
    }

    fun toQuestionsRecyclerClearTop() {
        QuestionsRecyclerListActivity.startClearTop(context)
    }
}