package com.example.mvc_kotlin.screens.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.adapter.recycler.QuestionsRecyclerAdapter
import com.example.mvc_kotlin.screens.common.BaseObservableViewMvc
import com.example.mvc_kotlin.screens.common.ViewMvcFactory

class QuestionsRecyclerMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?,
    viewMvcFactory: ViewMvcFactory,
): BaseObservableViewMvc<QuestionsListViewMvc.Listener>(), QuestionsRecyclerAdapter.Listener, QuestionsListViewMvc {

    private var mRecyclerQuestions: RecyclerView
    private var mAdapter: QuestionsRecyclerAdapter
    private var progress: ProgressBar

    init {
        setRootView(layoutInflater.inflate(R.layout.layout_questions_list_recycler,viewGroup,false))
        mRecyclerQuestions = findViewById(R.id.recycler_questions)
        progress = findViewById(R.id.progress)
        mRecyclerQuestions.layoutManager = LinearLayoutManager(context)
        mAdapter = QuestionsRecyclerAdapter( this, viewMvcFactory)
        mRecyclerQuestions.adapter = mAdapter
    }


    override fun bindQuestions(questions: List<Question>) {
        mAdapter.bindQuestions(questions)
    }

    override fun onQuestionClicked(question: Question) {
        for (listener in getListeners) {
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