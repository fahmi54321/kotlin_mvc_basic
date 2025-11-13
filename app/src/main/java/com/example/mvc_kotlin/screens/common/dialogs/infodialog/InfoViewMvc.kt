package com.example.mvc_kotlin.screens.common.dialogs.infodialog

import com.example.mvc_kotlin.screens.common.views.ObservableViewMvc

interface InfoViewMvc: ObservableViewMvc<InfoViewMvc.Listener> {
    interface Listener{
        fun onButtonClicked()
    }

    fun setTitle(title: String)
    fun setMessage(message: String)
    fun setButtonPositiveCaption(caption: String)
}