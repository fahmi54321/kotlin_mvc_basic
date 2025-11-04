package com.example.mvc_kotlin.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvc_kotlin.screens.QuestionsListViewItemMvc
import com.example.mvc_kotlin.screens.QuestionsListViewItemMvcImpl
import com.example.mvc_kotlin.screens.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.listview.QuestionsListViewMvcImpl
import com.example.mvc_kotlin.screens.questiondetails.QuestionDetailsViewMvc
import com.example.mvc_kotlin.screens.questiondetails.QuestionDetailsViewMvcImpl
import com.example.mvc_kotlin.screens.recycler.QuestionsRecyclerMvcImpl

class ViewMvcFactory(
    val layoutInflater: LayoutInflater
) {
    fun getQuestionsListViewMvc(viewGroup: ViewGroup?): QuestionsListViewMvc{
        return QuestionsListViewMvcImpl(layoutInflater,viewGroup, this)
    }

    fun getQuestionsRecyclerMvc(viewGroup: ViewGroup?): QuestionsListViewMvc {
        return QuestionsRecyclerMvcImpl(layoutInflater,viewGroup, this)
    }

    fun getQuestionsListViewItemMvc(parent: ViewGroup): QuestionsListViewItemMvc {
        return QuestionsListViewItemMvcImpl(layoutInflater,parent)
    }

    fun getQuestionDetailsViewMvc(viewGroup: ViewGroup?): QuestionDetailsViewMvc {
        return QuestionDetailsViewMvcImpl(layoutInflater, viewGroup)
    }
}