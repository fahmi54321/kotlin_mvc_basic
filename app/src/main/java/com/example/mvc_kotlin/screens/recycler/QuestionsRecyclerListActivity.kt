package com.example.mvc_kotlin.screens.recycler

import android.os.Bundle
import com.example.mvc_kotlin.questions.FetchQuestionListUseCase
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.QuestionsListViewMvc
import com.example.mvc_kotlin.screens.common.BaseActivity
import com.example.mvc_kotlin.screens.common.dialogs.ServerErrorDialogFragment
import com.example.mvc_kotlin.screens.questiondetails.QuestionDetailsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class QuestionsRecyclerListActivity : BaseActivity(), QuestionsListViewMvc.Listener,
    FetchQuestionListUseCase.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var fetchQuestionListUseCase: FetchQuestionListUseCase

    private lateinit var mViewMvc: QuestionsListViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsRecyclerMvc(null)
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
        supportFragmentManager.beginTransaction()
            .add(ServerErrorDialogFragment.Companion.newInstance(), null)
            .commitAllowingStateLoss()
        mViewMvc.hideProgressIndication()
    }

    override fun onQuestionFetched(questions: List<Question>) {
        mViewMvc.bindQuestions(questions)
        mViewMvc.hideProgressIndication()
    }
}