package com.example.mvc_kotlin.screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.Question

class QuestionsListViewItemMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup
) : QuestionsListViewItemMvc {

    private val rootView: View = layoutInflater.inflate(R.layout.layout_question_list_item,viewGroup, false)
    private val mListeners = HashSet<QuestionsListViewItemMvc.Listener>(1)
    private var mTxtTitle: TextView

    private lateinit var mQuestion: Question

    init {
        mTxtTitle = findViewById(R.id.txt_title)

        getRootView().setOnClickListener {
            for(listener in mListeners){
                listener.onQuestionClicked(mQuestion)
            }
        }
    }

    private fun <T: View?> findViewById(@IdRes id: Int) : T{
        return rootView.findViewById<T>(id)
    }

    override fun getRootView(): View {
        return rootView
    }

    override fun registerListener(listener: QuestionsListViewItemMvc.Listener) {
        mListeners.add(listener)
    }

    override fun unregisterListener(listener: QuestionsListViewItemMvc.Listener) {
        mListeners.remove(listener)
    }

    override fun bindQuestion(question: Question) {
        mQuestion = question
        mTxtTitle.text = question.title

    }
}