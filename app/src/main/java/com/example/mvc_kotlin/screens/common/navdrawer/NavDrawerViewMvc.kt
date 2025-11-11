package com.example.mvc_kotlin.screens.common.navdrawer

import android.widget.FrameLayout
import com.example.mvc_kotlin.screens.common.views.ObservableViewMvc

interface NavDrawerViewMvc : ObservableViewMvc<NavDrawerViewMvc.Listener> {

    interface Listener{
        fun onQuestionListClicked()
    }
    fun isDrawerOpen(): Boolean
    fun openDrawer()
    fun closeDrawer()
    fun getFragmentFrame(): FrameLayout
}