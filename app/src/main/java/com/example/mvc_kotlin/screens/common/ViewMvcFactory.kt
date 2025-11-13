package com.example.mvc_kotlin.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mvc_kotlin.screens.common.dialogs.infodialog.InfoMvcImpl
import com.example.mvc_kotlin.screens.common.dialogs.infodialog.InfoViewMvc
import com.example.mvc_kotlin.screens.common.dialogs.promptdialog.PromptMvcImpl
import com.example.mvc_kotlin.screens.common.dialogs.promptdialog.PromptViewMvc
import com.example.mvc_kotlin.screens.common.navdrawer.NavDrawerHelper
import com.example.mvc_kotlin.screens.common.navdrawer.NavDrawerViewMvc
import com.example.mvc_kotlin.screens.common.navdrawer.NavDrawerViewMvcImpl
import com.example.mvc_kotlin.screens.common.toolbar.ToolbarViewMvc
import com.example.mvc_kotlin.screens.questionslist.QuestionsListViewItemMvc
import com.example.mvc_kotlin.screens.questionslist.QuestionsListViewItemMvcImpl
import com.example.mvc_kotlin.screens.questionslist.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.questionslist.listview.QuestionsListViewMvcImpl
import com.example.mvc_kotlin.screens.questiondetails.QuestionDetailsViewMvc
import com.example.mvc_kotlin.screens.questiondetails.QuestionDetailsViewMvcImpl
import com.example.mvc_kotlin.screens.questionslist.recycler.QuestionsRecyclerMvcImpl

class ViewMvcFactory(
    private val layoutInflater: LayoutInflater,
    private val navDrawerHelper: NavDrawerHelper
) {
    fun getQuestionsListViewMvc(viewGroup: ViewGroup?): QuestionsListViewMvc{
        return QuestionsListViewMvcImpl(layoutInflater,viewGroup, this, navDrawerHelper)
    }

    fun getQuestionsRecyclerMvc(viewGroup: ViewGroup?): QuestionsListViewMvc {
        return QuestionsRecyclerMvcImpl(layoutInflater,viewGroup, this, navDrawerHelper)
    }

    fun getQuestionsListViewItemMvc(parent: ViewGroup): QuestionsListViewItemMvc {
        return QuestionsListViewItemMvcImpl(layoutInflater,parent)
    }

    fun getQuestionDetailsViewMvc(viewGroup: ViewGroup?): QuestionDetailsViewMvc {
        return QuestionDetailsViewMvcImpl(layoutInflater, viewGroup, this)
    }

    fun getToolbarViewMvc(viewGroup: ViewGroup?): ToolbarViewMvc {
        return ToolbarViewMvc(layoutInflater,viewGroup)
    }

    fun getNavDrawerViewMvc(viewGroup: ViewGroup?): NavDrawerViewMvc {
        return NavDrawerViewMvcImpl(layoutInflater,viewGroup)
    }

    fun getPromptViewMvc(viewGroup: ViewGroup?): PromptViewMvc {
        return PromptMvcImpl(
            layoutInflater,
            viewGroup
        )
    }

    fun getInfoViewMvc(viewGroup: ViewGroup?): InfoViewMvc {
        return InfoMvcImpl(
            layoutInflater,
            viewGroup
        )
    }
}