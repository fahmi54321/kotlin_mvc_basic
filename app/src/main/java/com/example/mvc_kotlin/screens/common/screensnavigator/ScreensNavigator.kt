package com.example.mvc_kotlin.screens.common.screensnavigator

import android.app.Activity
import android.content.Context
import com.example.mvc_kotlin.screens.questiondetails.QuestionDetailsActivity
import com.example.mvc_kotlin.screens.questionslist.listview.QuestionsListActivity
import com.example.mvc_kotlin.screens.questionslist.recycler.QuestionsRecyclerListActivity

class ScreensNavigator(
    val activity: Activity
) {

    private fun getContext(): Context{
        return activity
    }
    fun toDialogDetails(id: String){
        QuestionDetailsActivity.Companion.start(getContext(), id)
    }

    fun toQuestionsListClearTop() {
        QuestionsListActivity.startClearTop(getContext())
    }

    fun toQuestionsRecyclerClearTop() {
        QuestionsRecyclerListActivity.startClearTop(getContext())
    }

    fun onBackPressed() {
        activity.onBackPressed()
    }
}