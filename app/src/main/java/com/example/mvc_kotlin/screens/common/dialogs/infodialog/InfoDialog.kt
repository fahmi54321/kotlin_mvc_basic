package com.example.mvc_kotlin.screens.common.dialogs.infodialog

import android.app.Dialog
import android.os.Bundle
import com.example.mvc_kotlin.screens.common.dialogs.BaseDialog

open class InfoDialog : BaseDialog(), InfoViewMvc.Listener {

    private lateinit var mViewMvc: InfoViewMvc

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        mViewMvc = getCompositionRoot().getViewMvcFactory().getInfoViewMvc(null)
        dialog.setContentView(mViewMvc.getRootView())

        mViewMvc.setTitle(getArguments()?.getString(ARG_TITLE)?:"")
        mViewMvc.setMessage(getArguments()?.getString(ARG_MESSAGE)?:"")
        mViewMvc.setButtonPositiveCaption(getArguments()?.getString(ARG_BUTTON_CAPTION)?:"")

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

    override fun onButtonClicked() {
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