package com.example.mvc_kotlin.screens.common.screensnavigator

import com.example.mvc_kotlin.screens.common.fragmentframehelper.FragmentFrameHelper
import com.example.mvc_kotlin.screens.questiondetails.QuestionDetailsFragment
import com.example.mvc_kotlin.screens.questionslist.listview.QuestionsListFragment
import com.example.mvc_kotlin.screens.questionslist.recycler.QuestionsRecyclerListFragment

class ScreensNavigator(
    val fragmentFrameHelper: FragmentFrameHelper
) {

    fun toQuestionDetails(id: String) {
        fragmentFrameHelper.replaceFragment(QuestionDetailsFragment.newInstance(id))
    }

    fun toQuestionsList() {
        fragmentFrameHelper.replaceFragmentAndClearBackstack(QuestionsListFragment.newInstance())
    }

    fun toQuestionsRecycler() {
        fragmentFrameHelper.replaceFragmentAndClearBackstack(QuestionsRecyclerListFragment.newInstance())
    }

    fun onBackPressed() {
        fragmentFrameHelper.navigateUp()
    }
}