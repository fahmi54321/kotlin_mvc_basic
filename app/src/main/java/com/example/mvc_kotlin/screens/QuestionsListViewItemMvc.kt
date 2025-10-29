package com.example.mvc_kotlin.screens

import android.view.View
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.common.ViewMvc

interface QuestionsListViewItemMvc: ViewMvc {
    interface Listener{
        fun onQuestionClicked(question: Question)
    }

    fun registenerlistener(listener: Listener)
    fun unregisterListener(listener: Listener)
    fun bindQuestion(question: Question)
}