package com.example.mvc_kotlin.screens.questiondetails

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.QuestionDetails
import com.example.mvc_kotlin.screens.common.ViewMvcFactory
import com.example.mvc_kotlin.screens.common.navdrawer.BaseNavDrawerViewMvc
import com.example.mvc_kotlin.screens.common.navdrawer.DrawerItems
import com.example.mvc_kotlin.screens.common.toolbar.ToolbarViewMvc
import com.example.mvc_kotlin.screens.common.views.BaseObservableViewMvc

class QuestionDetailsViewMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?,
    viewMvcFactory: ViewMvcFactory
): BaseNavDrawerViewMvc<QuestionDetailsViewMvc.Listener>(
    layoutInflater,
    viewGroup
), QuestionDetailsViewMvc,
    ToolbarViewMvc.NavigateUpClickListener {

    private var mTxtQuestionTitle: TextView
    private var mTxtQuestionBody: TextView
    private var mProgressBar: ProgressBar
    private var toolbar: Toolbar
    private var toolbarViewMvc: ToolbarViewMvc

    init {
        setRootView(layoutInflater.inflate(R.layout.activity_question_details, viewGroup, false))

        mTxtQuestionTitle = findViewById(R.id.txt_question_title)
        mTxtQuestionBody = findViewById(R.id.txt_question_body)
        mProgressBar = findViewById(R.id.progress)
        toolbar = findViewById(R.id.toolbar)

        toolbarViewMvc = viewMvcFactory.getToolbarViewMvc(viewGroup)
        toolbarViewMvc.setTitle("Details")
        toolbarViewMvc.enableUpButtonAndListen(this)
        toolbar.addView(toolbarViewMvc.getRootView())
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

    override fun onNavigateUpClicked() {
        for(listener in getListeners){
            listener.onNavigateUpClicked()
        }
    }

    override fun onDrawerItemClicked(item: DrawerItems) {
        for(listener in getListeners){
            listener.onDrawerItemClicked(item)
        }
    }
}