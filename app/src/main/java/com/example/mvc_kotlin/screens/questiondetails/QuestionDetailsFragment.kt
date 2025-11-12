package com.example.mvc_kotlin.screens.questiondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvc_kotlin.screens.common.controller.BaseFragment

class QuestionDetailsFragment: BaseFragment() {
    private lateinit var questionDetailsController: QuestionDetailsController

    companion object{
        val ARG_QUESTION_ID: String = "ARG_QUESTION_ID"

        fun newInstance(questionId: String?): QuestionDetailsFragment {
            val args = Bundle()
            args.putString(ARG_QUESTION_ID, questionId)
            val fragment = QuestionDetailsFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(container)
        questionDetailsController = getCompositionRoot().getQuestionDetailsController()
        questionDetailsController.bindView(mViewMvc)

        return mViewMvc.getRootView()
    }

    override fun onStart() {
        super.onStart()
        questionDetailsController.setQuestionId(getQuestionId())
        questionDetailsController.onStart()

    }

    override fun onStop() {
        super.onStop()
        questionDetailsController.onStop()
    }

    private fun getQuestionId(): String{
        return arguments?.getString(ARG_QUESTION_ID,"")?:""
    }
}