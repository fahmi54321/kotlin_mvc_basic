package com.example.mvc_kotlin.screens.recycler

import android.os.Bundle
import com.example.mvc_kotlin.screens.QuestionsListController
import com.example.mvc_kotlin.screens.common.BaseActivity

class QuestionsRecyclerListActivity : BaseActivity() {

    private lateinit var questionsListController: QuestionsListController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsRecyclerMvc(null)
        questionsListController = getCompositionRoot().getQuestionsListController()
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