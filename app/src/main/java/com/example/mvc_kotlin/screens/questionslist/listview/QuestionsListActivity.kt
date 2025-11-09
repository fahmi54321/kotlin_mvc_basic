package com.example.mvc_kotlin.screens.questionslist.listview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.mvc_kotlin.screens.common.controller.BaseActivity
import com.example.mvc_kotlin.screens.questionslist.QuestionsListController
import com.example.mvc_kotlin.screens.questionslist.QuestionsListViewMvc

class QuestionsListActivity : BaseActivity() {
    private lateinit var questionsListController: QuestionsListController

    companion object {
        fun startClearTop(context: Context) {
            val intent = Intent(context, QuestionsListActivity::class.java)
            intent.setFlags(intent.getFlags() or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mViewMvc: QuestionsListViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null)
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