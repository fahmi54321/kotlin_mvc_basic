package com.example.mvc_kotlin.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.common.BaseViewMvc

class QuestionsListViewItemMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup
) : BaseViewMvc(), QuestionsListViewItemMvc {

    private val mListeners = HashSet<QuestionsListViewItemMvc.Listener>(1)
    private var mTxtTitle: TextView

    private lateinit var mQuestion: Question

    init {
        setRootView(layoutInflater.inflate(R.layout.layout_question_list_item,viewGroup, false))
        mTxtTitle = findViewById(R.id.txt_title)

        getRootView().setOnClickListener {
            for(listener in mListeners){
                listener.onQuestionClicked(mQuestion)
            }
        }
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