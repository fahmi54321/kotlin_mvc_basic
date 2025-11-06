package com.example.mvc_kotlin.common.dependencyinjection

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import com.example.mvc_kotlin.networking.StackoverflowApi
import com.example.mvc_kotlin.questions.FetchQuestionDetailsUseCase
import com.example.mvc_kotlin.questions.FetchQuestionListUseCase
import com.example.mvc_kotlin.screens.QuestionsListController
import com.example.mvc_kotlin.screens.common.MessagesDisplayer
import com.example.mvc_kotlin.screens.common.ScreensNavigator
import com.example.mvc_kotlin.screens.common.ViewMvcFactory

class ControllerCompositionRoot(
    val compositionRoot: CompositionRoot,
    val activity: Activity
) {

    private fun getLayoutInflater():LayoutInflater{
        return LayoutInflater.from(activity)
    }

    fun getStackoveflowApi(): StackoverflowApi {
        return compositionRoot.getStackoveflowApi()
    }

    fun getViewMvcFactory(): ViewMvcFactory{
        return ViewMvcFactory(getLayoutInflater())
    }

    fun getFetchQuestionDetailsUseCase(): FetchQuestionDetailsUseCase {
        return FetchQuestionDetailsUseCase(getStackoveflowApi())
    }

    fun getFetchQuestionListUseCase(): FetchQuestionListUseCase {
        return FetchQuestionListUseCase(getStackoveflowApi())
    }

    fun getQuestionsListController(): QuestionsListController {
        return QuestionsListController(
            getFetchQuestionListUseCase(),
            getMessagesDisplayer(),
            getScreenNavigator()
        )
    }

    private fun getScreenNavigator(): ScreensNavigator {
        return ScreensNavigator(activity)
    }

    private fun getMessagesDisplayer(): MessagesDisplayer{
        return MessagesDisplayer(getContext())
    }

    private fun getContext(): Context{
        return activity
    }

}