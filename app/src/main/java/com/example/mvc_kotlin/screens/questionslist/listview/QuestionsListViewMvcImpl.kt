package com.example.mvc_kotlin.screens.questionslist.listview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.questionslist.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.questionslist.adapter.listview.QuestionsAdapter
import com.example.mvc_kotlin.screens.common.views.BaseObservableViewMvc
import com.example.mvc_kotlin.screens.common.ViewMvcFactory

class QuestionsListViewMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?,
    viewMvcFactory: ViewMvcFactory
) : BaseObservableViewMvc<QuestionsListViewMvc.Listener>(), QuestionsAdapter.OnQuestionClickListener, QuestionsListViewMvc {
    private var mLstQuestions: ListView
    private var questionsAdapter: QuestionsAdapter

    private var progress: ProgressBar


    init {
        setRootView(layoutInflater.inflate(R.layout.layout_questions_list,viewGroup,false))
        mLstQuestions = findViewById(R.id.lst_questions)
        progress = findViewById(R.id.progress)
        questionsAdapter = QuestionsAdapter(context, this,viewMvcFactory)
        mLstQuestions.adapter = questionsAdapter
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
}