package com.example.mvc_kotlin.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.networking.StackoverflowApi
import com.example.mvc_kotlin.questions.QuestionDetails
import com.example.mvc_kotlin.screens.common.BaseActivity
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class QuestionDetailsActivity : BaseActivity() {
    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var stackoverflowApi: StackoverflowApi
    private lateinit var mViewMvc: QuestionDetailsViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stackoverflowApi = getCompositionRoot().getStackoveflowApi()
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
        mViewMvc.showProgressIndication()
        fetchQuestionDetails()
    }

    private fun fetchQuestionDetails(){
        coroutineScope.launch {
            try {
                val response = stackoverflowApi.questionDetails(getQuestionId())
                if (response.isSuccessful && response.body() != null) {
                    bindQuestionDetails(response.body()?.question)
                } else {
                    networkCallFailed()
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    networkCallFailed()
                }
            }
        }
    }

    private fun getQuestionId(): String{
        return intent.getStringExtra(EXTRA_QUESTION_ID)?:""
    }

    private fun bindQuestionDetails(question: QuestionDetails?){
        mViewMvc.hideProgressIndication()
        if(question != null){
            mViewMvc.bindQuestion(question)
        }
    }

    private fun networkCallFailed(){
        mViewMvc.hideProgressIndication()
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }
}