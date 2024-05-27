package com.amuz.mobile_gamepad.modules.widgets.dialogs.displayDialog

import android.content.Context
import com.amuz.mobile_gamepad.constants.Theme
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.settings.AppRepository
import com.amuz.mobile_gamepad.settings.SystemRepository

class DisplayDialogModel(context: Context) {
    private val appRepository = AppRepository()
    private val systemRepository = SystemRepository(context)

    private val modelTheme: Theme? = HomeController.getLayoutTheme()
    private val modelPowerSaving: Int = appRepository.getPowerSaving()
    private val modelTouchEffect: Boolean = appRepository.getTouchEffect()
    private val modelIsVibration: Boolean = HomeController.getIsVibrator() == true
    private var modelBrightness: Int = systemRepository.getBrightness()

    fun getTheme(): Theme? {
        return modelTheme
    }

    fun getPowerSaving(): Int {
        return modelPowerSaving
    }

    fun getTouchEffect(): Boolean {
        return modelTouchEffect
    }

    fun getIsVibration(): Boolean {
        return modelIsVibration
    }

    fun enableIsVibration(){
        appRepository.enableIsVibration()
    }

    fun disableIsVibration(){
        appRepository.disableIsVibration()
    }

    fun setBrightness(value: Int){
        systemRepository.setBrightness(value)
    }

    fun getBrightness(): Int {
        return modelBrightness
    }

}