package com.amuz.mobile_gamepad.settings

import com.amuz.mobile_gamepad.constants.Theme

class AppRepository {
    // Storage 연동 작업 필요
    private val appState: String = "Game Controller"
    private val appTheme: Theme = Theme.DARK
    private val appTouchEffect: Boolean = true
    private val appPowerSaving: Int = 0
    private var appIsVibration: Boolean = true

    fun getState(): String {
        return appState
    }

    fun getTheme(): Theme {
        return appTheme
    }

    fun getTouchEffect(): Boolean {
        return appTouchEffect
    }

    fun getPowerSaving(): Int {
        return appPowerSaving
    }

    fun getIsVibration(): Boolean {
        return appIsVibration
    }

    fun enableIsVibration(){
        appIsVibration = true
    }

    fun disableIsVibration(){
        appIsVibration = false
    }
}