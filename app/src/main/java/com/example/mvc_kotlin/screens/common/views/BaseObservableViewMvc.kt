package com.example.mvc_kotlin.screens.common.views

import java.util.Collections

abstract class BaseObservableViewMvc<ListenerType>: BaseViewMvc(), ObservableViewMvc<ListenerType> {
    private val mListeners = HashSet<ListenerType>(1)

    protected val getListeners: Set<ListenerType> = Collections.unmodifiableSet<ListenerType>(mListeners)


    override fun registerListener(listener: ListenerType) {
        mListeners.add(listener)
    }

    override fun unregisterListener(listener: ListenerType) {
        mListeners.remove(listener)
    }
}