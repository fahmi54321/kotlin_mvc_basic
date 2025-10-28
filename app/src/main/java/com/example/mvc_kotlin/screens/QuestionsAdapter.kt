package com.example.mvc_kotlin.screens

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.Question

class QuestionsAdapter(
    context: Context,
    private val onQuestionClickListener: OnQuestionClickListener
) : ArrayAdapter<Question>(context, 0) {

    interface OnQuestionClickListener {
        fun onQuestionClicked(question: Question)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_question_list_item, parent, false)

        val question = getItem(position)

        // Bind data ke tampilan
        val txtTitle: TextView = view.findViewById(R.id.txt_title)
        txtTitle.text = question?.title

        // Set listener
        view.setOnClickListener {
            question?.let { onQuestionClickListener.onQuestionClicked(it) }
        }

        return view
    }
}