package com.example.mvc_kotlin.screens.listview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListView
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.adapter.listview.QuestionsAdapter
import com.example.mvc_kotlin.screens.common.BaseObservableViewMvc

class QuestionsListViewMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?,
) : BaseObservableViewMvc<QuestionsListViewMvc.Listener>(), QuestionsAdapter.OnQuestionClickListener, QuestionsListViewMvc {
    private var mLstQuestions: ListView
    private var questionsAdapter: QuestionsAdapter


    init {
        setRootView(layoutInflater.inflate(R.layout.layout_questions_list,viewGroup,false))
        mLstQuestions = findViewById(R.id.lst_questions)
        questionsAdapter = QuestionsAdapter(context, this)
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
}