package com.example.mvc_kotlin.screens.common.screensnavigator

import android.app.Activity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mvc_kotlin.screens.common.controller.FragmentFrameWrapper
import com.example.mvc_kotlin.screens.questiondetails.QuestionDetailsFragment
import com.example.mvc_kotlin.screens.questionslist.listview.QuestionsListFragment
import com.example.mvc_kotlin.screens.questionslist.recycler.QuestionsRecyclerListFragment

class ScreensNavigator(
    val fragmentManager: FragmentManager,
    val fragmentFrameWrapper: FragmentFrameWrapper
) {

    fun toQuestionDetails(id: String) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(
            fragmentFrameWrapper.getFragmentFrame().id,
            QuestionDetailsFragment.newInstance(id)
        ).commit()
    }

    fun toQuestionsList() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            fragmentFrameWrapper.getFragmentFrame().id,
            QuestionsListFragment.newInstance()
        ).commit()
    }

    fun toQuestionsRecycler() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(
            fragmentFrameWrapper.getFragmentFrame().id,
            QuestionsRecyclerListFragment.newInstance()
        ).commit()
    }

    fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            val context = fragmentFrameWrapper.getFragmentFrame().context
            if (context is Activity) {
                context.onBackPressed()
            }
        }
    }
}