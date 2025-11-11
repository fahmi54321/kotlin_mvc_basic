package com.example.mvc_kotlin.screens.common.dialogs

import androidx.fragment.app.DialogFragment
import com.example.mvc_kotlin.common.CustomApplication
import com.example.mvc_kotlin.common.dependencyinjection.ControllerCompositionRoot

open class BaseDialog: DialogFragment() {
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