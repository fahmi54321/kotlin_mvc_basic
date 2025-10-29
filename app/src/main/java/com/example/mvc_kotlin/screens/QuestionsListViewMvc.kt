package com.example.mvc_kotlin.screens

import android.view.View
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.common.ViewMvc

interface QuestionsListViewMvc: ViewMvc {
    interface Listener{
        fun onQuestionClicked(question: Question)
    }

    fun registerListener(listener: Listener)

    fun unregisterListener(listener: Listener)

    fun bindQuestions(questions: List<Question>)

    fun onQuestionClicked(question: Question)
}