package com.example.mvc_kotlin.screens

import android.view.View
import com.example.mvc_kotlin.questions.Question

interface QuestionsListViewItemMvc {
    interface Listener{
        fun onQuestionClicked(question: Question)
    }

    fun getRootView(): View

    fun registenerlistener(listener: Listener)
    fun unregisterListener(listener: Listener)
    fun bindQuestion(question: Question)
}