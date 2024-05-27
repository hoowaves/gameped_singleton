package com.amuz.mobile_gamepad.settings

import androidx.compose.ui.graphics.Color
import com.amuz.mobile_gamepad.constants.AppColor

sealed class ColorRepository {
    abstract val ltButton: Color?
    abstract val lbButton: Color?

    data object GameController : ColorRepository() {
        override val ltButton: Color? = AppColor.CustomColor.green
        override val lbButton: Color? = null
    }

    data object Driving1 : ColorRepository() {
        override val ltButton: Color? = AppColor.CustomColor.green
        override val lbButton: Color? = null
    }

    data object Driving2 : ColorRepository() {
        override val ltButton: Color? = AppColor.CustomColor.green
        override val lbButton: Color? = null
    }

    data object Casual : ColorRepository() {
        override val ltButton: Color? = AppColor.CustomColor.green
        override val lbButton: Color? = null
    }
}