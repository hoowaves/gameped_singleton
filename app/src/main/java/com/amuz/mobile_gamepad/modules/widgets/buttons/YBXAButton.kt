package com.amuz.mobile_gamepad.modules.widgets.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.modules.home.HomeView
import com.amuz.mobile_gamepad.modules.layoutSetting.LayoutSettingView
import com.amuz.mobile_gamepad.modules.widgets.dialogs.customColorDialog.CustomColorDialog
import com.amuz.mobile_gamepad.settings.SystemRepository

class YBXAButton {

    @Composable
    fun Render() {
        val context = LocalContext.current
        val systemRepository = SystemRepository(context)
        val isEnable = remember { context is HomeView }
        val isSetting = remember { context is LayoutSettingView }
        var customColorDialogY by remember { mutableStateOf(false) }
        var customColorDialogB by remember { mutableStateOf(false) }
        var customColorDialogX by remember { mutableStateOf(false) }
        var customColorDialogA by remember { mutableStateOf(false) }
        var defaultBrushY by remember { mutableStateOf(SolidColor(HomeController.getButtonColor())) }
        var defaultBrushB by remember { mutableStateOf(SolidColor(HomeController.getButtonColor())) }
        var defaultBrushX by remember { mutableStateOf(SolidColor(HomeController.getButtonColor())) }
        var defaultBrushA by remember { mutableStateOf(SolidColor(HomeController.getButtonColor())) }

        BoxWithConstraints {
            val isPressedY = remember { mutableStateOf(false) }
            val isPressedB = remember { mutableStateOf(false) }
            val isPressedX = remember { mutableStateOf(false) }
            val isPressedA = remember { mutableStateOf(false) }
            val gradientColor = remember { mutableStateOf(Color.White) }
            val gradientBrush = Brush.linearGradient(
                colors = listOf(gradientColor.value, Color.Transparent),
                start = Offset(0f, 0f),
                end = Offset(maxWidth.value * 2, maxHeight.value * 2)
            )
            val maxHeight = maxHeight
            val size = (maxHeight.value * 0.8)
            val fontSize = (maxHeight.value / 8).sp

            Box(
                modifier = Modifier
                    .size(size.dp)
                    .graphicsLayer {
                        rotationZ = 45f
                    }
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(modifier = Modifier.weight(1f)) {
                        // Y
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .background(
                                    brush = if (isEnable && isPressedY.value) gradientBrush else defaultBrushY,
                                    shape = RoundedCornerShape(topStart = 20.dp)
                                )
                                .border(
                                    width = if (isSetting && isPressedY.value) 3.dp else 1.5.dp,
                                    color = if (isSetting && isPressedY.value) HomeController.getTextColor() else HomeController.getBorderColor(),
                                    shape = RoundedCornerShape(topStart = 20.dp)
                                )
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            if (isEnable) {
                                                if (HomeController.getIsVibrator() == true) {
                                                    systemRepository.setVibration()
                                                }
                                                isPressedY.value = true
                                                tryAwaitRelease()
                                                isPressedY.value = false
                                            } else {
                                                if (isSetting) {
                                                    isPressedY.value = true
                                                    customColorDialogY = true
                                                }
                                            }
                                        }
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            TextButton(text = "Y", fontSize)
                        }
                        // B
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .background(
                                    brush = if (isEnable && isPressedB.value) gradientBrush else defaultBrushB,
                                    shape = RoundedCornerShape(topEnd = 20.dp)
                                )
                                .border(
                                    width = if (isSetting && isPressedB.value) 3.dp else 1.5.dp,
                                    color = if (isSetting && isPressedB.value) HomeController.getTextColor() else HomeController.getBorderColor(),
                                    shape = RoundedCornerShape(topEnd = 20.dp)
                                )
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            if (isEnable) {
                                                if (HomeController.getIsVibrator() == true) {
                                                    systemRepository.setVibration()
                                                }
                                                isPressedB.value = true
                                                tryAwaitRelease()
                                                isPressedB.value = false
                                            } else {
                                                if (isSetting) {
                                                    isPressedB.value = true
                                                    customColorDialogB = true
                                                }
                                            }
                                        }
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            TextButton(text = "B", fontSize = fontSize)
                        }
                    }
                    Row(modifier = Modifier.weight(1f)) {
                        // X
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .background(
                                    brush = if (isEnable && isPressedX.value) gradientBrush else defaultBrushX,
                                    shape = RoundedCornerShape(bottomStart = 20.dp)
                                )
                                .border(
                                    width = if (isSetting && isPressedX.value) 3.dp else 1.5.dp,
                                    color = if (isSetting && isPressedX.value) HomeController.getTextColor() else HomeController.getBorderColor(),
                                    shape = RoundedCornerShape(bottomStart = 20.dp)
                                )
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            if (isEnable) {
                                                if (HomeController.getIsVibrator() == true) {
                                                    systemRepository.setVibration()
                                                }
                                                isPressedX.value = true
                                                tryAwaitRelease()
                                                isPressedX.value = false
                                            } else {
                                                if (isSetting) {
                                                    isPressedX.value = true
                                                    customColorDialogX = true
                                                }
                                            }
                                        }
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            TextButton(text = "X", fontSize = fontSize)
                        }
                        // A
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .background(
                                    brush = if (isEnable && isPressedA.value) gradientBrush else defaultBrushA,
                                    shape = RoundedCornerShape(bottomEnd = 20.dp)
                                )
                                .border(
                                    width = if (isSetting && isPressedA.value) 3.dp else 1.5.dp,
                                    color = if (isSetting && isPressedA.value) HomeController.getTextColor() else HomeController.getBorderColor(),
                                    shape = RoundedCornerShape(bottomEnd = 20.dp)
                                )
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onPress = {
                                            if (isEnable) {
                                                if (HomeController.getIsVibrator() == true) {
                                                    systemRepository.setVibration()
                                                }
                                                isPressedA.value = true
                                                tryAwaitRelease()
                                                isPressedA.value = false
                                            } else {
                                                if (isSetting) {
                                                    isPressedA.value = true
                                                    customColorDialogA = true
                                                }
                                            }
                                        }
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            TextButton(text = "A", fontSize = fontSize)
                        }
                    }
                }

                if (customColorDialogY) {
                    CustomColorDialog(
                        onDismissRequest = { color ->
                            customColorDialogY = false
                            isPressedY.value = false
                            defaultBrushY = SolidColor(color)
                        }
                    )
                }

                if (customColorDialogB) {
                    CustomColorDialog(
                        onDismissRequest = { color ->
                            customColorDialogB = false
                            isPressedB.value = false
                            defaultBrushB = SolidColor(color)
                        }
                    )
                }

                if (customColorDialogX) {
                    CustomColorDialog(
                        onDismissRequest = { color ->
                            customColorDialogX = false
                            isPressedX.value = false
                            defaultBrushX = SolidColor(color)
                        }
                    )
                }

                if (customColorDialogA) {
                    CustomColorDialog(
                        onDismissRequest = { color ->
                            customColorDialogA = false
                            isPressedA.value = false
                            defaultBrushA = SolidColor(color)
                        }
                    )
                }

            }

        }
    }

    @Composable
    private fun TextButton(text: String, fontSize: TextUnit) {
        Text(
            text = text,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = fontSize,
                color = HomeController.getTextColor()
            ),
            modifier = Modifier.graphicsLayer { rotationZ = -45f }
        )
    }
}