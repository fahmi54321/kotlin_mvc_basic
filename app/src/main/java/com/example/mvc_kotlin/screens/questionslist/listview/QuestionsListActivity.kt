package com.example.mvc_kotlin.screens.questionslist.listview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.screens.common.controller.BackPressedListener
import com.example.mvc_kotlin.screens.common.controller.BaseActivity
import com.example.mvc_kotlin.screens.questionslist.listview.QuestionsListFragment

class QuestionsListActivity : BaseActivity() {

    companion object {
        fun startClearTop(context: Context) {
            val intent = Intent(context, QuestionsListActivity::class.java)
            intent.setFlags(intent.getFlags() or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }

    private lateinit var backPressedListener: BackPressedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_content_frame)

        var questionsListFragment: QuestionsListFragment

        if(savedInstanceState == null){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            questionsListFragment = QuestionsListFragment()
            fragmentTransaction.add(R.id.frame_content,questionsListFragment)
            fragmentTransaction.commit()
        }else{
            questionsListFragment =
                getSupportFragmentManager().findFragmentById(R.id.frame_content) as QuestionsListFragment
        }

        backPressedListener = questionsListFragment
    }

    override fun onBackPressed() {
        if(!backPressedListener.onBackPressed()){
            super.onBackPressed()
        }
    }
}