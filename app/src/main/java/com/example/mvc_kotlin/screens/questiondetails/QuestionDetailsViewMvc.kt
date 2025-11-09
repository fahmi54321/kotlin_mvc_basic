package com.example.mvc_kotlin.screens.questiondetails

import com.example.mvc_kotlin.questions.QuestionDetails
import com.example.mvc_kotlin.screens.common.navdrawer.DrawerItems
import com.example.mvc_kotlin.screens.common.navdrawer.NavDrawerViewMvc
import com.example.mvc_kotlin.screens.common.views.ObservableViewMvc

interface QuestionDetailsViewMvc: ObservableViewMvc<QuestionDetailsViewMvc.Listener>, NavDrawerViewMvc {

    interface Listener{
        fun onNavigateUpClicked()
        fun onDrawerItemClicked(item: DrawerItems)
    }
    fun bindQuestion(question: QuestionDetails)
    fun showProgressIndication()
    fun hideProgressIndication()
}