package com.example.mvc_kotlin.screens.questionslist.listview

import android.os.Bundle
import com.example.mvc_kotlin.screens.questionslist.QuestionsListController
import com.example.mvc_kotlin.screens.questionslist.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.common.controller.BaseActivity

class QuestionsListActivity : BaseActivity() {
    private lateinit var questionsListController: QuestionsListController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionsListController = getCompositionRoot().getQuestionsListController()
        val mViewMvc: QuestionsListViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null)
        questionsListController.bindView(mViewMvc)
        setContentView(mViewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        questionsListController.onStart()
    }

    override fun onStop() {
        super.onStop()
        questionsListController.onStop()
    }
}