package com.example.mvc_kotlin.screens.adapter.listview

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.QuestionsListViewItemMvc
import com.example.mvc_kotlin.screens.common.ViewMvcFactory

class QuestionsAdapter(
    private val context: Context,
    private val onQuestionClickListener: OnQuestionClickListener,
    private val viewMvcFactory: ViewMvcFactory
) : ArrayAdapter<Question>(context, 0), QuestionsListViewItemMvc.Listener {

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val viewMvc: QuestionsListViewItemMvc

        if (view == null) {
            viewMvc = viewMvcFactory.getQuestionsListViewItemMvc(
                parent
            )
            viewMvc.registerListener(this)
            view = viewMvc.getRootView()
            view.tag = viewMvc
        } else {
            viewMvc = view.tag as QuestionsListViewItemMvc
        }

        val question = getItem(position)
        if(question != null) {
            viewMvc.bindQuestion(question)
        }

        return view
    }

    override fun onQuestionClicked(question: Question) {
        onQuestionClickListener.onQuestionClicked(question)
    }
}