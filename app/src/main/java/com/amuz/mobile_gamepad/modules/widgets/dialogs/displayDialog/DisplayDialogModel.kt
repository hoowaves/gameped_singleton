package com.amuz.mobile_gamepad.modules.widgets.dialogs.displayDialog

import android.content.Context
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.settings.SystemRepository
import com.amuz.mobile_gamepad.settings.app.AppSettingEntity

class DisplayDialogModel(context: Context) {
    private val systemRepository = SystemRepository(context)

    private val isDark: Boolean? = HomeController.appIsDark.value
    private val powerSaving: Int? = HomeController.appPowerSaving.value
    private val touchEffect: Boolean? = HomeController.appTouchEffect.value
    private val isVibration: Boolean? = HomeController.appIsVibration.value
    private var brightness: Int = systemRepository.getBrightness()

    fun getIsDark(): Boolean? {
        return this.isDark
    }

    fun getPowerSaving(): Int? {
        return this.powerSaving
    }

    fun getTouchEffect(): Boolean? {
        return this.touchEffect
    }

    fun getIsVibration(): Boolean? {
        return this.isVibration
    }

    fun setBrightness(value: Int){
        systemRepository.setBrightness(value)
    }

    fun getBrightness(): Int {
        return this.brightness
    }

}