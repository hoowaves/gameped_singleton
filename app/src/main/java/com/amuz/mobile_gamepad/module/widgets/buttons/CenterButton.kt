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
import kotlinx.coroutines.launch

class CenterButton(private val controller: IActivityController) {

    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val systemRepository = SystemRepository(context)
        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }
        val isSetting = remember { context is LayoutCustomView }
        val isPressed = remember { mutableStateOf(false) }

        val centerButton = isDarkService.getButtonColor().toArgb()
        val centerButtonColor by remember { mutableStateOf(SolidColor(Color(centerButton))) }
        var centerButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var centerButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var centerButtonBorderColor by remember { mutableStateOf(Color(centerButton)) }
        var centerButtonTextColor by remember { mutableStateOf(isDarkService.getTextColor()) }

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.touch_effect))
        val lottieAnimatable = rememberLottieAnimatable()
        val coroutineScope = rememberCoroutineScope()

        if (controller.isDark.value == true) {
            // 다크 모드일 때
            // 기본 버튼일 때
            if (isEnable && isPressed.value) {
                // 기본 버튼 눌렀을 때
                centerButtonBrush = Brush.verticalGradient(
                    colors = listOf(
                        AppColor.DarkMode.pressBorderColor,
                        isDarkService.getBorderColor()
                    )
                )
                centerButtonBorderWidth = 3.dp
                centerButtonBorderColor = AppColor.DarkMode.pressBorderColor
            } else {
                // 기본 버튼 안 눌렀을 때
                centerButtonBorderWidth = 1.5.dp
                centerButtonBrush = centerButtonColor
                centerButtonBorderColor = isDarkService.getBorderColor()
                centerButtonTextColor = isDarkService.getTextColor()
            }
        } else {
            // 라이트 모드일 때
            // 기본 버튼일 때
            if (isEnable && isPressed.value) {
                // 기본 버튼 눌렀을 때
                centerButtonBrush = Brush.verticalGradient(
                    colors = listOf(
                        AppColor.LightMode.pressBorderColor,
                        isDarkService.getButtonColor()
                    )
                )
                centerButtonBorderWidth = 3.dp
                centerButtonBorderColor = AppColor.LightMode.pressBorderColor
                centerButtonTextColor = isDarkService.getTextColor()
            } else {
                // 기본 버튼 안 눌렀을 때
                centerButtonBrush = centerButtonColor
                centerButtonBorderWidth = 1.5.dp
                centerButtonBorderColor =
                    isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
            }
        }

        BoxWithConstraints {
            val size = (maxHeight.value)
            Box(
                modifier = androidx.compose.ui.Modifier
                    .size((size / 3).dp)
                    .background(
                        brush = centerButtonBrush,
                        shape = RoundedCornerShape((size / 2).dp)
                    )
                    .border(
                        width = centerButtonBorderWidth,
                        color = centerButtonBorderColor,
                        shape = RoundedCornerShape((size / 2).dp)
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
                                    }
                                }
                            }
                        )
                    },
            ) {
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

    }
}