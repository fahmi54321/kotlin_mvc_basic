package com.example.mvc_kotlin.screens.questionslist.listview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvc_kotlin.screens.common.controller.BaseFragment
import com.example.mvc_kotlin.screens.questionslist.QuestionsListController
import com.example.mvc_kotlin.screens.questionslist.QuestionsListController.Companion.SAVED_STATE_SCREEN_STATE
import com.example.mvc_kotlin.screens.questionslist.QuestionsListViewMvc

class QuestionsListFragment: BaseFragment() {

    companion object {
        fun newInstance(): Fragment {
            return QuestionsListFragment()
        }
    }
    private lateinit var questionsListController: QuestionsListController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mViewMvc: QuestionsListViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(container)
        questionsListController = getCompositionRoot().getQuestionsListController()
        questionsListController.bindView(mViewMvc)
        if(savedInstanceState != null){
            restoreControllerState(savedInstanceState)
        }
        return mViewMvc.getRootView()
    }


    override fun onStart() {
        super.onStart()
        questionsListController.onStart()
    }

    override fun onStop() {
        super.onStop()
        questionsListController.onStop()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SAVED_STATE_SCREEN_STATE, questionsListController.getSavedState())
    }

    private fun restoreControllerState(savedInstanceState: Bundle) {
        questionsListController.restoreSavedState(
            savedInstanceState.getSerializable(SAVED_STATE_SCREEN_STATE) as QuestionsListController.Companion.SavedState
        )
    }
}