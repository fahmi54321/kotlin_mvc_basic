package com.example.mvc_kotlin.screens.questiondetails

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.QuestionDetails
import com.example.mvc_kotlin.screens.common.views.BaseViewMvc

class QuestionDetailsViewMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?
): BaseViewMvc(), QuestionDetailsViewMvc {

    private var mTxtQuestionTitle: TextView
    private var mTxtQuestionBody: TextView
    private var mProgressBar: ProgressBar

    init {
        setRootView(layoutInflater.inflate(R.layout.activity_question_details, viewGroup, false))

        mTxtQuestionTitle = findViewById(R.id.txt_question_title)
        mTxtQuestionBody = findViewById(R.id.txt_question_body)
        mProgressBar = findViewById(R.id.progress)
    }

    override fun bindQuestion(question: QuestionDetails) {
        mTxtQuestionTitle.setText(Html.fromHtml(question.title, Html.FROM_HTML_MODE_LEGACY))
        mTxtQuestionBody.setText(Html.fromHtml(question.body, Html.FROM_HTML_MODE_LEGACY))
    }

    override fun showProgressIndication() {
        mProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressIndication() {
        mProgressBar.visibility = View.GONE
    }
}