package com.amuz.mobile_gamepad.modules.widgets.buttons

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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.modules.home.HomeView
import com.amuz.mobile_gamepad.modules.layoutCustom.LayoutCustomController
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingModel
import com.amuz.mobile_gamepad.modules.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.modules.widgets.dialogs.CustomColorDialog
import com.amuz.mobile_gamepad.settings.SystemRepository
import com.amuz.mobile_gamepad.settings.app.AppSettingModel

class LBButton {
    @Composable
    fun Render(layoutCustomController: LayoutCustomController) {
        val context = LocalContext.current
        val systemRepository = SystemRepository(context)

        val isEnable = remember { context is HomeView }
        val isSetting = remember { context is LayoutCustomView }
        var customColorDialog by remember { mutableStateOf(false) }
        val lbButton by layoutCustomController.lbButton.observeAsState()
        val defaultBrushColor = if (lbButton == 0) {
            AppSettingModel.getButtonColor()
        } else {
            Color(lbButton ?: 0)
        }

        var defaultBrush by remember { mutableStateOf(SolidColor(defaultBrushColor)) }
        LaunchedEffect(lbButton) {
            val newBrushColor = if (lbButton == 0) {
                AppSettingModel.getButtonColor()
            } else {
                Color(lbButton ?: 0)
            }
            defaultBrush = SolidColor(newBrushColor)
        }


        BoxWithConstraints {
            val isPressed = remember { mutableStateOf(false) }
            val gradientBrush = if (AppSettingModel.isDark.value == true) {
                Brush.verticalGradient(
                    colors = listOf(defaultBrushColor, AppSettingModel.getBorderColor())
                )
            } else {
                Brush.linearGradient(
                    colors = listOf(
                        AppSettingModel.darken(defaultBrushColor, 0.8f),
                        AppSettingModel.darken(defaultBrushColor, 0.8f)
                    ),
                    start = Offset(0f, 0f),
                    end = Offset(1f, 1f)
                )
            }
            val fontSize = (maxWidth.value / 4).sp

            Box(
                modifier = Modifier
                    .width((maxWidth.value * 0.7).dp)
                    .height(maxHeight.value.dp)
                    .background(
                        brush = if (isEnable && isPressed.value) gradientBrush else defaultBrush,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .border(
                        width = if (AppSettingModel.isDark.value == true) {
                            3.dp
                        } else {
                            if (isEnable && isPressed.value) 3.dp else 1.5.dp
                        },
                        color = if (AppSettingModel.isDark.value == true) {
                            if (isSetting && isPressed.value) {
                                AppSettingModel.getTextColor()
                            } else if (isEnable && isPressed.value) {
                                defaultBrushColor
                            } else {
                                AppSettingModel.getBorderColor()
                            }
                        } else {
                            if (isSetting && isPressed.value) {
                                AppSettingModel.getTextColor()
                            } else if (isEnable && isPressed.value) {
                                defaultBrushColor
                            } else {
                                AppSettingModel.darken(defaultBrushColor, 0.7f)
                            }
                        },
                        shape = RoundedCornerShape(18.dp)
                    )
                    .innerShadow(
                        spread = 5.dp,
                        blur = 10.dp,
                        color = if (AppSettingModel.isDark.value == true) {
                            AppSettingModel.darken(defaultBrushColor, 0.7f)
                        } else {
                            if (isEnable && isPressed.value) {
                                AppSettingModel.darken(defaultBrushColor, 0.3f)
                            } else {
                                AppSettingModel.darken(defaultBrushColor, 0.7f)
                            }
                        },
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
                    text = "LB",
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
                        defaultBrush = SolidColor(color)
                        if (color.toArgb() == AppSettingModel.getButtonColor().toArgb()) {
                            layoutCustomController.lbButton.value = 0
                        } else {
                            layoutCustomController.lbButton.value = color.toArgb()
                        }
                    }
                )
            }

        }
    }
}