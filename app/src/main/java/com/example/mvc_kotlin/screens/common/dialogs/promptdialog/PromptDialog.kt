package com.example.mvc_kotlin.screens.common.dialogs.promptdialog

import android.app.Dialog
import android.os.Bundle
import com.example.mvc_kotlin.screens.common.dialogs.BaseDialog
import com.example.mvc_kotlin.screens.common.dialogs.DialogsEventBus

open class PromptDialog : BaseDialog(), PromptViewMvc.Listener {
    private lateinit var mDialogsEventBus: DialogsEventBus
    private lateinit var mViewMvc: PromptViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDialogsEventBus = getCompositionRoot().getDialogsEventBus()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        mViewMvc = getCompositionRoot().getViewMvcFactory().getPromptViewMvc(null)
        dialog.setContentView(mViewMvc.getRootView())

        mViewMvc.setTitle(getArguments()?.getString(ARG_TITLE)?:"")
        mViewMvc.setMessage(getArguments()?.getString(ARG_MESSAGE)?:"")
        mViewMvc.setPositiveButtonCaption(getArguments()?.getString(ARG_POSITIVE_BUTTON_CAPTION)?:"")
        mViewMvc.setNegativeButtonCaption(getArguments()?.getString(ARG_NEGATIVE_BUTTON_CAPTION)?:"")

        return dialog
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
    }

    override fun onStop() {
        super.onStop()
        mViewMvc.unregisterListener(this)
    }

    override fun onPositiveButtonClicked() {
        dismiss()
        mDialogsEventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.POSITIVE))
    }

    override fun onNegativeButtonClicked() {
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