package com.example.mvc_kotlin.screens.common.main

import android.os.Bundle
import android.widget.FrameLayout
import com.example.mvc_kotlin.screens.common.controller.BaseActivity
import com.example.mvc_kotlin.screens.common.fragmentframehelper.FragmentFrameWrapper
import com.example.mvc_kotlin.screens.common.navdrawer.NavDrawerHelper
import com.example.mvc_kotlin.screens.common.navdrawer.NavDrawerViewMvc
import com.example.mvc_kotlin.screens.common.screensnavigator.ScreensNavigator

class MainActivity : BaseActivity(),
    FragmentFrameWrapper,
    NavDrawerViewMvc.Listener,
    NavDrawerHelper {

    private lateinit var screensNavigator: ScreensNavigator

    private lateinit var mViewMvc: NavDrawerViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        screensNavigator = getCompositionRoot().getScreenNavigator()
        mViewMvc = getCompositionRoot().getViewMvcFactory().getNavDrawerViewMvc(null)
        setContentView(mViewMvc.getRootView())

        if(savedInstanceState == null){
            screensNavigator.toQuestionsList()
        }
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        mViewMvc.unregisterListener(this)
    }

    override fun onBackPressed() {
        if(isDrawerOpen()){
            closeDrawer()
        }else{
            super.onBackPressed()
        }
    }

    override fun getFragmentFrame(): FrameLayout {
        return mViewMvc.getFragmentFrame()
    }

    override fun onQuestionListClicked() {
        screensNavigator.toQuestionsList()
    }

    override fun openDrawer() {
        mViewMvc.openDrawer()
    }

    override fun closeDrawer() {
        mViewMvc.closeDrawer()
    }

    override fun isDrawerOpen(): Boolean {
        return mViewMvc.isDrawerOpen()
    }
}