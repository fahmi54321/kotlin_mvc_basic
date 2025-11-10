package com.example.mvc_kotlin.screens.common.fragmentframehelper

import androidx.fragment.app.Fragment

interface HierarchicalFragment {
    fun getHierarchicalParentFragment(): Fragment?
}