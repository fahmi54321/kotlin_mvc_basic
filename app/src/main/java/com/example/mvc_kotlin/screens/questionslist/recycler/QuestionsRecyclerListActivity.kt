package com.example.mvc_kotlin.screens.questionslist.recycler

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.screens.common.controller.BackPressedListener
import com.example.mvc_kotlin.screens.common.controller.BaseActivity

class QuestionsRecyclerListActivity : BaseActivity() {

    companion object {
        fun startClearTop(context: Context) {
            val intent = Intent(context, QuestionsRecyclerListActivity::class.java)
            intent.setFlags(intent.getFlags() or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }

    private lateinit var backPressedListener: BackPressedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_content_frame)

        var questionsListFragment: QuestionsRecyclerListFragment

        if(savedInstanceState == null){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            questionsListFragment = QuestionsRecyclerListFragment()
            fragmentTransaction.add(R.id.frame_content,questionsListFragment)
            fragmentTransaction.commit()
        }else{
            questionsListFragment =
                getSupportFragmentManager().findFragmentById(R.id.frame_content) as QuestionsRecyclerListFragment
        }

        backPressedListener = questionsListFragment
    }

    override fun onBackPressed() {
        if(!backPressedListener.onBackPressed()){
            super.onBackPressed()
        }
    }


}