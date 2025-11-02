package com.example.mvc_kotlin.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.mvc_kotlin.CustomApplication
import com.example.mvc_kotlin.common.dependencyinjection.ControllerCompositionRoot

open class BaseActivity: AppCompatActivity() {

    private var controllerCompositionRoot: ControllerCompositionRoot? = null
    protected fun getCompositionRoot(): ControllerCompositionRoot{
        if(controllerCompositionRoot == null){
            controllerCompositionRoot = ControllerCompositionRoot(
                ((application) as CustomApplication).getCompositionRoot(),
                this
            )
        }
        return controllerCompositionRoot!!
    }
}