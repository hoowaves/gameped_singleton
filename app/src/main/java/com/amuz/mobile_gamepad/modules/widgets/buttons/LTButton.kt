package com.amuz.mobile_gamepad.modules.widgets.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.BrushPainter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.modules.home.HomeView
import com.amuz.mobile_gamepad.modules.layoutCustom.LayoutCustomController
import com.amuz.mobile_gamepad.modules.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.modules.widgets.dialogs.CustomColorDialog
import com.amuz.mobile_gamepad.settings.SystemRepository
import com.amuz.mobile_gamepad.settings.app.AppSettingModel


class LTButton {
    @Composable
    fun Render(layoutCustomController: LayoutCustomController) {
        val context = LocalContext.current
        val systemRepository = SystemRepository(context)

        val isEnable = remember { context is HomeView }
        val isSetting = remember { context is LayoutCustomView }
        val isPressed = remember { mutableStateOf(false) }
        var customColorDialog by remember { mutableStateOf(false) }

        val ltButton by layoutCustomController.ltButton.observeAsState()
        var ltButtonColor by remember { mutableStateOf(SolidColor(Color(ltButton ?: 0))) }
        var ltButtonBrush by remember { mutableStateOf<Brush>(SolidColor(AppSettingModel.getButtonColor())) }
        var ltButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var ltButtonBorderColor by remember { mutableStateOf(Color(ltButton ?: 0)) }
        var ltButtonInnerShadowColor by remember { mutableStateOf(Color(ltButton ?: 0)) }

        LaunchedEffect(ltButton) {
            ltButtonColor = if (ltButton == 0) {
                SolidColor(AppSettingModel.getButtonColor())
            } else {
                SolidColor(Color(ltButton ?: 0))
            }
        }

        if (AppSettingModel.isDark.value == true) {
            ltButtonInnerShadowColor = AppSettingModel.darken(Color(ltButton ?: 0), 0.5f)
            // 다크 모드일 때
            ltButtonBorderWidth = 3.dp
            if (ltButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    ltButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.DarkMode.pressBorderColor,
                            AppSettingModel.getBorderColor()
                        )
                    )
                    ltButtonBorderColor = AppColor.DarkMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    ltButtonBrush = ltButtonColor
                    ltButtonBorderColor = AppSettingModel.getBorderColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    ltButtonBrush = Brush.verticalGradient(
                        colors = listOf(Color(ltButton ?: 0), AppSettingModel.getBorderColor())
                    )
                    ltButtonBorderColor = Color(ltButton ?: 0)
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    ltButtonBrush = ltButtonColor
                    ltButtonBorderColor = AppSettingModel.getBorderColor()
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
                            AppSettingModel.getButtonColor()
                        )
                    )
                    ltButtonBorderWidth = 3.dp
                    ltButtonBorderColor = AppColor.LightMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    ltButtonBrush = ltButtonColor
                    ltButtonBorderWidth = 1.5.dp
                    ltButtonBorderColor = AppSettingModel.darken(AppSettingModel.getButtonColor(), 0.7f)
                    ltButtonInnerShadowColor = AppSettingModel.darken(AppSettingModel.getButtonColor(), 0.9f)
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    ltButtonBrush = SolidColor(AppSettingModel.darken(Color(ltButton ?: 0), 0.7f))
                    ltButtonBorderWidth = 3.dp
                    ltButtonBorderColor = Color(ltButton ?: 0)
                    ltButtonInnerShadowColor = AppSettingModel.darken(Color(ltButton ?: 0), 0.5f)
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    ltButtonBrush = ltButtonColor
                    ltButtonBorderWidth = 1.5.dp
                    ltButtonBorderColor = AppSettingModel.darken(Color(ltButton ?: 0), 0.7f)
                    ltButtonInnerShadowColor = AppSettingModel.darken(Color(ltButton ?: 0), 0.7f)
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
                                    if (AppSettingModel.isVibration.value == true) {
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
                        color = Color.White
                    ),
                )
            }

            if (customColorDialog) {
                CustomColorDialog(
                    onDismissRequest = { color ->
                        customColorDialog = false
                        isPressed.value = false
                        ltButtonColor = SolidColor(color)
                        if (color.toArgb() == AppSettingModel.getButtonColor().toArgb()) {
                            layoutCustomController.ltButton.value = 0
                        } else {
                            layoutCustomController.ltButton.value = color.toArgb()
                        }
                    }
                )
            }

        }
    }

}