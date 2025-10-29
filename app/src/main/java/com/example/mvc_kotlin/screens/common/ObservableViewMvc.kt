package com.example.mvc_kotlin.screens.common

import com.example.mvc_kotlin.screens.QuestionsListViewMvc.Listener

interface ObservableViewMvc<ListenerType> : ViewMvc {
    fun registerListener(listener: ListenerType)

    fun unregisterListener(listener: ListenerType)
}