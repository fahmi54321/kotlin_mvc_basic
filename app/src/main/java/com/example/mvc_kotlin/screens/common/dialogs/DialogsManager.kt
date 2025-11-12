package com.example.mvc_kotlin.screens.common.dialogs

import androidx.fragment.app.FragmentManager
import com.example.mvc_kotlin.screens.common.dialogs.promptdialog.PromptDialog

class DialogsManager(
    val fragmentManager: FragmentManager
) {
    fun showUseCaseErrorDialog(tag: String?){
//        val infoDialog = InfoDialog.newInfoDialog(
//            "Opps",
//            "Error",
//            "Ok"
//        )
//
//        infoDialog.show(fragmentManager,tag)

        val promptDialog = PromptDialog.newInfoDialog(
            "Opps",
            "Errpr",
            "Retry",
            "Close"
        )
        promptDialog.show(fragmentManager, tag)
    }

    fun getShownDialogTag(): String? {
        for(fragment in fragmentManager.fragments){
            if(fragment is BaseDialog){
                return fragment.tag
            }
        }
        return null
    }
}