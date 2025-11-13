package com.example.mvc_kotlin.screens.common.toolbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.screens.common.views.BaseViewMvc

class ToolbarViewMvc(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?
): BaseViewMvc() {
    interface NavigateUpClickListener{
        fun onNavigateUpClicked()
    }

    interface HamburgerClickListener{
        fun onHamburgerClicked()
    }

    interface LocationRequestListener{
        fun onLocationRequestClicked()
    }

    private var mTxtTitle: TextView
    private var mBtnBack: ImageButton
    private var mBtnHamburger: ImageButton
    private var mBtnLocation: ImageButton
    private lateinit var mNavigateUpClickListener: NavigateUpClickListener
    private lateinit var mHamburgerClickListener: HamburgerClickListener
    private lateinit var mLocationRequestListener: LocationRequestListener

    init {
        setRootView(layoutInflater.inflate(R.layout.layout_toolbar, viewGroup,false))
        mTxtTitle = findViewById(R.id.txt_toolbar_title)
        mBtnBack = findViewById(R.id.btn_back)
        mBtnHamburger = findViewById(R.id.btn_hamburger)
        mBtnLocation = findViewById(R.id.btn_location)
        mBtnBack.setOnClickListener {
            mNavigateUpClickListener.onNavigateUpClicked()
        }
        mBtnHamburger.setOnClickListener {
            mHamburgerClickListener.onHamburgerClicked()
        }
        mBtnLocation.setOnClickListener {
            mLocationRequestListener.onLocationRequestClicked()
        }
    }

    fun setTitle(title: String){
        mTxtTitle.setText(title)
    }
    fun enableUpButtonAndListen(navigateUpClickListener: NavigateUpClickListener){
        mNavigateUpClickListener = navigateUpClickListener
        mBtnBack.visibility = View.VISIBLE
    }

    fun enableHamburgerButtonAndListen(hamburgerClickListener: HamburgerClickListener){
        mHamburgerClickListener = hamburgerClickListener
        mBtnHamburger.visibility = View.VISIBLE
    }

    fun enableLocationRequestButtonAndListen(locationRequestListener: LocationRequestListener){
        mLocationRequestListener = locationRequestListener
        mBtnLocation.visibility = View.VISIBLE
    }

}