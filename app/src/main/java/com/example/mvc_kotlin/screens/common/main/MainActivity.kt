package com.example.mvc_kotlin.screens.common.main

import android.os.Bundle
import android.widget.FrameLayout
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.screens.common.controller.BackPressDispatcher
import com.example.mvc_kotlin.screens.common.controller.BackPressedListener
import com.example.mvc_kotlin.screens.common.controller.BaseActivity
import com.example.mvc_kotlin.screens.common.fragmentframehelper.FragmentFrameWrapper
import com.example.mvc_kotlin.screens.common.screensnavigator.ScreensNavigator

class MainActivity : BaseActivity(), BackPressDispatcher, FragmentFrameWrapper {

    private val backPressedListeners: MutableSet<BackPressedListener> = HashSet()
    private lateinit var screensNavigator: ScreensNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_content_frame)

        screensNavigator = getCompositionRoot().getScreenNavigator()

        if(savedInstanceState == null){
            screensNavigator.toQuestionsList()
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