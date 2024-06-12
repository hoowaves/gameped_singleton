package com.amuz.mobile_gamepad.settings.app

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.constants.AppColor
import kotlin.math.max

object AppSettingModel {
    val id: MutableLiveData<Int> = MutableLiveData()
    val layout: MutableLiveData<Int> = MutableLiveData()
    var isDark: MutableLiveData<Boolean> = MutableLiveData()
    var touchEffect: MutableLiveData<Boolean> = MutableLiveData()
    var powerSaving: MutableLiveData<Int> = MutableLiveData()
    var isVibration: MutableLiveData<Boolean> = MutableLiveData()

    fun init(appSettingEntity: AppSettingEntity?) {
        if (appSettingEntity != null) {
            id.value = appSettingEntity.id
            layout.value = appSettingEntity.layout
            isDark.value = appSettingEntity.isDark
            touchEffect.value = appSettingEntity.touchEffect
            powerSaving.value = appSettingEntity.powerSaving
            isVibration.value = appSettingEntity.isVibration
        }
    }

    fun getBackgroundColor(): Color {
        return if (isDark.value == true) {
            AppColor.DarkMode.backgroundColor
        } else {
            AppColor.LightMode.backgroundColor
        }
    }

    fun getButtonColor(): Color {
        return if (isDark.value == true) {
            AppColor.DarkMode.buttonColor
        } else {
            AppColor.LightMode.buttonColor
        }
    }

    fun getBorderColor(): Color {
        return if (isDark.value == true) {
            AppColor.DarkMode.borderColor
        } else {
            AppColor.LightMode.borderColor
        }
    }

    fun getTextColor(): Color {
        return if (isDark.value == true) {
            AppColor.DarkMode.textColor
        } else {
            AppColor.LightMode.textColor
        }
    }


    fun darken(color: Color, factor: Float): Color {
        val argb = color.toArgb()
        val red = (argb shr 16) and 0xFF
        val green = (argb shr 8) and 0xFF
        val blue = argb and 0xFF

        val newRed = max((red * factor).toInt(), 0)
        val newGreen = max((green * factor).toInt(), 0)
        val newBlue = max((blue * factor).toInt(), 0)

        val darkerColor = android.graphics.Color.argb(
            (argb shr 24) and 0xFF,
            newRed,
            newGreen,
            newBlue
        )
        return Color(darkerColor)
    }

}