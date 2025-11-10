package com.example.mvc_kotlin.screens.questionslist

import com.example.mvc_kotlin.questions.FetchQuestionListUseCase
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.common.controller.BackPressDispatcher
import com.example.mvc_kotlin.screens.common.controller.BackPressedListener
import com.example.mvc_kotlin.screens.common.toasthelper.ToastHelper
import com.example.mvc_kotlin.screens.common.screensnavigator.ScreensNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class QuestionsListController(
    val fetchQuestionListUseCase: FetchQuestionListUseCase,
    val toastHelper: ToastHelper,
    val screensNavigator: ScreensNavigator,
    val backPressDispatcher: BackPressDispatcher
): QuestionsListViewMvc.Listener, FetchQuestionListUseCase.Listener, BackPressedListener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var mViewMvc: QuestionsListViewMvc

    fun onStart(){
        mViewMvc.registerListener(this)
        backPressDispatcher.registenerListener(this)
        fetchQuestionListUseCase.registerListener(this)
        fetchQuestions()
    }

    fun onStop(){
        mViewMvc.unregisterListener(this)
        backPressDispatcher.unregistenerListener(this)
        fetchQuestionListUseCase.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    fun bindView(mViewMvc: QuestionsListViewMvc){
        this.mViewMvc = mViewMvc
    }

    private fun fetchQuestions() {
        mViewMvc.showProgressIndication()

        coroutineScope.launch {
            fetchQuestionListUseCase.fetchQuestionsAndNotify()
        }
    }

    override fun onQuestionClicked(question: Question) {
        screensNavigator.toQuestionDetails(question.id)
    }

    override fun onQuestionsListClicked() {
        // this is the questions list screen, no-op
    }

    override fun onQuestionFetchFailed() {
        toastHelper.showUseCaseError()
    }

    override fun onQuestionFetched(questions: List<Question>) {
        mViewMvc.bindQuestions(questions)
        mViewMvc.hideProgressIndication()
    }

    override fun onBackPressed(): Boolean {
        if(mViewMvc.isDrawerOpen()){
            mViewMvc.closeDrawer()
            return true
        }else{
            return false
        }
    }
}