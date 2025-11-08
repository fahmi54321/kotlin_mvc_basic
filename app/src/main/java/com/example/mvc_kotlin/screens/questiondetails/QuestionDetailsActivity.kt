package com.example.mvc_kotlin.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.questions.FetchQuestionDetailsUseCase
import com.example.mvc_kotlin.questions.QuestionDetails
import com.example.mvc_kotlin.screens.common.controller.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class QuestionDetailsActivity : BaseActivity(), FetchQuestionDetailsUseCase.Listener,
    QuestionDetailsViewMvc.Listener {
    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var mViewMvc: QuestionDetailsViewMvc

    private lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchQuestionDetailsUseCase = getCompositionRoot().getFetchQuestionDetailsUseCase()
        mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null)

        enableEdgeToEdge()
        setContentView(mViewMvc.getRootView())
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
        fetchQuestionDetailsUseCase.registerListener(this)
        mViewMvc.showProgressIndication()
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        mViewMvc.unregisterListener(this)
        fetchQuestionDetailsUseCase.unregisterListener(this)
    }

    private fun fetchQuestionDetails(){
        coroutineScope.launch {
            fetchQuestionDetailsUseCase.fetchQuestionDetailsAndNotify(getQuestionId())
        }
    }

    private fun getQuestionId(): String{
        return intent.getStringExtra(EXTRA_QUESTION_ID)?:""
    }


    override fun onQuestionDetailsFetched(question: QuestionDetails) {
        mViewMvc.hideProgressIndication()
        mViewMvc.bindQuestion(question)
    }

    override fun onQuestionDetailsFetchFailed() {
        mViewMvc.hideProgressIndication()
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun onNavigateUpClicked() {
        onBackPressed()
    }
}