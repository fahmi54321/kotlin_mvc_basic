package com.example.mvc_kotlin.questions

import com.example.mvc_kotlin.common.BaseObservable
import com.example.mvc_kotlin.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException

class FetchQuestionListUseCase(
    val stackoverflowApi: StackoverflowApi
): BaseObservable<FetchQuestionListUseCase.Listener>() {
    interface Listener{
        fun onQuestionFetchFailed()
        fun onQuestionFetched(questions: List<Question>)

    }

    suspend fun fetchQuestionsAndNotify(){
        try {
            val response = stackoverflowApi.lastActiveQuestions(20)
            if (response.isSuccessful && response.body() != null) {
                notifySuccess(response.body()!!.questions)
            } else {
                notifyFailure()
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                notifyFailure()
            }
        }
    }

    private fun notifySuccess(questions: List<Question>) {
        for(listener in getListeners()){
            listener.onQuestionFetched(questions)
        }
    }

    private fun notifyFailure() {
        for(listener in getListeners()){
            listener.onQuestionFetchFailed()
        }
    }
}