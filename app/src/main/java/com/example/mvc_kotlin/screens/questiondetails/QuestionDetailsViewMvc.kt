package com.example.mvc_kotlin.screens.questiondetails

import com.example.mvc_kotlin.questions.QuestionDetails
import com.example.mvc_kotlin.screens.common.views.ObservableViewMvc

interface QuestionDetailsViewMvc: ObservableViewMvc<QuestionDetailsViewMvc.Listener> {

    interface Listener{
        fun onNavigateUpClicked()
    }
    fun bindQuestion(question: QuestionDetails)
    fun showProgressIndication()
    fun hideProgressIndication()
}