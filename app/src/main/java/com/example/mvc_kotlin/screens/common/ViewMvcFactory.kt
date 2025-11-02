package com.example.mvc_kotlin.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvc_kotlin.screens.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.listview.QuestionsListViewMvcImpl
import com.example.mvc_kotlin.screens.recycler.QuestionsRecyclerMvcImpl

class ViewMvcFactory(
    val layoutInflater: LayoutInflater
) {
    fun getQuestionsListViewMvc(viewGroup: ViewGroup?): QuestionsListViewMvc{
        return QuestionsListViewMvcImpl(layoutInflater,viewGroup)
    }

    fun getQuestionsRecyclerMvc(viewGroup: ViewGroup?): QuestionsListViewMvc {
        return QuestionsRecyclerMvcImpl(layoutInflater,viewGroup)
    }
}