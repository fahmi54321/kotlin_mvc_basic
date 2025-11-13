package com.example.mvc_kotlin.screens.common.dialogs.promptdialog

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.screens.common.views.BaseObservableViewMvc

class PromptMvcImpl(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?,
) : BaseObservableViewMvc<PromptViewMvc.Listener>(), PromptViewMvc {

    private lateinit var mTxtTitle: TextView
    private lateinit var mTxtMessage: TextView
    private lateinit var mBtnPositive: AppCompatButton
    private lateinit var mBtnNegative: AppCompatButton


    init {
        setRootView(layoutInflater.inflate(R.layout.layout_questions_list,viewGroup,false))

        mTxtTitle = findViewById(R.id.txt_title)
        mTxtMessage = findViewById(R.id.txt_message)
        mBtnPositive = findViewById(R.id.btn_positive)
        mBtnNegative = findViewById(R.id.btn_negative)


        mBtnPositive.setOnClickListener {
            for(listener in getListeners){
                listener.onPositiveButtonClicked()
            }
        }
        mBtnNegative.setOnClickListener {
            for(listener in getListeners){
                listener.onNegativeButtonClicked()
            }
        }
    }

    override fun setTitle(title: String) {
        mTxtTitle.setText(title)

    }

    override fun setMessage(message: String) {
        mTxtMessage.setText(message)

    }

    override fun setPositiveButtonCaption(caption: String) {
        mBtnPositive.setText(caption)

    }

    override fun setNegativeButtonCaption(caption: String) {
        mBtnNegative.setText(caption)

    }

}