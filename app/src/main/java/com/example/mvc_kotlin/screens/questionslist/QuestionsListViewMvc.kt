package com.example.mvc_kotlin.screens.questionslist

import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.common.navdrawer.NavDrawerViewMvc
import com.example.mvc_kotlin.screens.common.views.ObservableViewMvc

interface QuestionsListViewMvc: ObservableViewMvc<QuestionsListViewMvc.Listener>, NavDrawerViewMvc {
    interface Listener{
        fun onQuestionClicked(question: Question)
        fun onQuestionsListClicked()
    }

    fun bindQuestions(questions: List<Question>)

    fun onQuestionClicked(question: Question)

    fun showProgressIndication()
    fun hideProgressIndication()
}