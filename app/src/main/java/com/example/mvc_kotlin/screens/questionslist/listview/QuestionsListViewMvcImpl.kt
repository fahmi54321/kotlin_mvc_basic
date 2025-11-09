package com.example.mvc_kotlin.screens.questionslist.listview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.questionslist.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.questionslist.adapter.listview.QuestionsAdapter
import com.example.mvc_kotlin.screens.common.ViewMvcFactory
import com.example.mvc_kotlin.screens.common.navdrawer.BaseNavDrawerViewMvc
import com.example.mvc_kotlin.screens.common.navdrawer.DrawerItems
import com.example.mvc_kotlin.screens.common.toolbar.ToolbarViewMvc

class QuestionsListViewMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?,
    viewMvcFactory: ViewMvcFactory
) : BaseNavDrawerViewMvc<QuestionsListViewMvc.Listener>(
    layoutInflater,
    viewGroup
), QuestionsAdapter.OnQuestionClickListener, QuestionsListViewMvc,
    ToolbarViewMvc.HamburgerClickListener {
    private var mLstQuestions: ListView
    private var questionsAdapter: QuestionsAdapter

    private var progress: ProgressBar

    private var toolbar: Toolbar
    private var toolbarViewMvc: ToolbarViewMvc


    init {
        setRootView(layoutInflater.inflate(R.layout.layout_questions_list,viewGroup,false))
        mLstQuestions = findViewById(R.id.lst_questions)
        progress = findViewById(R.id.progress)
        toolbar = findViewById(R.id.toolbar)
        questionsAdapter = QuestionsAdapter(context, this,viewMvcFactory)
        mLstQuestions.adapter = questionsAdapter

        toolbarViewMvc = viewMvcFactory.getToolbarViewMvc(viewGroup)
        toolbarViewMvc.setTitle("Test")
        toolbar.addView(toolbarViewMvc.getRootView())

        toolbarViewMvc.enableHamburgerButtonAndListen(this)
    }

    override fun  bindQuestions(questions: List<Question>) {
        questionsAdapter.clear()
        questionsAdapter.addAll(questions)
        questionsAdapter.notifyDataSetChanged()
    }

    override fun onQuestionClicked(question: Question) {
        for (listener in getListeners){
            listener.onQuestionClicked(question)
        }
    }

    override fun showProgressIndication() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgressIndication() {
        progress.visibility = View.GONE
    }

    override fun onDrawerItemClicked(item: DrawerItems) {
        for (listener in getListeners) {
            when (item) {
                DrawerItems.QUESTIONS_LIST -> {
                    listener.onQuestionsListClicked()
                }
            }
        }
    }

    override fun onHamburgerClicked() {
        openDrawer()
    }
}