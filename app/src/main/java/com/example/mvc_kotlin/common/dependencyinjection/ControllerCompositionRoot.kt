package com.example.mvc_kotlin.common.dependencyinjection

import android.app.Activity
import android.view.LayoutInflater
import com.example.mvc_kotlin.networking.StackoverflowApi
import com.example.mvc_kotlin.screens.common.ViewMvcFactory

class ControllerCompositionRoot(
    val compositionRoot: CompositionRoot,
    val activity: Activity
) {

    private fun getLayoutInflater():LayoutInflater{
        return LayoutInflater.from(activity)
    }

    fun getStackoveflowApi(): StackoverflowApi {
        return compositionRoot.getStackoveflowApi()
    }

    fun getViewMvcFactory(): ViewMvcFactory{
        return ViewMvcFactory(getLayoutInflater())
    }

}