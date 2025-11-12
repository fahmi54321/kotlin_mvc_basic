package com.example.mvc_kotlin.screens.common.dialogs.promptdialog

class PromptDialogEvent(val clickedButton: Button) {
    enum class Button {
        POSITIVE, NEGATIVE
    }
}