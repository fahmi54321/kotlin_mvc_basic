package com.example.mvc_kotlin.questions

import com.example.mvc_kotlin.common.BaseObservable
import com.example.mvc_kotlin.networking.StackoverflowApi
import kotlinx.coroutines.CancellationException

class FetchQuestionDetailsUseCase(
    val stackoverflowApi: StackoverflowApi
): BaseObservable<FetchQuestionDetailsUseCase.Listener>() {
    interface Listener{
        fun onQuestionDetailsFetched(questionDetails: QuestionDetails)
        fun onQuestionDetailsFetchFailed()
    }

    suspend fun fetchQuestionDetailsAndNotify(questionId: String){
        try {
            val response = stackoverflowApi.questionDetails(questionId)
            if (response.isSuccessful && response.body() != null) {
                notifySuccess(response.body()?.question)
            } else {
                notifyFailure()
            }
        } catch (t: Throwable) {
            if (t !is CancellationException) {
                notifyFailure()
            }
        }
    }

    private fun notifySuccess(question: QuestionDetails?) {
        if(question != null){
            for(listener in getListeners()){
                listener.onQuestionDetailsFetched(question)
            }
        }
    }

    private fun notifyFailure() {
        for (listener in getListeners()){
            listener.onQuestionDetailsFetchFailed()
        }
    }


}