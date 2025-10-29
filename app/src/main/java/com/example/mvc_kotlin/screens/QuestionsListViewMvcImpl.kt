package com.example.mvc_kotlin.screens

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.annotation.IdRes
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.Question

class QuestionsListViewMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?,
) : QuestionsAdapter.OnQuestionClickListener, QuestionsListViewMvc {
    private var mLstQuestions: ListView
    private var questionsAdapter: QuestionsAdapter

    private val rootView:View = layoutInflater.inflate(R.layout.layout_questions_list,viewGroup,false)

    private val listeners = HashSet<QuestionsListViewMvc.Listener>()
    private val context: Context get() = rootView.context

    init {
        mLstQuestions = findViewById(R.id.lst_questions)
        questionsAdapter = QuestionsAdapter(context, this)
        mLstQuestions.adapter = questionsAdapter
    }

    override fun getRootView(): View{
        return rootView
    }

    private fun <T: View?> findViewById(@IdRes id: Int) : T{
        return rootView.findViewById<T>(id)
    }

    override fun registerListener(listener: QuestionsListViewMvc.Listener){
        listeners.add(listener)
    }

    override fun  unregisterListener(listener: QuestionsListViewMvc.Listener){
        listeners.remove(listener)
    }

    override fun  bindQuestions(questions: List<Question>) {
        questionsAdapter.clear()
        questionsAdapter.addAll(questions)
        questionsAdapter.notifyDataSetChanged()
    }

    override fun onQuestionClicked(question: Question) {
        for (listener in listeners){
            listener.onQuestionClicked(question)
        }
    }
}