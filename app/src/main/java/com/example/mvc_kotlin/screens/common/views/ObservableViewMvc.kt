package com.example.mvc_kotlin.screens.common.views

interface ObservableViewMvc<ListenerType> : ViewMvc {
    fun registerListener(listener: ListenerType)

    fun unregisterListener(listener: ListenerType)
}