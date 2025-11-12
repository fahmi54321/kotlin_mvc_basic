package com.example.mvc_kotlin.screens.questiondetails

import com.example.mvc_kotlin.questions.FetchQuestionDetailsUseCase
import com.example.mvc_kotlin.questions.QuestionDetails
import com.example.mvc_kotlin.screens.common.dialogs.DialogsEventBus
import com.example.mvc_kotlin.screens.common.dialogs.DialogsManager
import com.example.mvc_kotlin.screens.common.dialogs.promptdialog.PromptDialogEvent
import com.example.mvc_kotlin.screens.common.screensnavigator.ScreensNavigator
import com.example.mvc_kotlin.screens.common.toasthelper.ToastHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import java.io.Serializable

class QuestionDetailsController(
    val fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase,
    val toastHelper: ToastHelper,
    val screensNavigator: ScreensNavigator,
    val dialogsManager: DialogsManager,
    val dialogsEventBus: DialogsEventBus
): FetchQuestionDetailsUseCase.Listener,
    QuestionDetailsViewMvc.Listener, DialogsEventBus.Listener {

    companion object{
        val DIALOG_ID_NETWORK_ERROR: String = "DIALOG_ID_NETWORK_ERROR"
        val SAVED_STATE_SCREEN_STATE: String = "SAVED_STATE_SCREEN_STATE"

        class SavedState (val mScreenState: ScreenState) : Serializable
    }

    enum class ScreenState{
        IDLE,
        DETAILS_SHOWN,
        NETWORK_ERROR
    }

    private var mScreenState: ScreenState = ScreenState.IDLE
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var mViewMvc: QuestionDetailsViewMvc

    private var questionId: String = ""

    fun setQuestionId(id: String){
        questionId = id
    }

    fun onStart(){
        mViewMvc.registerListener(this)
        fetchQuestionDetailsUseCase.registerListener(this)
        dialogsEventBus.registerListener(this)
        if(mScreenState != ScreenState.NETWORK_ERROR){
            fetchQuestionDetails(questionId)
        }
    }

    fun onStop(){
        mViewMvc.unregisterListener(this)
        fetchQuestionDetailsUseCase.unregisterListener(this)
        dialogsEventBus.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    fun bindView(mViewMvc: QuestionDetailsViewMvc){
        this.mViewMvc = mViewMvc
    }

    fun getSavedState(): SavedState{
        return SavedState(mScreenState)
    }

    fun restoreSavedState(savedState: SavedState){
        mScreenState = savedState.mScreenState
    }

    private fun fetchQuestionDetails(questionId: String){
        mViewMvc.showProgressIndication()

        coroutineScope.launch {
            fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId)
        }
    }

    override fun onQuestionDetailsFetched(question: QuestionDetails) {
        mScreenState = ScreenState.DETAILS_SHOWN
        mViewMvc.hideProgressIndication()
        mViewMvc.bindQuestion(question)
    }

    override fun onQuestionDetailsFetchFailed() {
        mScreenState = ScreenState.NETWORK_ERROR
        mViewMvc.hideProgressIndication()
        dialogsManager.showUseCaseErrorDialog(DIALOG_ID_NETWORK_ERROR)
    }

    override fun onNavigateUpClicked() {
        screensNavigator.onBackPressed()
    }

    override fun onDialogEvent(event: Any) {
        if (event is PromptDialogEvent) {
            when (event.clickedButton) {
                PromptDialogEvent.Button.POSITIVE -> {
                    mScreenState = ScreenState.IDLE
                    coroutineScope.launch {
                        fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(questionId)
                    }
                }
                PromptDialogEvent.Button.NEGATIVE -> {
                    mScreenState = ScreenState.IDLE
                }
            }
        }
    }
}