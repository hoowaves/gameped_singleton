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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import com.amuz.mobile_gamepad.module.widgets.dialogs.CustomColorDialog
import kotlinx.coroutines.launch

class LeftButton(private val controller: IActivityController) {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val systemRepository = SystemRepository(context)
        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }
        val isSetting = remember { context is LayoutCustomView }
        val isPressed = remember { mutableStateOf(false) }

        val leftButton = controller.leftButton.value
        var leftButtonColor by remember { mutableStateOf(SolidColor(Color(leftButton ?: 0))) }
        var leftButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var leftButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var leftButtonBorderColor by remember { mutableStateOf(Color(leftButton ?: 0)) }
        var leftButtonTextColor by remember { mutableStateOf(isDarkService.getTextColor()) }

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.touch_effect))
        val lottieAnimatable = rememberLottieAnimatable()
        val coroutineScope = rememberCoroutineScope()

        var customColorDialog by remember { mutableStateOf(false) }

        leftButtonColor = if (leftButton == 0) {
            SolidColor(isDarkService.getButtonColor())
        } else {
            SolidColor(Color(leftButton ?: 0))
        }

        if (controller.isDark.value == true) {
            // 다크 모드일 때
            if (leftButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    leftButtonBorderWidth = 3.dp
                    leftButtonBrush = Brush.linearGradient(
                        colors = listOf(
                            AppColor.DarkMode.pressBorderColor,
                            isDarkService.getBorderColor()
                        )
                    )
                    leftButtonBorderColor = AppColor.DarkMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    leftButtonBorderWidth = 1.dp
                    leftButtonBrush = leftButtonColor
                    leftButtonBorderColor = isDarkService.getBorderColor()
                    leftButtonTextColor = isDarkService.getTextColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    leftButtonBorderWidth = 3.dp
                    leftButtonBrush = Brush.linearGradient(
                        colors = listOf(Color(leftButton ?: 0), isDarkService.getBorderColor())
                    )
                    leftButtonBorderColor = Color(leftButton ?: 0)
                    leftButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    leftButtonBorderWidth = 1.dp
                    leftButtonBrush = leftButtonColor
                    leftButtonBorderColor = isDarkService.getBorderColor()
                    leftButtonTextColor = isDarkService.getButtonTextColor(Color(leftButton ?: 0))
                }
            }
        } else {
            // 라이트 모드일 때
            if (leftButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    leftButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.LightMode.pressBorderColor,
                            isDarkService.getButtonColor()
                        )
                    )
                    leftButtonBorderWidth = 3.dp
                    leftButtonBorderColor = AppColor.LightMode.pressBorderColor
                    leftButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 기본 버튼 안 눌렀을 때
                    leftButtonBrush = leftButtonColor
                    leftButtonBorderWidth = 1.dp
                    leftButtonBorderColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    leftButtonBrush = SolidColor(isDarkService.getDarken(Color(leftButton ?: 0), 0.7f))
                    leftButtonBorderWidth = 3.dp
                    leftButtonBorderColor = Color(leftButton ?: 0)
                    leftButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    leftButtonBrush = leftButtonColor
                    leftButtonBorderWidth = 1.dp
                    leftButtonBorderColor = isDarkService.getDarken(Color(leftButton ?: 0), 0.7f)
                    leftButtonTextColor = isDarkService.getButtonTextColor(Color(leftButton ?: 0))
                }
            }
        }

        if (isSetting && isPressed.value) {
            leftButtonBorderWidth = 3.dp
            leftButtonBorderColor = AppColor.DarkMode.pressBorderColor
        }

        BoxWithConstraints {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = leftButtonBrush,
                        shape = RoundedCornerShape(bottomStart = (maxHeight.value+10).dp)
                    )
                    .border(
                        width = leftButtonBorderWidth,
                        color = leftButtonBorderColor,
                        shape = RoundedCornerShape(bottomStart = maxHeight.value.dp)
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
                    imageVector = Icons.Default.KeyboardArrowLeft,
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
                                .offset(x = (-maxHeight/2),y = (-maxHeight/2))
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
                defaultColor = if (controller.leftButton.value == 0) isDarkService.getButtonColor()
                    .toArgb() else controller.leftButton.value ?: 0,
                controller = controller,
                onDismissRequest = { color ->
                    customColorDialog = false
                    isPressed.value = false
                    leftButtonColor = SolidColor(color)
                    if (color.toArgb() == isDarkService.getButtonColor().toArgb()) {
                        controller.leftButton.value = 0
                    } else {
                        controller.leftButton.value = color.toArgb()
                    }
                }
            )
        }

    }
}