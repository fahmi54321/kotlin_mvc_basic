package com.example.mvc_kotlin.screens.questionslist.recycler

import android.os.Bundle
import com.example.mvc_kotlin.screens.questionslist.QuestionsListController
import com.example.mvc_kotlin.screens.common.controller.BaseActivity

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

    override fun onBackPressed() {
        if(!questionsListController.onBackPressed()){
            super.onBackPressed()
        }
    }
}