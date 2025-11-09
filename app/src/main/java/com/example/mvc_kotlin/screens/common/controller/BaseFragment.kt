package com.example.mvc_kotlin.screens.common.controller

import androidx.fragment.app.Fragment
import com.example.mvc_kotlin.common.CustomApplication
import com.example.mvc_kotlin.common.dependencyinjection.ControllerCompositionRoot

open class BaseFragment: Fragment() {

    private var controllerCompositionRoot: ControllerCompositionRoot? = null
    protected fun getCompositionRoot(): ControllerCompositionRoot {
        if (controllerCompositionRoot == null) {
            controllerCompositionRoot = ControllerCompositionRoot(
                (requireActivity().getApplication() as CustomApplication).getCompositionRoot(),
                requireActivity()
            )
        }
        return controllerCompositionRoot!!
    }
}