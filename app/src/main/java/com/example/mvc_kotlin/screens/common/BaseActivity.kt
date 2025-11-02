package com.example.mvc_kotlin.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.example.mvc_kotlin.CustomApplication
import com.example.mvc_kotlin.common.CompositionRoot

open class BaseActivity: AppCompatActivity() {
    protected fun getCompositionRoot(): CompositionRoot{
        return (application as CustomApplication).getCompositionRoot()
    }
}