package com.example.mvc_kotlin.screens.common.dialogs.promptdialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.mvc_kotlin.R
import com.example.mvc_kotlin.screens.common.dialogs.BaseDialog
import com.example.mvc_kotlin.screens.common.dialogs.DialogsEventBus

open class PromptDialog : BaseDialog() {
    private lateinit var mTxtTitle: TextView
    private lateinit var mTxtMessage: TextView
    private lateinit var mBtnPositive: AppCompatButton
    private lateinit var mBtnNegative: AppCompatButton
    private lateinit var mDialogsEventBus: DialogsEventBus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDialogsEventBus = getCompositionRoot().getDialogsEventBus()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_prompt)

        mTxtTitle = dialog.findViewById(R.id.txt_title)
        mTxtMessage = dialog.findViewById(R.id.txt_message)
        mBtnPositive = dialog.findViewById(R.id.btn_positive)
        mBtnNegative = dialog.findViewById(R.id.btn_negative)

        mTxtTitle.setText(getArguments()?.getString(ARG_TITLE))
        mTxtMessage.setText(getArguments()?.getString(ARG_MESSAGE))
        mBtnPositive.setText(getArguments()?.getString(ARG_POSITIVE_BUTTON_CAPTION))
        mBtnNegative.setText(getArguments()?.getString(ARG_NEGATIVE_BUTTON_CAPTION))

        mBtnPositive.setOnClickListener { onPositiveButtonClicked() }
        mBtnNegative.setOnClickListener { onNegativeButtonClicked() }

        return dialog
    }

    protected fun onPositiveButtonClicked() {
        dismiss()
        mDialogsEventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.POSITIVE))
    }

    protected fun onNegativeButtonClicked() {
        dismiss()
        mDialogsEventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.NEGATIVE))
    }

    companion object {
        protected const val ARG_TITLE: String = "ARG_TITLE"
        protected const val ARG_MESSAGE: String = "ARG_MESSAGE"
        protected const val ARG_POSITIVE_BUTTON_CAPTION: String = "ARG_POSITIVE_BUTTON_CAPTION"
        protected const val ARG_NEGATIVE_BUTTON_CAPTION: String = "ARG_NEGATIVE_BUTTON_CAPTION"

        fun newInfoDialog(title: String?, message: String?, positiveButtonCaption: String?, negativeButtonCaption: String?): PromptDialog {
            val promptDialog = PromptDialog()
            val args = Bundle(3)
            args.putString(ARG_TITLE, title)
            args.putString(ARG_MESSAGE, message)
            args.putString(ARG_POSITIVE_BUTTON_CAPTION, positiveButtonCaption)
            args.putString(ARG_NEGATIVE_BUTTON_CAPTION, negativeButtonCaption)
            promptDialog.setArguments(args)
            return promptDialog
        }
    }
}