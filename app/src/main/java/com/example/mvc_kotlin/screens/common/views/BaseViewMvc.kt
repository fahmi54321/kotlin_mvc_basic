package com.example.mvc_kotlin.screens.common.views

import android.content.Context
import android.view.View
import androidx.annotation.IdRes

abstract class BaseViewMvc: ViewMvc {
    private lateinit var rootView: View

    protected open fun setRootView(view: View) {
        rootView = view
    }

    override fun getRootView(): View {
        return rootView
    }

    protected fun <T: View?> findViewById(@IdRes id: Int) : T{
        return rootView.findViewById<T>(id)
    }

    protected val context: Context get() = rootView.context
}