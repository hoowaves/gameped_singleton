package com.amuz.mobile_gamepad.module.widgets.commons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.amuz.mobile_gamepad.constants.AppColor
import kotlin.math.max

class IsDarkService(private val isDark: Boolean) {
    fun getBackgroundColor(): Color {
        return if (isDark) {
            AppColor.DarkMode.backgroundColor
        } else {
            AppColor.LightMode.backgroundColor
        }
    }

    fun getButtonColor(): Color {
        return if (isDark) {
            AppColor.DarkMode.buttonColor
        } else {
            AppColor.LightMode.buttonColor
        }
    }

    fun getBorderColor(): Color {
        return if (isDark) {
            AppColor.DarkMode.borderColor
        } else {
            AppColor.LightMode.borderColor
        }
    }

    fun getTextColor(): Color {
        return if (isDark) {
            AppColor.DarkMode.textColor
        } else {
            AppColor.LightMode.textColor
        }
    }


    fun getDarken(color: Color, factor: Float): Color {
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

    fun getButtonTextColor(color: Color): Color {
        return when (color) {
            AppColor.CustomColor.orange -> Color(0xFF232323)
            AppColor.CustomColor.strawberry -> Color.White
            AppColor.CustomColor.lemon -> Color(0xFF232323)
            AppColor.CustomColor.magenta -> Color(0xFF232323)
            AppColor.CustomColor.ultramarineBlue -> Color.White
            AppColor.CustomColor.cyan -> Color.White
            AppColor.CustomColor.violet -> Color.White
            AppColor.CustomColor.lime -> Color(0xFF232323)
            AppColor.CustomColor.realRed -> Color.White
            else -> Color.Black
        }
    }

    fun getDarkShadow(): Color {
        return if (isDark) {
            getDarken(AppColor.DarkMode.buttonColor, 0.3f)
        } else {
            AppColor.LightMode.buttonColor
        }
    }

    fun getLightShadow(): Color {
        return if (isDark) {
            getDarken(AppColor.DarkMode.buttonColor, 0.7f)
        } else {
            Color.White
        }
    }
}