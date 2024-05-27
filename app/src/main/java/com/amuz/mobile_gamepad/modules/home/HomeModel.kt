package com.amuz.mobile_gamepad.modules.home

import com.amuz.mobile_gamepad.constants.Theme
import com.amuz.mobile_gamepad.settings.AppRepository

class HomeModel {
    private val appRepository = AppRepository()

    private val modelState: String = appRepository.getState()
    private val modelTheme: Theme = appRepository.getTheme()
    private val modelIsVibrator: Boolean = appRepository.getIsVibration()

    fun getState(): String {
        return modelState
    }

    fun getTheme(): Theme {
        return modelTheme
    }

    fun getIsVibrator(): Boolean{
        return modelIsVibrator
    }
}