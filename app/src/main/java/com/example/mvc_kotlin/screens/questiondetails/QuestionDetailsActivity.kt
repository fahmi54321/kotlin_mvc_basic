package com.example.mvc_kotlin.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.screens.common.controller.BackPressedListener
import com.example.mvc_kotlin.screens.common.controller.BaseActivity

class QuestionDetailsActivity : BaseActivity() {
    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        const val ARG_QUESTION_ID = "ARG_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    private lateinit var backPressedListener: BackPressedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_content_frame)

        var questionsListFragment: QuestionDetailsFragment

        if(savedInstanceState == null){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            questionsListFragment = QuestionDetailsFragment.newInstance(getQuestionId())
            fragmentTransaction.add(R.id.frame_content,questionsListFragment)
            fragmentTransaction.commit()
        }else{
            questionsListFragment =
                getSupportFragmentManager().findFragmentById(R.id.frame_content) as QuestionDetailsFragment
        }

        backPressedListener = questionsListFragment
    }

    private fun getQuestionId(): String? {
        return intent.getStringExtra(EXTRA_QUESTION_ID)
    }

    override fun onBackPressed() {
        if(!backPressedListener.onBackPressed()){
            super.onBackPressed()
        }
    }


}