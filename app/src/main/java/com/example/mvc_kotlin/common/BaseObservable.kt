package com.example.mvc_kotlin.common

import java.util.Collections
import java.util.concurrent.ConcurrentHashMap

abstract class BaseObservable<LISTENER_CLASS> {
    private val mListeners: MutableSet<LISTENER_CLASS> =
        Collections.newSetFromMap<LISTENER_CLASS>(
            ConcurrentHashMap<LISTENER_CLASS, Boolean>(1)
        )

    fun registerListener(listener: LISTENER_CLASS) {
        mListeners.add(listener)
    }

    fun unregisterListener(listener: LISTENER_CLASS) {
        mListeners.remove(listener)
    }

    protected fun getListeners(): MutableSet<LISTENER_CLASS> {
        return Collections.unmodifiableSet(mListeners)
    }
}