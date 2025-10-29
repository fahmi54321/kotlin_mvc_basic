package com.example.mvc_kotlin.screens

import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.common.ObservableViewMvc

interface QuestionsListViewItemMvc: ObservableViewMvc<QuestionsListViewItemMvc.Listener> {
    interface Listener{
        fun onQuestionClicked(question: Question)
    }
    fun bindQuestion(question: Question)
}