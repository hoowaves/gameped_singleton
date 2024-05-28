package com.amuz.mobile_gamepad.modules.widgets.buttons

import android.util.Log
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
import androidx.compose.runtime.getValue
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
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.modules.home.HomeView
import com.amuz.mobile_gamepad.modules.layoutSetting.LayoutSettingController
import com.amuz.mobile_gamepad.modules.layoutSetting.LayoutSettingView
import com.amuz.mobile_gamepad.modules.widgets.dialogs.CustomColorDialog
import com.amuz.mobile_gamepad.settings.SystemRepository

class LTButton {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val systemRepository = SystemRepository(context)

        val isEnable = remember { context is HomeView }
        val isSetting = remember { context is LayoutSettingView }
        var customColorDialog by remember { mutableStateOf(false) }
        val buttonColor = HomeController.ltButton.value ?: 0
        val defaultBrushColor = if (buttonColor == 0) {
            HomeController.getButtonColor()
        } else {
            Color(buttonColor)
        }
        var defaultBrush by remember { mutableStateOf(SolidColor(defaultBrushColor)) }

        BoxWithConstraints {
            val isPressed = remember { mutableStateOf(false) }
            val gradientColor = remember { mutableStateOf(Color.White) }
            val gradientBrush = Brush.linearGradient(
                colors = listOf(gradientColor.value, Color.Transparent),
                start = Offset(0f, 0f),
                end = Offset(maxWidth.value * 2, maxHeight.value * 2)
            )
            val fontSize = (maxWidth.value / 5).sp

            Box(
                modifier = Modifier
                    .width((maxWidth.value * 0.7).dp)
                    .height(maxHeight.value.dp)
                    .background(
                        brush = if (isEnable && isPressed.value) gradientBrush else defaultBrush,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .border(
                        width = if (isSetting && isPressed.value) 3.dp else 1.5.dp,
                        color = if (isSetting && isPressed.value) HomeController.getTextColor() else HomeController.getBorderColor(),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                if (isEnable) {
                                    if (HomeController.appIsVibration.value == true) {
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
                        color = HomeController.getTextColor()
                    ),
                )
            }

            if (customColorDialog) {
                CustomColorDialog(
                    onDismissRequest = { color ->
                        customColorDialog = false
                        isPressed.value = false
                        defaultBrush = SolidColor(color)
                        LayoutSettingController.setLtButton(color.toArgb())
                    }
                )
            }

        }
    }
}