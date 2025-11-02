package com.example.mvc_kotlin.screens.adapter.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.QuestionsListViewItemMvc
import com.example.mvc_kotlin.screens.common.ViewMvcFactory

class QuestionsRecyclerAdapter(
    private val listener: Listener,
    private val viewMvcFactory: ViewMvcFactory,
) : RecyclerView.Adapter<QuestionsRecyclerAdapter.MyViewHolder>(),
    QuestionsListViewItemMvc.Listener {

    interface Listener {
        fun onQuestionClicked(question: Question)
    }

    class MyViewHolder(val viewMvc: QuestionsListViewItemMvc) :
        RecyclerView.ViewHolder(viewMvc.getRootView())

    private var questions: List<Question> = emptyList()

    fun bindQuestions(questions: List<Question>) {
        this.questions = ArrayList(questions)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewMvc = viewMvcFactory.getQuestionsListViewItemMvc(parent)
        viewMvc.registerListener(this)
        return MyViewHolder(viewMvc)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewMvc.bindQuestion(questions[position])
    }

    override fun getItemCount(): Int = questions.size

    override fun onQuestionClicked(question: Question) {
        listener.onQuestionClicked(question)
    }
}