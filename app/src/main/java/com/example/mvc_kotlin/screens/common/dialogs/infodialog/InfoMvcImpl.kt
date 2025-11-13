package com.example.mvc_kotlin.screens.common.dialogs.infodialog

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.screens.common.views.BaseObservableViewMvc

class InfoMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?,
) : BaseObservableViewMvc<InfoViewMvc.Listener>(), InfoViewMvc {

    private var mTxtTitle: TextView
    private var mTxtMessage: TextView
    private var mBtnPositive: AppCompatButton


    init {
        setRootView(layoutInflater.inflate(R.layout.layout_questions_list,viewGroup,false))

        mTxtTitle = findViewById(R.id.txt_title)
        mTxtMessage = findViewById(R.id.txt_message)
        mBtnPositive = findViewById(R.id.btn_positive)


        mBtnPositive.setOnClickListener {
            for (listener in getListeners){
                listener.onButtonClicked()
            }
        }
    }

    override fun setTitle(title: String) {
        mTxtTitle.setText(title)

    }

    override fun setMessage(message: String) {
        mTxtMessage.setText(message)

    }

    override fun setButtonPositiveCaption(caption: String) {
        mBtnPositive.setText(caption)

    }
}