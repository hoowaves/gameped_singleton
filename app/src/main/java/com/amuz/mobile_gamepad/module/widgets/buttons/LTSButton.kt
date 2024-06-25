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

class LTSButton(private val controller: IActivityController) {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val systemRepository = SystemRepository(context)

        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }

        val isSetting = remember { context is LayoutCustomView }
        val isPressed = remember { mutableStateOf(false) }
        var customColorDialog by remember { mutableStateOf(false) }

        val ltsButton = controller.ltsButton.value
        var ltsButtonColor by remember { mutableStateOf(SolidColor(Color(ltsButton ?: 0))) }
        var ltsButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var ltsButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var ltsButtonBorderColor by remember { mutableStateOf(Color(ltsButton ?: 0)) }
        var ltsButtonInnerShadowColor by remember { mutableStateOf(Color(ltsButton ?: 0)) }
        var ltsButtonTextColor by remember { mutableStateOf(isDarkService.getTextColor()) }

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.touch_effect))
        val lottieAnimatable = rememberLottieAnimatable()
        val coroutineScope = rememberCoroutineScope()

        ltsButtonColor = if (ltsButton == 0) {
            SolidColor(isDarkService.getButtonColor())
        } else {
            SolidColor(Color(ltsButton ?: 0))
        }

        if (controller.isDark.value == true) {
            ltsButtonInnerShadowColor = isDarkService.getDarken(Color(ltsButton ?: 0), 0.5f)
            // 다크 모드일 때
            ltsButtonBorderWidth = 3.dp
            if (ltsButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    ltsButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.DarkMode.pressBorderColor,
                            isDarkService.getBorderColor()
                        )
                    )
                    ltsButtonBorderColor = AppColor.DarkMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    ltsButtonBrush = ltsButtonColor
                    ltsButtonBorderColor = isDarkService.getBorderColor()
                    ltsButtonTextColor = isDarkService.getTextColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    ltsButtonBrush = Brush.verticalGradient(
                        colors = listOf(Color(ltsButton ?: 0), isDarkService.getBorderColor())
                    )
                    ltsButtonBorderColor = Color(ltsButton ?: 0)
                    ltsButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    ltsButtonBrush = ltsButtonColor
                    ltsButtonBorderColor = isDarkService.getBorderColor()
                    ltsButtonTextColor = isDarkService.getButtonTextColor(Color(ltsButton ?: 0))
                }
            }
        } else {
            // 라이트 모드일 때
            if (ltsButton == 0) {
                // 기본 버튼일 때
                if (isEnable && isPressed.value) {
                    // 기본 버튼 눌렀을 때
                    ltsButtonBrush = Brush.verticalGradient(
                        colors = listOf(
                            AppColor.LightMode.pressBorderColor,
                            isDarkService.getButtonColor()
                        )
                    )
                    ltsButtonBorderWidth = 3.dp
                    ltsButtonBorderColor = AppColor.LightMode.pressBorderColor
                } else {
                    // 기본 버튼 안 눌렀을 때
                    ltsButtonBrush = ltsButtonColor
                    ltsButtonBorderWidth = 1.5.dp
                    ltsButtonBorderColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
                    ltsButtonInnerShadowColor =
                        isDarkService.getDarken(isDarkService.getButtonColor(), 0.9f)
                    ltsButtonTextColor = isDarkService.getTextColor()
                }
            } else {
                // 커스텀 버튼일 때
                if (isEnable && isPressed.value) {
                    // 커스텀 버튼 눌렀을 때
                    ltsButtonBrush = SolidColor(isDarkService.getDarken(Color(ltsButton ?: 0), 0.7f))
                    ltsButtonBorderWidth = 3.dp
                    ltsButtonBorderColor = Color(ltsButton ?: 0)
                    ltsButtonInnerShadowColor = isDarkService.getDarken(Color(ltsButton ?: 0), 0.5f)
                    ltsButtonTextColor = isDarkService.getTextColor()
                } else {
                    // 커스텀 버튼 안 눌렀을 때
                    ltsButtonBrush = ltsButtonColor
                    ltsButtonBorderWidth = 1.5.dp
                    ltsButtonBorderColor = isDarkService.getDarken(Color(ltsButton ?: 0), 0.7f)
                    ltsButtonInnerShadowColor = isDarkService.getDarken(Color(ltsButton ?: 0), 0.7f)
                    ltsButtonTextColor = isDarkService.getButtonTextColor(Color(ltsButton ?: 0))
                }
            }
        }

        BoxWithConstraints {
            val size = (maxWidth.value * 0.7).dp
            val fontSize = (maxWidth.value / 5).sp

            Box(
                modifier = Modifier
                    .size(size)
                    .shadowCustom(
                        color = isDarkService.getDarkShadow(),
                        offsetX = ((-size.value * 0.7)+10).dp,
                        offsetY = 10.dp,
                        blurRadius = 18.dp,
                    )
                    .shadowCustom(
                        color = isDarkService.getLightShadow(),
                        offsetX = ((-size.value * 0.7)-10).dp,
                        offsetY = (-10).dp,
                        blurRadius = 18.dp,
                    )
            ) {

            }

            Box(
                modifier = Modifier
                    .size(size)
                    .offset(x = (-size.value * 0.7).dp)
                    .background(
                        brush = ltsButtonBrush,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .border(
                        width = ltsButtonBorderWidth,
                        color = ltsButtonBorderColor,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .innerShadow(
                        spread = 1.dp,
                        blur = 20.dp,
                        color = ltsButtonInnerShadowColor,
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
                    text = "LTS",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = fontSize,
                        color = ltsButtonTextColor
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
                    defaultColor = if (controller.ltsButton.value == 0) isDarkService.getButtonColor()
                        .toArgb() else controller.ltsButton.value ?: 0,
                    controller = controller,
                    onDismissRequest = { color ->
                        customColorDialog = false
                        isPressed.value = false
                        ltsButtonColor = SolidColor(color)
                        if (color.toArgb() == isDarkService.getButtonColor().toArgb()) {
                            controller.ltsButton.value = 0
                        } else {
                            controller.ltsButton.value = color.toArgb()
                        }
                    }
                )
            }

        }

    }
}