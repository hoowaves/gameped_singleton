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
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.module.activitys.layoutCustomList.LayoutCustomListView
import com.amuz.mobile_gamepad.module.widgets.dialogs.CustomColorDialog
import com.amuz.mobile_gamepad.module.system.SystemRepository
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.innerShadow

class LBButton(private val controller: IActivityController) {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val systemRepository = SystemRepository(context)

        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }

        val isSetting = remember { context is LayoutCustomView }
        val isPressed = remember { mutableStateOf(false) }
        var customColorDialog by remember { mutableStateOf(false) }

        val lbButton = controller.lbButton.value
        var lbButtonColor by remember { mutableStateOf(SolidColor(Color(lbButton ?: 0))) }
        var lbButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var lbButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var lbButtonBorderColor by remember { mutableStateOf(Color(lbButton ?: 0)) }
        var lbButtonInnerShadowColor by remember { mutableStateOf(Color(lbButton ?: 0)) }
        var lbButtonTextColor by remember { mutableStateOf(isDarkService.getButtonTextColor(Color(lbButton ?: 0))) }


        lbButtonColor = if (lbButton == 0) {
            SolidColor(isDarkService.getButtonColor())
        } else {
            SolidColor(Color(lbButton ?: 0))
        }

        if (controller.isDark.value == true) {
            lbButtonInnerShadowColor = isDarkService.getDarken(Color(lbButton ?: 0), 0.5f)
            // 다크 모드일 때
            lbButtonBorderWidth = 3.dp
            if (lbButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    lbButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.DarkMode.pressBorderColor,
                            isDarkService.getBorderColor()
                        )
                    )
                    lbButtonBorderColor = AppColor.DarkMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    lbButtonBrush = lbButtonColor
                    lbButtonBorderColor = isDarkService.getBorderColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    lbButtonBrush = Brush.verticalGradient(
                        colors = listOf(Color(lbButton ?: 0), isDarkService.getBorderColor())
                    )
                    lbButtonBorderColor = Color(lbButton ?: 0)
                    lbButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    lbButtonBrush = lbButtonColor
                    lbButtonBorderColor = isDarkService.getBorderColor()
                    lbButtonTextColor = isDarkService.getButtonTextColor(Color(lbButton ?: 0))
                }
            }
        } else {
            // 라이트 모드일 때
            if (lbButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    lbButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.LightMode.pressBorderColor,
                            isDarkService.getButtonColor()
                        )
                    )
                    lbButtonBorderWidth = 3.dp
                    lbButtonBorderColor = AppColor.LightMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    lbButtonBrush = lbButtonColor
                    lbButtonBorderWidth = 1.5.dp
                    lbButtonBorderColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
                    lbButtonInnerShadowColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.9f)
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    lbButtonBrush = SolidColor(isDarkService.getDarken(Color(lbButton ?: 0), 0.7f))
                    lbButtonBorderWidth = 3.dp
                    lbButtonBorderColor = Color(lbButton ?: 0)
                    lbButtonInnerShadowColor = isDarkService.getDarken(Color(lbButton ?: 0), 0.5f)
                    lbButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    lbButtonBrush = lbButtonColor
                    lbButtonBorderWidth = 1.5.dp
                    lbButtonBorderColor = isDarkService.getDarken(Color(lbButton ?: 0), 0.7f)
                    lbButtonInnerShadowColor = isDarkService.getDarken(Color(lbButton ?: 0), 0.7f)
                    lbButtonTextColor = isDarkService.getButtonTextColor(Color(lbButton ?: 0))
                }
            }
        }

        BoxWithConstraints {
            val fontSize = (maxWidth.value / 4).sp

            Box(
                modifier = Modifier
                    .width((maxWidth.value * 0.7).dp)
                    .height(maxHeight.value.dp)
                    .background(
                        brush = lbButtonBrush,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .border(
                        width = lbButtonBorderWidth,
                        color = lbButtonBorderColor,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .innerShadow(
                        spread = 1.dp,
                        blur = 20.dp,
                        color = lbButtonInnerShadowColor,
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
                    text = "LB",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSize,
                        color = lbButtonTextColor
                    ),
                )
            }

            if (customColorDialog) {
                CustomColorDialog(
                    defaultColor = if (controller.lbButton.value == 0) isDarkService.getButtonColor()
                        .toArgb() else controller.lbButton.value ?: 0,
                    controller = controller,
                    onDismissRequest = { color ->
                        customColorDialog = false
                        isPressed.value = false
                        lbButtonColor = SolidColor(color)
                        if (color.toArgb() == isDarkService.getButtonColor().toArgb()) {
                            controller.lbButton.value = 0
                        } else {
                            controller.lbButton.value = color.toArgb()
                        }
                    }
                )
            }

        }
    }
}