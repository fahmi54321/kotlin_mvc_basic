package com.example.mvc_kotlin.screens.questionslist

import com.example.mvc_kotlin.questions.FetchQuestionListUseCase
import com.example.mvc_kotlin.questions.Question
import com.example.mvc_kotlin.screens.common.dialogs.DialogsEventBus
import com.example.mvc_kotlin.screens.common.dialogs.DialogsManager
import com.example.mvc_kotlin.screens.common.dialogs.promptdialog.PromptDialogEvent
import com.example.mvc_kotlin.screens.common.toasthelper.ToastHelper
import com.example.mvc_kotlin.screens.common.screensnavigator.ScreensNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import java.io.Serializable

class QuestionsListController(
    val fetchQuestionListUseCase: FetchQuestionListUseCase,
    val toastHelper: ToastHelper,
    val screensNavigator: ScreensNavigator,
    val dialogsManager: DialogsManager,
    val dialogsEventBus: DialogsEventBus
): QuestionsListViewMvc.Listener, FetchQuestionListUseCase.Listener, DialogsEventBus.Listener {

    companion object{
        val DIALOG_ID_NETWORK_ERROR: String = "DIALOG_ID_NETWORK_ERROR"
        val SAVED_STATE_SCREEN_STATE: String = "SAVED_STATE_SCREEN_STATE"

        class SavedState (val mScreenState: ScreenState) : Serializable
    }

    enum class ScreenState{
        IDLE,
        LISTS_SHOWN,
        NETWORK_ERROR
    }

    private var mScreenState: ScreenState = ScreenState.IDLE

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var mViewMvc: QuestionsListViewMvc

    fun onStart(){
        mViewMvc.registerListener(this)
        fetchQuestionListUseCase.registerListener(this)
        dialogsEventBus.registerListener(this)
        if(mScreenState != QuestionsListController.ScreenState.NETWORK_ERROR) {
            fetchQuestions()
        }
    }

    fun onStop(){
        mViewMvc.unregisterListener(this)
        fetchQuestionListUseCase.unregisterListener(this)
        dialogsEventBus.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    fun bindView(mViewMvc: QuestionsListViewMvc){
        this.mViewMvc = mViewMvc
    }

    fun getSavedState(): Companion.SavedState {
        return SavedState(mScreenState)
    }

    fun restoreSavedState(savedState: Companion.SavedState){
        mScreenState = savedState.mScreenState
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

    override fun onQuestionFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR
        dialogsManager.showUseCaseErrorDialog(DIALOG_ID_NETWORK_ERROR)
    }

    override fun onQuestionFetched(questions: List<Question>) {
        mScreenState = ScreenState.LISTS_SHOWN
        mViewMvc.bindQuestions(questions)
        mViewMvc.hideProgressIndication()
    }

    override fun onDialogEvent(event: Any) {
        if(event is PromptDialogEvent){
            when(event.clickedButton){
                PromptDialogEvent.Button.POSITIVE ->{
                    mScreenState = ScreenState.IDLE
                    coroutineScope.launch {
                        fetchQuestionListUseCase.fetchQuestionsAndNotify()
                    }
                }
                PromptDialogEvent.Button.NEGATIVE ->{
                    mScreenState = ScreenState.IDLE
                }
            }
        }
    }
}