package com.example.mvc_kotlin.screens.common.dialogs.infodialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.screens.common.dialogs.BaseDialog

open class InfoDialog : BaseDialog() {
    private lateinit var mTxtTitle: TextView
    private lateinit var mTxtMessage: TextView
    private lateinit var mBtnPositive: AppCompatButton

    public override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_info)

        mTxtTitle = dialog.findViewById(R.id.txt_title)
        mTxtMessage = dialog.findViewById(R.id.txt_message)
        mBtnPositive = dialog.findViewById(R.id.btn_positive)

        mTxtTitle.setText(getArguments()?.getString(ARG_TITLE))
        mTxtMessage.setText(getArguments()?.getString(ARG_MESSAGE))
        mBtnPositive.setText(getArguments()?.getString(ARG_BUTTON_CAPTION))

        mBtnPositive.setOnClickListener { v: View? -> onButtonClicked() }

        return dialog
    }

    protected fun onButtonClicked() {
        dismiss()
    }

    companion object {
        protected const val ARG_TITLE: String = "ARG_TITLE"
        protected const val ARG_MESSAGE: String = "ARG_MESSAGE"
        protected const val ARG_BUTTON_CAPTION: String = "ARG_BUTTON_CAPTION"

        fun newInfoDialog(title: String?, message: String?, buttonCaption: String?): InfoDialog {
            val infoDialog = InfoDialog()
            val args = Bundle(3)
            args.putString(ARG_TITLE, title)
            args.putString(ARG_MESSAGE, message)
            args.putString(ARG_BUTTON_CAPTION, buttonCaption)
            infoDialog.setArguments(args)
            return infoDialog
        }
    }
}