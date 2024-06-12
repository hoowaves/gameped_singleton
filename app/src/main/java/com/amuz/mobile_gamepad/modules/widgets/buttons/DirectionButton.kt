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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
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
import com.amuz.mobile_gamepad.modules.home.HomeView
import com.amuz.mobile_gamepad.modules.network.WebOSManager
import com.amuz.mobile_gamepad.settings.SystemRepository
import com.amuz.mobile_gamepad.settings.app.AppSettingModel

class DirectionButton {

    @Composable
    fun Render() {
        val context = LocalContext.current
        val systemRepository = SystemRepository(context)
        val isEnable = remember { context is HomeView }

        BoxWithConstraints {
            val isPressedY = remember { mutableStateOf(false) }
            val isPressedB = remember { mutableStateOf(false) }
            val isPressedX = remember { mutableStateOf(false) }
            val isPressedA = remember { mutableStateOf(false) }
            val isPressedCenter = remember { mutableStateOf(false) }

            val gradientColor = remember { mutableStateOf(Color.White) }
            val gradientBrush = Brush.linearGradient(
                colors = listOf(gradientColor.value, Color.Transparent),
                start = Offset(0f, 0f),
                end = Offset(maxWidth.value * 2, maxHeight.value * 2)
            )
            val defaultBrush = SolidColor(AppSettingModel.getButtonColor())
            val maxHeight = maxHeight
            val size = (maxHeight.value)
            val fontSize = (maxHeight.value / 8).sp


            Box(
                modifier = Modifier
                    .size(size.dp)
                    .graphicsLayer {
                        rotationZ = 45f
                    }
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .border(
                        1.5.dp,
                        color = AppSettingModel.getBorderColor(),
                        shape = RoundedCornerShape((size / 2).dp)
                    )
                ) {
                    Row(modifier = Modifier.weight(1f)) {
                        // Y
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .background(
                                    brush = if (isPressedY.value) gradientBrush else defaultBrush,
                                    shape = RoundedCornerShape(topStart = (size / 2).dp)
                                )
                                .border(
                                    1.5.dp,
                                    color = AppSettingModel.getBorderColor(),
                                    shape = RoundedCornerShape(topStart = (size / 2).dp)
                                )
                                .innerShadow(
                                    spread = 3.dp,
                                    blur = 10.dp,
                                    color = AppSettingModel.getBackgroundColor(),
                                )
                                .pointerInput(Unit) {
                                    if (!isEnable) return@pointerInput
                                    detectTapGestures(
                                        onPress = {
                                            if (AppSettingModel.isVibration.value == true) {
                                                systemRepository.setVibration()
                                            }
                                            WebOSManager.sendUp()
                                            isPressedY.value = true
                                            tryAwaitRelease()
                                            isPressedY.value = false
                                        }
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowUp,
                                contentDescription = null,
                                tint = AppSettingModel.getTextColor(),
                                modifier = Modifier.graphicsLayer { rotationZ = -45f }
                            )
                        }
                        // B
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .background(
                                    brush = if (isPressedB.value) gradientBrush else defaultBrush,
                                    shape = RoundedCornerShape(topEnd = (size / 2).dp)
                                )
                                .innerShadow(
                                    spread = 3.dp,
                                    blur = 10.dp,
                                    color = AppSettingModel.getBackgroundColor(),
                                )
                                .pointerInput(Unit) {
                                    if (!isEnable) return@pointerInput
                                    detectTapGestures(
                                        onPress = {
                                            if (AppSettingModel.isVibration.value == true) {
                                                systemRepository.setVibration()
                                            }
                                            WebOSManager.sendRight()
                                            isPressedB.value = true
                                            tryAwaitRelease()
                                            isPressedB.value = false
                                        }
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = null,
                                tint = AppSettingModel.getTextColor(),
                                modifier = Modifier.graphicsLayer { rotationZ = -45f }
                            )
                        }
                    }
                    Row(modifier = Modifier.weight(1f)) {
                        // X
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .background(
                                    brush = if (isPressedX.value) gradientBrush else defaultBrush,
                                    shape = RoundedCornerShape(bottomStart = (size / 2).dp)
                                )
                                .innerShadow(
                                    spread = 3.dp,
                                    blur = 10.dp,
                                    color = AppSettingModel.getBackgroundColor(),
                                )
                                .pointerInput(Unit) {
                                    if (!isEnable) return@pointerInput
                                    detectTapGestures(
                                        onPress = {
                                            if (AppSettingModel.isVibration.value == true) {
                                                systemRepository.setVibration()
                                            }
                                            WebOSManager.sendLeft()
                                            isPressedX.value = true
                                            tryAwaitRelease()
                                            isPressedX.value = false
                                        }
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                                tint = AppSettingModel.getTextColor(),
                                modifier = Modifier.graphicsLayer { rotationZ = -45f }
                            )
                        }
                        // A
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .background(
                                    brush = if (isPressedA.value) gradientBrush else defaultBrush,
                                    shape = RoundedCornerShape(bottomEnd = (size / 2).dp)
                                )
                                .border(
                                    1.5.dp,
                                    color = AppSettingModel.getBorderColor(),
                                    shape = RoundedCornerShape(bottomEnd = (size / 2).dp)
                                )
                                .innerShadow(
                                    spread = 3.dp,
                                    blur = 10.dp,
                                    color = AppSettingModel.getBackgroundColor(),
                                )
                                .pointerInput(Unit) {
                                    if (!isEnable) return@pointerInput
                                    detectTapGestures(
                                        onPress = {
                                            if (AppSettingModel.isVibration.value == true) {
                                                systemRepository.setVibration()
                                            }
                                            WebOSManager.sendDown()
                                            isPressedA.value = true
                                            tryAwaitRelease()
                                            isPressedA.value = false
                                        }
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null,
                                tint = AppSettingModel.getTextColor(),
                                modifier = Modifier.graphicsLayer { rotationZ = -45f }
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .size(size.dp)
                , contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size((size / 3).dp)
                        .shadow(
                            elevation = 10.dp,
                            spotColor = AppSettingModel.getBorderColor(),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .background(
                            brush = if (isPressedCenter.value) gradientBrush else SolidColor(
                                AppSettingModel.getBackgroundColor()
                            ),
                            shape = RoundedCornerShape((size / 2).dp)
                        )
                        .border(
                            1.5.dp,
                            color = AppSettingModel.getBorderColor(),
                            shape = RoundedCornerShape((size / 2).dp)
                        )
                        .pointerInput(Unit) {
                            if (!isEnable) return@pointerInput
                            detectTapGestures(
                                onPress = {
                                    if (AppSettingModel.isVibration.value == true) {
                                        systemRepository.setVibration()
                                    }
                                    WebOSManager.sendOk()
                                    isPressedCenter.value = true
                                    tryAwaitRelease()
                                    isPressedCenter.value = false
                                }
                            )
                        },
                )
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
                color = AppSettingModel.getTextColor()
            ),
            modifier = Modifier.graphicsLayer { rotationZ = -45f }
        )
    }
}