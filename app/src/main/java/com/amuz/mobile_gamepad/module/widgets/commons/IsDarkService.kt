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

    fun getDarkInnerShadow(): Color{
        return if(isDark){
            AppColor.DarkMode.darkInnerColor.copy(alpha = 0.58f)
        }else {
            AppColor.LightMode.darkInnerColor
        }
    }

    fun getLightInnerShadow(): Color{
        return if(isDark){
            AppColor.DarkMode.lightInnerColor.copy(alpha = 0.25f)
        }else {
            AppColor.LightMode.lightInnerColor
        }
    }

    fun getDarkOuterShadow(): Color {
        return if (isDark) {
            getDarken(AppColor.DarkMode.buttonColor, 0.3f)
        } else {
            AppColor.LightMode.buttonColor
        }
    }

    fun getLightOuterShadow(): Color {
        return if (isDark) {
            getDarken(AppColor.DarkMode.buttonColor, 0.7f)
        } else {
            Color.White
        }
    }

    fun getCustomBorderColor(color: Color): Color {
        return when (color) {
            AppColor.CustomColor.orange -> Color(0xFF9E3B00)
            AppColor.CustomColor.strawberry -> Color(0xFF8F002C)
            AppColor.CustomColor.lemon -> Color(0xFFC7A000)
            AppColor.CustomColor.magenta -> Color(0xFFFF4EF4)
            AppColor.CustomColor.ultramarineBlue -> Color(0xFF130093)
            AppColor.CustomColor.cyan -> Color(0xFF003776)
            AppColor.CustomColor.violet -> Color(0xFF520081)
            AppColor.CustomColor.lime -> Color(0xFF579C00)
            AppColor.CustomColor.realRed -> Color(0xFFA60000)
            else -> Color.Black
        }
    }

}