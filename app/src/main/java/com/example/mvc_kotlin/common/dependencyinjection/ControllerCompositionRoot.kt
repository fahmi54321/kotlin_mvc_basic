package com.example.mvc_kotlin.common.dependencyinjection

import android.app.Activity
import android.view.LayoutInflater
import com.example.mvc_kotlin.networking.StackoverflowApi

class ControllerCompositionRoot(
    val compositionRoot: CompositionRoot,
    val activity: Activity
) {

    fun getStackoveflowApi(): StackoverflowApi {
        return compositionRoot.getStackoveflowApi()
    }

    fun getLayoutInflater():LayoutInflater{
        return LayoutInflater.from(activity)
    }

}