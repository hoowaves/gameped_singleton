package com.amuz.mobile_gamepad.module.widgets.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.amuz.mobile_gamepad.R
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.module.activitys.layoutCustomList.LayoutCustomListView
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.innerShadow
import com.amuz.mobile_gamepad.module.widgets.commons.shadowCustom

class ViewButton(private val controller: IActivityController) {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }

        val isPressed = remember { mutableStateOf(false) }

        val viewButton = isDarkService.getButtonColor().toArgb()
        val viewButtonColor by remember { mutableStateOf(SolidColor(Color(viewButton))) }
        var viewButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var viewButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var viewButtonBorderColor by remember { mutableStateOf(Color(viewButton)) }
        var viewButtonTextColor by remember { mutableStateOf(isDarkService.getTextColor()) }

        if (controller.isDark.value == true) {
            // 다크 모드일 때
            viewButtonBorderWidth = 3.dp
            // 기본 버튼일 때
            if (isEnable && isPressed.value) {
                // 기본 버튼 눌렀을 때
                viewButtonBrush = Brush.verticalGradient(
                    colors = listOf(
                        AppColor.DarkMode.pressBorderColor,
                        isDarkService.getBorderColor()
                    )
                )
                viewButtonBorderColor = AppColor.DarkMode.pressBorderColor
            } else {
                // 기본 버튼 안 눌렀을 때
                viewButtonBrush = viewButtonColor
                viewButtonBorderColor = isDarkService.getBorderColor()
                viewButtonTextColor = isDarkService.getTextColor()
            }
        } else {
            // 라이트 모드일 때
            // 기본 버튼일 때
            if (isEnable && isPressed.value) {
                // 기본 버튼 눌렀을 때
                viewButtonBrush = Brush.verticalGradient(
                    colors = listOf(
                        AppColor.LightMode.pressBorderColor,
                        isDarkService.getButtonColor()
                    )
                )
                viewButtonBorderWidth = 3.dp
                viewButtonBorderColor = AppColor.LightMode.pressBorderColor
                viewButtonTextColor = isDarkService.getTextColor()
            } else {
                // 기본 버튼 안 눌렀을 때
                viewButtonBrush = viewButtonColor
                viewButtonBorderWidth = 1.5.dp
                viewButtonBorderColor =
                    isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
            }
        }

        BoxWithConstraints {
            val size = (maxWidth.value / 1.7).dp

            Box(
                modifier = Modifier
                    .size(size)
                    .shadowCustom(
                        color = isDarkService.getDarkShadow(),
                        offsetX = 10.dp,
                        offsetY = 10.dp,
                        blurRadius = 15.dp,
                    )
                    .shadowCustom(
                        color = isDarkService.getLightShadow(),
                        offsetX = (-10).dp,
                        offsetY = (-10).dp,
                        blurRadius = 15.dp,
                    )
            ) {

            }

            Box(
                modifier = Modifier
                    .size(size = size)
                    .background(
                        brush = viewButtonBrush,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .border(
                        width = viewButtonBorderWidth,
                        color = viewButtonBorderColor,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .pointerInput(Unit) {
                        if (!isEnable) return@pointerInput
                        detectTapGestures(
                            onPress = {
                                isPressed.value = true
                                tryAwaitRelease()
                                isPressed.value = false
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = if (controller.isDark.value == true) painterResource(id = R.drawable.view_default) else painterResource(id = R.drawable.view_light),
                    contentDescription = "보기 버튼",
                    modifier = Modifier
                        .size(size = size / 2)
                )
            }

        }
    }
}