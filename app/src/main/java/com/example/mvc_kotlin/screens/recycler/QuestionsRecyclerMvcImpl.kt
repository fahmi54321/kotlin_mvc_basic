package com.example.mvc_kotlin.screens.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.adapter.recycler.QuestionsRecyclerAdapter

class QuestionsRecyclerMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?
): QuestionsListViewMvc, QuestionsRecyclerAdapter.Listener {

    private var mRecyclerQuestions: RecyclerView
    private var mAdapter: QuestionsRecyclerAdapter

    private val rootView: View = layoutInflater.inflate(R.layout.layout_questions_list_recycler,viewGroup,false)

    private val listeners = HashSet<QuestionsListViewMvc.Listener>()
    private val context: Context get() = rootView.context

    init {
        mRecyclerQuestions = findViewById(R.id.recycler_questions)
        mRecyclerQuestions.layoutManager = LinearLayoutManager(context)
        mAdapter = QuestionsRecyclerAdapter(layoutInflater, this)
        mRecyclerQuestions.adapter = mAdapter
    }

    private fun <T: View?> findViewById(@IdRes id: Int) : T{
        return rootView.findViewById<T>(id)
    }


    override fun registerListener(listener: QuestionsListViewMvc.Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: QuestionsListViewMvc.Listener) {
        listeners.remove(listener)
    }

    override fun bindQuestions(questions: List<Question>) {
        mAdapter.bindQuestions(questions)
    }

    override fun getRootView(): View {
        return rootView
    }

    override fun onQuestionClicked(question: Question) {
        for (listener in listeners) {
            listener.onQuestionClicked(question)
        }
    }
}