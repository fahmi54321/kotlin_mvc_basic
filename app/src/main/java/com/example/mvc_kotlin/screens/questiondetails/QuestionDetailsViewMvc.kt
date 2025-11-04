package com.example.mvc_kotlin.screens.questiondetails

import com.example.mvc_kotlin.questions.QuestionDetails
import com.example.mvc_kotlin.screens.common.ViewMvc

interface QuestionDetailsViewMvc: ViewMvc {
    fun bindQuestion(question: QuestionDetails)
    fun showProgressIndication()
    fun hideProgressIndication()
}