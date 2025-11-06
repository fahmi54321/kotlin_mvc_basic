package com.example.mvc_kotlin.screens.listview

import android.os.Bundle
import com.example.mvc_kotlin.screens.QuestionsListController
import com.example.mvc_kotlin.screens.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.common.BaseActivity

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