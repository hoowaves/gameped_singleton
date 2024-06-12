package com.amuz.mobile_gamepad.modules.widgets.buttons

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
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

class LTSButton() {
    @Composable
    fun Render(layoutCustomController: LayoutCustomController) {
        val context = LocalContext.current
        val systemRepository = SystemRepository(context)
        val isEnable = remember { context is HomeView }
        val isSetting = remember { context is LayoutCustomView }
        var customColorDialog by remember { mutableStateOf(false) }

        val ltsButton by layoutCustomController.ltsButton.observeAsState()
        val defaultBrushColor = if (ltsButton == 0) {
            AppSettingModel.getButtonColor()
        } else {
            Color(ltsButton ?: 0)
        }

        var defaultBrush by remember { mutableStateOf(SolidColor(defaultBrushColor)) }
        LaunchedEffect(ltsButton) {
            val newBrushColor = if (ltsButton == 0) {
                AppSettingModel.getButtonColor()
            } else {
                Color(ltsButton ?: 0)
            }
            defaultBrush = SolidColor(newBrushColor)
        }

        BoxWithConstraints {
            val isPressed = remember { mutableStateOf(false) }
            val gradientColor = remember { mutableStateOf(Color.White) }
            val gradientBrush = Brush.linearGradient(
                colors = listOf(gradientColor.value, Color.Transparent),
                start = Offset(0f, 0f),
                end = Offset(maxWidth.value * 2, maxHeight.value * 2)
            )
            val size = (maxWidth.value * 0.7).dp
            val fontSize = (maxWidth.value / 5).sp

            Box(
                modifier = Modifier
                    .size(size)
                    .offset(x = (-size.value * 0.7).dp)
                    .background(
                        brush = if (isEnable && isPressed.value) gradientBrush else defaultBrush,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .border(
                        width = if (isSetting && isPressed.value) 3.dp else 1.5.dp,
                        color = if (isSetting && isPressed.value) AppSettingModel.getTextColor() else AppSettingModel.getBorderColor(),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .innerShadow(
                        spread = 5.dp,
                        blur = 10.dp,
                        color = AppSettingModel.getBackgroundColor(),
                        cornersRadius = 15.dp
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
                    text = "LTS",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSize,
                        color = AppSettingModel.getTextColor()
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
                            layoutCustomController.ltsButton.value = 0
                        } else {
                            layoutCustomController.ltsButton.value = color.toArgb()
                        }

                    }
                )
            }

        }
    }
}