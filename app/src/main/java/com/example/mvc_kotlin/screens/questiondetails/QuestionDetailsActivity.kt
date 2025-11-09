package com.example.mvc_kotlin.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.screens.common.controller.BaseActivity

class QuestionDetailsActivity : BaseActivity() {
    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    private lateinit var questionDetailsController: QuestionDetailsController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mViewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null)
        questionDetailsController = getCompositionRoot().getQuestionDetailsController()
        questionDetailsController.bindView(mViewMvc)

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
        questionDetailsController.onStart(getQuestionId())

    }

    override fun onStop() {
        super.onStop()
        questionDetailsController.onStop()
    }

    override fun onBackPressed() {
        if(!questionDetailsController.onBackPressed()){
            super.onBackPressed()
        }
    }

    private fun getQuestionId(): String{
        return intent.getStringExtra(EXTRA_QUESTION_ID)?:""
    }
}