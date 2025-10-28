package com.example.mvc_kotlin.screens

import android.view.View
import com.example.mvc_kotlin.questions.Question

interface QuestionsListViewMvc {
    interface Listener{
        fun onQuestionClicked(question: Question)
    }

    fun registerListener(listener: Listener)

    fun unregisterListener(listener: Listener)

    fun bindQuestions(questions: List<Question>)

    fun getRootView(): View

    fun onQuestionClicked(question: Question)
}