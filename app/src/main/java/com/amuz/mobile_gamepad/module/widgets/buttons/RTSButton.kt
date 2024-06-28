package com.amuz.mobile_gamepad.module.widgets.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.amuz.mobile_gamepad.module.activitys.defaultMode.DefaultModeView
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.module.activitys.layoutCustomList.LayoutCustomListView
import com.amuz.mobile_gamepad.module.widgets.dialogs.CustomColorDialog
import com.amuz.mobile_gamepad.module.system.SystemRepository
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.innerShadow
import com.amuz.mobile_gamepad.module.widgets.commons.outerShadow
import kotlinx.coroutines.launch

class RTSButton(private val controller: IActivityController) {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val systemRepository = SystemRepository(context)

        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }

        val isSetting = remember { context is LayoutCustomView }
        val isPressed = remember { mutableStateOf(false) }
        var customColorDialog by remember { mutableStateOf(false) }

        val rtsButton = controller.rtsButton.value
        var rtsButtonColor by remember { mutableStateOf(SolidColor(Color(rtsButton ?: 0))) }
        var rtsButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var rtsButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var rtsButtonBorderColor by remember { mutableStateOf(Color(rtsButton ?: 0)) }
        var rtsButtonInnerShadowColor by remember { mutableStateOf(Color(rtsButton ?: 0)) }
        var rtsButtonTextColor by remember { mutableStateOf(isDarkService.getTextColor()) }

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.touch_effect))
        val lottieAnimatable = rememberLottieAnimatable()
        val coroutineScope = rememberCoroutineScope()

        rtsButtonColor = if (rtsButton == 0) {
            SolidColor(isDarkService.getButtonColor())
        } else {
            SolidColor(Color(rtsButton ?: 0))
        }

        if (controller.isDark.value == true) {
            rtsButtonInnerShadowColor = isDarkService.getDarken(Color(rtsButton ?: 0), 0.5f)
            // 다크 모드일 때
            rtsButtonBorderWidth = 3.dp
            if (rtsButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    rtsButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.DarkMode.pressBorderColor,
                            isDarkService.getBorderColor()
                        )
                    )
                    rtsButtonBorderColor = AppColor.DarkMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    rtsButtonBrush = rtsButtonColor
                    rtsButtonBorderColor = isDarkService.getBorderColor()
                    rtsButtonTextColor = isDarkService.getTextColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    rtsButtonBrush = Brush.verticalGradient(
                        colors = listOf(Color(rtsButton ?: 0), isDarkService.getBorderColor())
                    )
                    rtsButtonBorderColor = Color(rtsButton ?: 0)
                    rtsButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    rtsButtonBrush = rtsButtonColor
                    rtsButtonBorderColor = isDarkService.getBorderColor()
                    rtsButtonTextColor = isDarkService.getButtonTextColor(Color(rtsButton ?: 0))
                }
            }
        } else {
            // 라이트 모드일 때
            if (rtsButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    rtsButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.LightMode.pressBorderColor,
                            isDarkService.getButtonColor()
                        )
                    )
                    rtsButtonBorderWidth = 3.dp
                    rtsButtonBorderColor = AppColor.LightMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    rtsButtonBrush = rtsButtonColor
                    rtsButtonBorderWidth = 1.5.dp
                    rtsButtonBorderColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
                    rtsButtonInnerShadowColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.9f)
                    rtsButtonTextColor = isDarkService.getTextColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    rtsButtonBrush =
                        SolidColor(isDarkService.getDarken(Color(rtsButton ?: 0), 0.7f))
                    rtsButtonBorderWidth = 3.dp
                    rtsButtonBorderColor = Color(rtsButton ?: 0)
                    rtsButtonInnerShadowColor = isDarkService.getDarken(Color(rtsButton ?: 0), 0.5f)
                    rtsButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    rtsButtonBrush = rtsButtonColor
                    rtsButtonBorderWidth = 1.5.dp
                    rtsButtonBorderColor = isDarkService.getDarken(Color(rtsButton ?: 0), 0.7f)
                    rtsButtonInnerShadowColor = isDarkService.getDarken(Color(rtsButton ?: 0), 0.7f)
                    rtsButtonTextColor = isDarkService.getButtonTextColor(Color(rtsButton ?: 0))
                }
            }
        }

        BoxWithConstraints {
            val size = (maxWidth.value * 0.7).dp
            val fontSize = (maxWidth.value / 5).sp

            Box(
                modifier = Modifier
                    .size(size)
                    .outerShadow(
                        color = isDarkService.getDarkOuterShadow(),
                        offsetX = ((size.value * 0.7)+10).dp,
                        offsetY = 10.dp,
                        blurRadius = 18.dp,
                    )
                    .outerShadow(
                        color = isDarkService.getLightOuterShadow(),
                        offsetX = ((size.value * 0.7)-10).dp,
                        offsetY = (-10).dp,
                        blurRadius = 18.dp,
                    )
            ) {

            }

            Box(
                modifier = Modifier
                    .size(size)
                    .offset(x = (size.value * 0.7).dp)
                    .background(
                        brush = rtsButtonBrush,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .border(
                        width = rtsButtonBorderWidth,
                        color = rtsButtonBorderColor,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .innerShadow(
                        shape = RoundedCornerShape(18.dp),
                        color = isDarkService.getLightInnerShadow(),
                        offsetX = 2.dp,
                        offsetY = 2.dp,
                        blur = 10.dp,
                        spread = 0.dp,
                    )
                    .innerShadow(
                        shape = RoundedCornerShape(18.dp),
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
                Text(
                    text = "RTS",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSize,
                        color = rtsButtonTextColor
                    ),
                )
                if (isSetting && isPressed.value) {
                    BoxWithConstraints {
                        Box(
                            modifier = Modifier
                                .wrapContentSize(unbounded = true)
                                .width(maxWidth + 5.dp)
                                .height(maxHeight + 5.dp)
                                .border(
                                    width = 2.dp,
                                    color = AppColor.CustomColor.check
                                )
                        ) {

                        }
                    }
                }
                if (isEnable && isPressed.value) {
                    BoxWithConstraints {
                        Box(
                            modifier = Modifier
                                .wrapContentSize(unbounded = true)
                                .size(maxWidth * 3)
                                .offset(y = (-maxHeight)),
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

            if (customColorDialog) {
                CustomColorDialog(
                    defaultColor = if (controller.rtsButton.value == 0) isDarkService.getButtonColor()
                        .toArgb() else controller.rtsButton.value ?: 0,
                    controller = controller,
                    onDismissRequest = { color ->
                        customColorDialog = false
                        isPressed.value = false
                        rtsButtonColor = SolidColor(color)
                        if (color.toArgb() == isDarkService.getButtonColor().toArgb()) {
                            controller.rtsButton.value = 0
                        } else {
                            controller.rtsButton.value = color.toArgb()
                        }
                    }
                )
            }

        }

    }
}