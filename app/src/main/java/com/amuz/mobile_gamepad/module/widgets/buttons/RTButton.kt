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
import com.amuz.mobile_gamepad.module.widgets.commons.shadowCustom
import kotlinx.coroutines.launch

class RTButton(private val controller: IActivityController) {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val systemRepository = SystemRepository(context)

        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }

        val isSetting = remember { context is LayoutCustomView }
        val isPressed = remember { mutableStateOf(false) }
        var customColorDialog by remember { mutableStateOf(false) }

        val rtButton = controller.rtButton.value
        var rtButtonColor by remember { mutableStateOf(SolidColor(Color(rtButton ?: 0))) }
        var rtButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var rtButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var rtButtonBorderColor by remember { mutableStateOf(Color(rtButton ?: 0)) }
        var rtButtonInnerShadowColor by remember { mutableStateOf(Color(rtButton ?: 0)) }
        var rtButtonTextColor by remember { mutableStateOf(isDarkService.getTextColor()) }

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.touch_effect))
        val lottieAnimatable = rememberLottieAnimatable()
        val coroutineScope = rememberCoroutineScope()

        rtButtonColor = if (rtButton == 0) {
            SolidColor(isDarkService.getButtonColor())
        } else {
            SolidColor(Color(rtButton ?: 0))
        }

        if (controller.isDark.value == true) {
            rtButtonInnerShadowColor = isDarkService.getDarken(Color(rtButton ?: 0), 0.5f)
            // 다크 모드일 때
            rtButtonBorderWidth = 3.dp
            if (rtButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    rtButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.DarkMode.pressBorderColor,
                            isDarkService.getBorderColor()
                        )
                    )
                    rtButtonBorderColor = AppColor.DarkMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    rtButtonBrush = rtButtonColor
                    rtButtonBorderColor = isDarkService.getBorderColor()
                    rtButtonTextColor = isDarkService.getTextColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    rtButtonBrush = Brush.verticalGradient(
                        colors = listOf(Color(rtButton ?: 0), isDarkService.getBorderColor())
                    )
                    rtButtonBorderColor = Color(rtButton ?: 0)
                    rtButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    rtButtonBrush = rtButtonColor
                    rtButtonBorderColor = isDarkService.getBorderColor()
                    rtButtonTextColor = isDarkService.getButtonTextColor(Color(rtButton ?: 0))
                }
            }
        } else {
            // 라이트 모드일 때
            if (rtButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    rtButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.LightMode.pressBorderColor,
                            isDarkService.getButtonColor()
                        )
                    )
                    rtButtonBorderWidth = 3.dp
                    rtButtonBorderColor = AppColor.LightMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    rtButtonBrush = rtButtonColor
                    rtButtonBorderWidth = 1.5.dp
                    rtButtonBorderColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
                    rtButtonInnerShadowColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.9f)
                    rtButtonTextColor = isDarkService.getTextColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    rtButtonBrush = SolidColor(isDarkService.getDarken(Color(rtButton ?: 0), 0.7f))
                    rtButtonBorderWidth = 3.dp
                    rtButtonBorderColor = Color(rtButton ?: 0)
                    rtButtonInnerShadowColor = isDarkService.getDarken(Color(rtButton ?: 0), 0.5f)
                    rtButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    rtButtonBrush = rtButtonColor
                    rtButtonBorderWidth = 1.5.dp
                    rtButtonBorderColor = isDarkService.getDarken(Color(rtButton ?: 0), 0.7f)
                    rtButtonInnerShadowColor = isDarkService.getDarken(Color(rtButton ?: 0), 0.7f)
                    rtButtonTextColor = isDarkService.getButtonTextColor(Color(rtButton ?: 0))
                }
            }
        }

        BoxWithConstraints {
            val fontSize = (maxWidth.value / 4).sp

            Box(
                modifier = Modifier
                    .width((maxWidth.value * 0.7).dp)
                    .height((maxHeight.value).dp)
                    .shadowCustom(
                        color = isDarkService.getDarkShadow(),
                        offsetX = 10.dp,
                        offsetY = 10.dp,
                        blurRadius = 18.dp,
                    )
                    .shadowCustom(
                        color = isDarkService.getLightShadow(),
                        offsetX = (-10).dp,
                        offsetY = (-10).dp,
                        blurRadius = 18.dp,
                    )
            ) {

            }

            Box(
                modifier = Modifier
                    .width((maxWidth.value * 0.7).dp)
                    .height(maxHeight.value.dp)
                    .background(
                        brush = rtButtonBrush,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .border(
                        width = rtButtonBorderWidth,
                        color = rtButtonBorderColor,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .innerShadow(
                        spread = 1.dp,
                        blur = 20.dp,
                        color = rtButtonInnerShadowColor,
                        cornersRadius = 18.dp
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
                    text = "RT",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSize,
                        color = rtButtonTextColor
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
                                .offset(y = (-maxHeight / 2)),
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
                    defaultColor = if (controller.rtButton.value == 0) isDarkService.getButtonColor()
                        .toArgb() else controller.rtButton.value ?: 0,
                    controller = controller,
                    onDismissRequest = { color ->
                        customColorDialog = false
                        isPressed.value = false
                        rtButtonColor = SolidColor(color)
                        if (color.toArgb() == isDarkService.getButtonColor().toArgb()) {
                            controller.rtButton.value = 0
                        } else {
                            controller.rtButton.value = color.toArgb()
                        }
                    }
                )
            }

        }

    }
}