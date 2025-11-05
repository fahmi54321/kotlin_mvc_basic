package com.example.mvc_kotlin.screens.listview

import android.os.Bundle
import android.widget.Toast
import com.example.mvc_kotlin.questions.FetchQuestionListUseCase
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.common.BaseActivity
import com.example.mvc_kotlin.screens.questiondetails.QuestionDetailsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class QuestionsListActivity : BaseActivity(), QuestionsListViewMvc.Listener,
    FetchQuestionListUseCase.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var fetchQuestionListUseCase: FetchQuestionListUseCase

    private lateinit var mViewMvc: QuestionsListViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null)
        mViewMvc.registerListener(this)

        fetchQuestionListUseCase = getCompositionRoot().getFetchQuestionListUseCase()

        setContentView(mViewMvc.getRootView())
    }

    override fun onStart() {
        super.onStart()
        fetchQuestionListUseCase.registerListener(this)
        fetchQuestions()
    }

    override fun onStop() {
        super.onStop()
        fetchQuestionListUseCase.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestions() {
        mViewMvc.showProgressIndication()

        coroutineScope.launch {
            fetchQuestionListUseCase.fetchQuestionsAndNotify()
        }
    }


    override fun onQuestionClicked(question: Question) {
        QuestionDetailsActivity.start(this, question.id)
    }

    override fun onQuestionFetchFailed() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
    }

    override fun onQuestionFetched(questions: List<Question>) {
        mViewMvc.bindQuestions(questions)
        mViewMvc.hideProgressIndication()
    }
}