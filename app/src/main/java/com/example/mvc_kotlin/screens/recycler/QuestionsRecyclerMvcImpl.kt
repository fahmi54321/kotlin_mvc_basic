package com.example.mvc_kotlin.screens.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.adapter.recycler.QuestionsRecyclerAdapter
import com.example.mvc_kotlin.screens.common.BaseObservableViewMvc

class QuestionsRecyclerMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?
): BaseObservableViewMvc<QuestionsListViewMvc.Listener>(), QuestionsRecyclerAdapter.Listener, QuestionsListViewMvc {

    private var mRecyclerQuestions: RecyclerView
    private var mAdapter: QuestionsRecyclerAdapter

    init {
        setRootView(layoutInflater.inflate(R.layout.layout_questions_list_recycler,viewGroup,false))
        mRecyclerQuestions = findViewById(R.id.recycler_questions)
        mRecyclerQuestions.layoutManager = LinearLayoutManager(context)
        mAdapter = QuestionsRecyclerAdapter(layoutInflater, this)
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
}