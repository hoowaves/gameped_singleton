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
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

class XButton(private val controller: IActivityController) {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val systemRepository = SystemRepository(context)
        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }
        val isSetting = remember { context is LayoutCustomView }
        val isPressed = remember { mutableStateOf(false) }

        val xButton = controller.xButton.value
        var xButtonColor by remember { mutableStateOf(SolidColor(Color(xButton ?: 0))) }
        var xButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var xButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var xButtonBorderColor by remember { mutableStateOf(Color(xButton ?: 0)) }
        var xButtonTextColor by remember { mutableStateOf(isDarkService.getTextColor()) }

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.touch_effect))
        val lottieAnimatable = rememberLottieAnimatable()
        val coroutineScope = rememberCoroutineScope()

        var customColorDialog by remember { mutableStateOf(false) }

        xButtonColor = if (xButton == 0) {
            SolidColor(isDarkService.getButtonColor())
        } else {
            SolidColor(Color(xButton ?: 0))
        }

        if (controller.isDark.value == true) {
            // 다크 모드일 때
            if (xButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    xButtonBorderWidth = 3.dp
                    xButtonBrush = Brush.linearGradient(
                        colors = listOf(
                            AppColor.DarkMode.pressBorderColor,
                            isDarkService.getBorderColor()
                        )
                    )
                    xButtonBorderColor = AppColor.DarkMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    xButtonBorderWidth = 1.dp
                    xButtonBrush = xButtonColor
                    xButtonBorderColor = isDarkService.getBorderColor()
                    xButtonTextColor = isDarkService.getTextColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    xButtonBorderWidth = 3.dp
                    xButtonBrush = Brush.linearGradient(
                        colors = listOf(Color(xButton ?: 0), isDarkService.getBorderColor())
                    )
                    xButtonBorderColor = Color(xButton ?: 0)
                    xButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    xButtonBorderWidth = 1.dp
                    xButtonBrush = xButtonColor
                    xButtonBorderColor = isDarkService.getBorderColor()
                    xButtonTextColor = isDarkService.getButtonTextColor(Color(xButton ?: 0))
                }
            }
        } else {
            // 라이트 모드일 때
            if (xButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    xButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.LightMode.pressBorderColor,
                            isDarkService.getButtonColor()
                        )
                    )
                    xButtonBorderWidth = 3.dp
                    xButtonBorderColor = AppColor.LightMode.pressBorderColor
                    xButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 기본 버튼 안 눌렀을 때
                    xButtonBrush = xButtonColor
                    xButtonBorderWidth = 1.dp
                    xButtonBorderColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    xButtonBrush = SolidColor(isDarkService.getDarken(Color(xButton ?: 0), 0.7f))
                    xButtonBorderWidth = 3.dp
                    xButtonBorderColor = Color(xButton ?: 0)
                    xButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    xButtonBrush = xButtonColor
                    xButtonBorderWidth = 1.dp
                    xButtonBorderColor = isDarkService.getDarken(Color(xButton ?: 0), 0.7f)
                    xButtonTextColor = isDarkService.getButtonTextColor(Color(xButton ?: 0))
                }
            }
        }


        BoxWithConstraints {
            val fontSize = (maxHeight.value / 4).sp

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = xButtonBrush,
                        shape = RoundedCornerShape(bottomStart = 20.dp)
                    )
                    .border(
                        width = xButtonBorderWidth,
                        color = xButtonBorderColor,
                        shape = RoundedCornerShape(bottomStart = 20.dp)
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
                Text(
                    text = "X",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSize,
                        color = xButtonTextColor
                    ),
                    modifier = Modifier.graphicsLayer(
                        rotationZ = -45f
                    )
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
                defaultColor = if (controller.xButton.value == 0) isDarkService.getButtonColor()
                    .toArgb() else controller.xButton.value ?: 0,
                controller = controller,
                onDismissRequest = { color ->
                    customColorDialog = false
                    isPressed.value = false
                    xButtonColor = SolidColor(color)
                    if (color.toArgb() == isDarkService.getButtonColor().toArgb()) {
                        controller.xButton.value = 0
                    } else {
                        controller.xButton.value = color.toArgb()
                    }
                }
            )
        }

    }
}