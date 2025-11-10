package com.example.mvc_kotlin.common.dependencyinjection

import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.mvc_kotlin.networking.StackoverflowApi
import com.example.mvc_kotlin.questions.FetchQuestionDetailsUseCase
import com.example.mvc_kotlin.questions.FetchQuestionListUseCase
import com.example.mvc_kotlin.screens.questionslist.QuestionsListController
import com.example.mvc_kotlin.screens.common.toasthelper.ToastHelper
import com.example.mvc_kotlin.screens.common.screensnavigator.ScreensNavigator
import com.example.mvc_kotlin.screens.common.ViewMvcFactory
import com.example.mvc_kotlin.screens.common.controller.BackPressDispatcher
import com.example.mvc_kotlin.screens.common.controller.FragmentFrameWrapper
import com.example.mvc_kotlin.screens.questiondetails.QuestionDetailsController

class ControllerCompositionRoot(
    val compositionRoot: CompositionRoot,
    val activity: FragmentActivity
) {


    private fun getFragmentManager(): FragmentManager{
        return activity.supportFragmentManager
    }

    private fun getFragmentFrameWrapper(): FragmentFrameWrapper {
        return activity as FragmentFrameWrapper
    }

    private fun getBackPressDispatcher(): BackPressDispatcher{
        return activity as BackPressDispatcher
    }

    private fun getScreenNavigator(): ScreensNavigator {
        return ScreensNavigator(
            getFragmentManager(),
            getFragmentFrameWrapper()
        )
    }

    private fun getToastHelper(): ToastHelper{
        return ToastHelper(getContext())
    }

    private fun getContext(): Context{
        return activity
    }


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
            getToastHelper(),
            getScreenNavigator(),
            getBackPressDispatcher()
        )
    }

    fun getQuestionDetailsController(): QuestionDetailsController {
        return QuestionDetailsController(
            getFetchQuestionDetailsUseCase(),
            getToastHelper(),
            getScreenNavigator(),
            getBackPressDispatcher()
        )
    }

}