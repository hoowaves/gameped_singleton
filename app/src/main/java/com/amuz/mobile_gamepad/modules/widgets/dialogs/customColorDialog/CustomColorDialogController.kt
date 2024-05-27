package com.amuz.mobile_gamepad.modules.widgets.dialogs.customColorDialog


import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


class CustomColorDialogController(layoutState: String) {
    private val customColorDialogModel = CustomColorDialogModel(layoutState)

    private val _ltButton: MutableLiveData<Color?> =
        MutableLiveData(customColorDialogModel.getLtButton())
    val ltButton: LiveData<Color?> get() = _ltButton

    private val _lbButton: MutableLiveData<Color?> =
        MutableLiveData(customColorDialogModel.getLbButton())
    val lbButton: LiveData<Color?> get() = _lbButton

    fun getModelLtButton(): Color? {
        return customColorDialogModel.getLtButton()
    }

    fun getModelLbButton(): Color? {
        return customColorDialogModel.getLbButton()
    }

}