package com.example.mvc_kotlin.screens.common.dialogs

import androidx.fragment.app.FragmentManager
import com.example.mvc_kotlin.screens.common.dialogs.infodialog.InfoDialog

class DialogsManager(
    val fragmentManager: FragmentManager
) {
    fun showUseCaseErrorDialog(tag: String){
        val infoDialog = InfoDialog.newInfoDialog(
            "Opps",
            "Error",
            "Ok"
        )

        infoDialog.show(fragmentManager,tag)
    }
}