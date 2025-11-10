package com.example.mvc_kotlin.screens.common.controller

interface BackPressDispatcher {
    fun registenerListener(listener: BackPressedListener)
    fun unregistenerListener(listener: BackPressedListener)
}