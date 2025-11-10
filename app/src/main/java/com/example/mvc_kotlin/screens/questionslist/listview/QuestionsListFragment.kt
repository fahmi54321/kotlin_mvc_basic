package com.example.mvc_kotlin.screens.questionslist.listview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvc_kotlin.screens.common.controller.BaseFragment
import com.example.mvc_kotlin.screens.questionslist.QuestionsListController
import com.example.mvc_kotlin.screens.questionslist.QuestionsListViewMvc

class QuestionsListFragment: BaseFragment() {
    private lateinit var questionsListController: QuestionsListController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mViewMvc: QuestionsListViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(container)
        questionsListController = getCompositionRoot().getQuestionsListController()
        questionsListController.bindView(mViewMvc)
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

    companion object {
        fun newInstance(): Fragment {
            return QuestionsListFragment()
        }
    }
}