package com.example.mvc_kotlin.screens.common.main

import android.os.Bundle
import android.widget.FrameLayout
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.screens.common.controller.BackPressDispatcher
import com.example.mvc_kotlin.screens.common.controller.BackPressedListener
import com.example.mvc_kotlin.screens.common.controller.BaseActivity
import com.example.mvc_kotlin.screens.common.controller.FragmentFrameWrapper
import com.example.mvc_kotlin.screens.questionslist.listview.QuestionsListFragment

class MainActivity : BaseActivity(), BackPressDispatcher, FragmentFrameWrapper {

    private val backPressedListeners: MutableSet<BackPressedListener> = HashSet<BackPressedListener>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_content_frame)

        var questionsListFragment: QuestionsListFragment

        if(savedInstanceState == null){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            questionsListFragment = QuestionsListFragment()
            fragmentTransaction.add(R.id.frame_content,questionsListFragment)
            fragmentTransaction.commit()
        }
    }

    override fun onBackPressed() {
        var isBackPressConsumedByAndListener: Boolean = false
        for(listener in backPressedListeners){
            if(listener.onBackPressed()){
                isBackPressConsumedByAndListener = true
            }
        }

        if(!isBackPressConsumedByAndListener){
            super.onBackPressed()
        }
    }

    override fun registenerListener(listener: BackPressedListener) {
        backPressedListeners.add(listener)
    }

    override fun unregistenerListener(listener: BackPressedListener) {
        backPressedListeners.remove(listener)
    }

    override fun getFragmentFrame(): FrameLayout {
        return findViewById(R.id.frame_content)
    }
}