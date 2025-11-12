package com.example.mvc_kotlin.screens.common.dialogs

import com.example.mvc_kotlin.common.BaseObservable

class DialogsEventBus: BaseObservable<DialogsEventBus.Listener>() {
    interface Listener{
        fun onDialogEvent(event: Any)
    }

    fun postEvent(event: Any){
        for(listener in getListeners()){
            listener.onDialogEvent(event)
        }
    }
}