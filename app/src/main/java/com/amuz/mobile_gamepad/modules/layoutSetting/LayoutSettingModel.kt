package com.amuz.mobile_gamepad.modules.layoutSetting

import androidx.compose.ui.graphics.Color
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.settings.ColorRepository

class LayoutSettingModel {
    private var colorRepository: ColorRepository

    init {
        colorRepository = when (HomeController.getLayoutState()) {
            "Game Controller" -> ColorRepository.GameController
            "Driving 1" -> ColorRepository.Driving1
            "Driving 2" -> ColorRepository.Driving2
            "Casual" -> ColorRepository.Casual
            else -> ColorRepository.GameController
        }
    }

    private val ltButton: Color? = colorRepository.ltButton
    private val lbButton: Color? = colorRepository.lbButton

    fun getLtButton(): Color? {
        return ltButton
    }

    fun getLbButton(): Color? {
        return lbButton
    }

}