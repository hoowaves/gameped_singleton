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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
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

class DownButton(private val controller: IActivityController) {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val systemRepository = SystemRepository(context)
        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }
        val isSetting = remember { context is LayoutCustomView }
        val isPressed = remember { mutableStateOf(false) }

        val downButton = controller.downButton.value
        var downButtonColor by remember { mutableStateOf(SolidColor(Color(downButton ?: 0))) }
        var downButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var downButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var downButtonBorderColor by remember { mutableStateOf(Color(downButton ?: 0)) }
        var downButtonTextColor by remember { mutableStateOf(isDarkService.getTextColor()) }

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.touch_effect))
        val lottieAnimatable = rememberLottieAnimatable()
        val coroutineScope = rememberCoroutineScope()

        var customColorDialog by remember { mutableStateOf(false) }

        downButtonColor = if (downButton == 0) {
            SolidColor(isDarkService.getButtonColor())
        } else {
            SolidColor(Color(downButton ?: 0))
        }

        if (controller.isDark.value == true) {
            // 다크 모드일 때
            if (downButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    downButtonBorderWidth = 3.dp
                    downButtonBrush = Brush.linearGradient(
                        colors = listOf(
                            AppColor.DarkMode.pressBorderColor,
                            isDarkService.getBorderColor()
                        )
                    )
                    downButtonBorderColor = AppColor.DarkMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    downButtonBorderWidth = 1.dp
                    downButtonBrush = downButtonColor
                    downButtonBorderColor = isDarkService.getBorderColor()
                    downButtonTextColor = isDarkService.getTextColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    downButtonBorderWidth = 3.dp
                    downButtonBrush = Brush.linearGradient(
                        colors = listOf(Color(downButton ?: 0), isDarkService.getBorderColor())
                    )
                    downButtonBorderColor = Color(downButton ?: 0)
                    downButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    downButtonBorderWidth = 1.dp
                    downButtonBrush = downButtonColor
                    downButtonBorderColor = isDarkService.getBorderColor()
                    downButtonTextColor = isDarkService.getButtonTextColor(Color(downButton ?: 0))
                }
            }
        } else {
            // 라이트 모드일 때
            if (downButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    downButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.LightMode.pressBorderColor,
                            isDarkService.getButtonColor()
                        )
                    )
                    downButtonBorderWidth = 3.dp
                    downButtonBorderColor = AppColor.LightMode.pressBorderColor
                    downButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 기본 버튼 안 눌렀을 때
                    downButtonBrush = downButtonColor
                    downButtonBorderWidth = 1.dp
                    downButtonBorderColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    downButtonBrush =
                        SolidColor(isDarkService.getDarken(Color(downButton ?: 0), 0.7f))
                    downButtonBorderWidth = 3.dp
                    downButtonBorderColor = Color(downButton ?: 0)
                    downButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    downButtonBrush = downButtonColor
                    downButtonBorderWidth = 1.dp
                    downButtonBorderColor = isDarkService.getDarken(Color(downButton ?: 0), 0.7f)
                    downButtonTextColor = isDarkService.getButtonTextColor(Color(downButton ?: 0))
                }
            }
        }

        if (isSetting && isPressed.value) {
            downButtonBorderWidth = 3.dp
            downButtonBorderColor = AppColor.DarkMode.pressBorderColor
        }

        BoxWithConstraints {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = downButtonBrush,
                        shape = RoundedCornerShape(bottomEnd = (maxHeight.value+10).dp)
                    )
                    .border(
                        width = downButtonBorderWidth,
                        color = downButtonBorderColor,
                        shape = RoundedCornerShape(bottomEnd = maxHeight.value.dp)
                    )
                    .innerShadow(
                        shape = RoundedCornerShape(bottomEnd = (maxHeight.value + 10).dp),
                        color = isDarkService.getLightInnerShadow(),
                        offsetX = 2.dp,
                        offsetY = 2.dp,
                        blur = 10.dp,
                        spread = 0.dp,
                    )
                    .innerShadow(
                        shape = RoundedCornerShape(bottomEnd = (maxHeight.value + 10).dp),
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
                    imageVector = Icons.Default.KeyboardArrowDown,
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
                defaultColor = if (controller.downButton.value == 0) isDarkService.getButtonColor()
                    .toArgb() else controller.downButton.value ?: 0,
                controller = controller,
                onDismissRequest = { color ->
                    customColorDialog = false
                    isPressed.value = false
                    downButtonColor = SolidColor(color)
                    if (color.toArgb() == isDarkService.getButtonColor().toArgb()) {
                        controller.downButton.value = 0
                    } else {
                        controller.downButton.value = color.toArgb()
                    }
                }
            )
        }

    }
}