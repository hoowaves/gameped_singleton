package com.amuz.mobile_gamepad.module.widgets.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.amuz.mobile_gamepad.R
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.module.activitys.layoutCustomList.LayoutCustomListView
import com.amuz.mobile_gamepad.module.system.SystemRepository
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.innerShadow
import com.amuz.mobile_gamepad.module.widgets.dialogs.CustomColorDialog
import kotlinx.coroutines.launch

class UpButton(private val controller: IActivityController) {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val systemRepository = SystemRepository(context)
        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }
        val isSetting = remember { context is LayoutCustomView }
        val isPressed = remember { mutableStateOf(false) }

        val upButton = controller.upButton.value
        var upButtonColor by remember { mutableStateOf(SolidColor(Color(upButton ?: 0))) }
        var upButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var upButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var upButtonBorderColor by remember { mutableStateOf(Color(upButton ?: 0)) }
        var upButtonTextColor by remember { mutableStateOf(isDarkService.getTextColor()) }

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.touch_effect))
        val lottieAnimatable = rememberLottieAnimatable()
        val coroutineScope = rememberCoroutineScope()

        var customColorDialog by remember { mutableStateOf(false) }

        upButtonColor = if (upButton == 0) {
            SolidColor(isDarkService.getButtonColor())
        } else {
            SolidColor(Color(upButton ?: 0))
        }

        if (controller.isDark.value == true) {
            // 다크 모드일 때
            if (upButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    upButtonBorderWidth = 3.dp
                    upButtonBrush = Brush.linearGradient(
                        colors = listOf(
                            AppColor.DarkMode.pressBorderColor,
                            isDarkService.getBorderColor()
                        )
                    )
                    upButtonBorderColor = AppColor.DarkMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    upButtonBorderWidth = 1.dp
                    upButtonBrush = upButtonColor
                    upButtonBorderColor = isDarkService.getBorderColor()
                    upButtonTextColor = isDarkService.getTextColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    upButtonBorderWidth = 3.dp
                    upButtonBrush = Brush.linearGradient(
                        colors = listOf(Color(upButton ?: 0), isDarkService.getBorderColor())
                    )
                    upButtonBorderColor = Color(upButton ?: 0)
                    upButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    upButtonBorderWidth = 1.dp
                    upButtonBrush = upButtonColor
                    upButtonBorderColor = isDarkService.getBorderColor()
                    upButtonTextColor = isDarkService.getButtonTextColor(Color(upButton ?: 0))
                }
            }
        } else {
            // 라이트 모드일 때
            if (upButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    upButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.LightMode.pressBorderColor,
                            isDarkService.getButtonColor()
                        )
                    )
                    upButtonBorderWidth = 3.dp
                    upButtonBorderColor = AppColor.LightMode.pressBorderColor
                    upButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 기본 버튼 안 눌렀을 때
                    upButtonBrush = upButtonColor
                    upButtonBorderWidth = 1.dp
                    upButtonBorderColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    upButtonBrush = SolidColor(isDarkService.getDarken(Color(upButton ?: 0), 0.7f))
                    upButtonBorderWidth = 3.dp
                    upButtonBorderColor = Color(upButton ?: 0)
                    upButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    upButtonBrush = upButtonColor
                    upButtonBorderWidth = 1.dp
                    upButtonBorderColor = isDarkService.getDarken(Color(upButton ?: 0), 0.7f)
                    upButtonTextColor = isDarkService.getButtonTextColor(Color(upButton ?: 0))
                }
            }
        }

        if (isSetting && isPressed.value) {
            upButtonBorderWidth = 3.dp
            upButtonBorderColor = AppColor.DarkMode.pressBorderColor
        }

        BoxWithConstraints {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = upButtonBrush,
                        shape = RoundedCornerShape(topStart = (maxHeight.value + 10).dp)
                    )
                    .border(
                        width = upButtonBorderWidth,
                        color = upButtonBorderColor,
                        shape = RoundedCornerShape(topStart = maxHeight.value.dp)
                    )
                    .innerShadow(
                        shape = RoundedCornerShape(topStart = (maxHeight.value + 10).dp),
                        color = isDarkService.getLightInnerShadow(),
                        offsetX = 2.dp,
                        offsetY = 2.dp,
                        blur = 10.dp,
                        spread = 0.dp,
                    )
                    .innerShadow(
                        shape = RoundedCornerShape(topStart = (maxHeight.value + 10).dp),
                        color = isDarkService.getDarkInnerShadow(),
                        offsetX = (-2).dp,
                        offsetY = (-4).dp,
                        blur = 10.dp,
                        spread = 0.dp,
                    )
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                if (isEnable) {
                                    if (controller.isVibration.value == true) {
                                        systemRepository.setVibration()
                                    }
                                    if (controller.touchEffect.value == true) {
                                        coroutineScope.launch {
                                            lottieAnimatable.animate(
                                                composition,
                                                iterations = LottieConstants.IterateForever
                                            )
                                        }
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
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = null,
                    tint = isDarkService.getTextColor(),
                    modifier = Modifier.graphicsLayer { rotationZ = -45f }
                )
                if (isEnable && isPressed.value) {
                    BoxWithConstraints {
                        Box(
                            modifier = Modifier
                                .wrapContentSize(unbounded = true)
                                .size(maxWidth * 3)
                                .offset(x = (-maxHeight / 2), y = (-maxHeight / 2))
                                .graphicsLayer(rotationZ = -45f),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            LottieAnimation(
                                composition = composition,
                                progress = lottieAnimatable.progress,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }

        if (customColorDialog) {
            CustomColorDialog(
                defaultColor = if (controller.upButton.value == 0) isDarkService.getButtonColor()
                    .toArgb() else controller.upButton.value ?: 0,
                controller = controller,
                onDismissRequest = { color ->
                    customColorDialog = false
                    isPressed.value = false
                    upButtonColor = SolidColor(color)
                    if (color.toArgb() == isDarkService.getButtonColor().toArgb()) {
                        controller.upButton.value = 0
                    } else {
                        controller.upButton.value = color.toArgb()
                    }
                }
            )
        }

    }
}