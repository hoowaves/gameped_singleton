package com.amuz.mobile_gamepad.module.widgets.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.activitys.defaultMode.DefaultModeView
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.module.activitys.layoutCustomList.LayoutCustomListView
import com.amuz.mobile_gamepad.module.widgets.dialogs.CustomColorDialog
import com.amuz.mobile_gamepad.module.system.SystemRepository
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.innerShadow


class LTButton(private val controller: IActivityController) {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val systemRepository = SystemRepository(context)

        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }

        val isSetting = remember { context is LayoutCustomView }
        val isPressed = remember { mutableStateOf(false) }
        var customColorDialog by remember { mutableStateOf(false) }

        val ltButton = controller.ltButton.value
        var ltButtonColor by remember { mutableStateOf(SolidColor(Color(ltButton ?: 0))) }
        var ltButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var ltButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var ltButtonBorderColor by remember { mutableStateOf(Color(ltButton ?: 0)) }
        var ltButtonInnerShadowColor by remember { mutableStateOf(Color(ltButton ?: 0)) }
        var ltButtonTextColor by remember { mutableStateOf(isDarkService.getButtonTextColor(Color(ltButton ?: 0))) }

        ltButtonColor = if (ltButton == 0) {
            SolidColor(isDarkService.getButtonColor())
        } else {
            SolidColor(Color(ltButton ?: 0))
        }

        if (controller.isDark.value == true) {
            ltButtonInnerShadowColor = isDarkService.getDarken(Color(ltButton ?: 0), 0.5f)
            // 다크 모드일 때
            ltButtonBorderWidth = 3.dp
            if (ltButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    ltButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.DarkMode.pressBorderColor,
                            isDarkService.getBorderColor()
                        )
                    )
                    ltButtonBorderColor = AppColor.DarkMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    ltButtonBrush = ltButtonColor
                    ltButtonBorderColor = isDarkService.getBorderColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    ltButtonBrush = Brush.verticalGradient(
                        colors = listOf(Color(ltButton ?: 0), isDarkService.getBorderColor())
                    )
                    ltButtonBorderColor = Color(ltButton ?: 0)
                    ltButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    ltButtonBrush = ltButtonColor
                    ltButtonBorderColor = isDarkService.getBorderColor()
                    ltButtonTextColor = isDarkService.getButtonTextColor(Color(ltButton ?: 0))
                }
            }
        } else {
            // 라이트 모드일 때
            if (ltButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    ltButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.LightMode.pressBorderColor,
                            isDarkService.getButtonColor()
                        )
                    )
                    ltButtonBorderWidth = 3.dp
                    ltButtonBorderColor = AppColor.LightMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    ltButtonBrush = ltButtonColor
                    ltButtonBorderWidth = 1.5.dp
                    ltButtonBorderColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
                    ltButtonInnerShadowColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.9f)
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    ltButtonBrush = SolidColor(isDarkService.getDarken(Color(ltButton ?: 0), 0.7f))
                    ltButtonBorderWidth = 3.dp
                    ltButtonBorderColor = Color(ltButton ?: 0)
                    ltButtonInnerShadowColor = isDarkService.getDarken(Color(ltButton ?: 0), 0.5f)
                    ltButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    ltButtonBrush = ltButtonColor
                    ltButtonBorderWidth = 1.5.dp
                    ltButtonBorderColor = isDarkService.getDarken(Color(ltButton ?: 0), 0.7f)
                    ltButtonInnerShadowColor = isDarkService.getDarken(Color(ltButton ?: 0), 0.7f)
                    ltButtonTextColor = isDarkService.getButtonTextColor(Color(ltButton ?: 0))
                }
            }
        }

        BoxWithConstraints {
            val fontSize = (maxWidth.value / 4).sp

            Box(
                modifier = Modifier
                    .width((maxWidth.value * 0.7).dp)
                    .height((maxHeight.value).dp)
                    .background(
                        brush = ltButtonBrush,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .border(
                        width = ltButtonBorderWidth,
                        color = ltButtonBorderColor,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .innerShadow(
                        spread = 1.dp,
                        blur = 20.dp,
                        color = ltButtonInnerShadowColor,
                        cornersRadius = 18.dp
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                if (isEnable) {
                                    if (controller.isVibration.value == true) {
                                        systemRepository.setVibration()
                                    }
                                    isPressed.value = true
                                    tryAwaitRelease()
                                    isPressed.value = false
                                } else {
                                    if (isSetting) {
                                        isPressed.value = true
                                        customColorDialog = true
                                    }
                                }
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "LT",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSize,
                        color = ltButtonTextColor
                    ),
                )
            }

            if (customColorDialog) {
                CustomColorDialog(
                    defaultColor = if (controller.ltButton.value == 0) isDarkService.getButtonColor()
                        .toArgb() else controller.ltButton.value ?: 0,
                    controller = controller,
                    onDismissRequest = { color ->
                        customColorDialog = false
                        isPressed.value = false
                        ltButtonColor = SolidColor(color)
                        if (color.toArgb() == isDarkService.getButtonColor().toArgb()) {
                            controller.ltButton.value = 0
                        } else {
                            controller.ltButton.value = color.toArgb()
                        }
                    }
                )
            }

        }
    }

}