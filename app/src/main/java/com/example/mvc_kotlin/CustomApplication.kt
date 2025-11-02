package com.example.mvc_kotlin

import android.app.Application
import com.example.mvc_kotlin.common.dependencyinjection.CompositionRoot

class CustomApplication: Application() {

    private lateinit var compositionRoot: CompositionRoot

    override fun onCreate() {
        super.onCreate()
        compositionRoot = CompositionRoot()
    }

    fun getCompositionRoot(): CompositionRoot{
        return compositionRoot
    }
}