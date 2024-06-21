package com.amuz.mobile_gamepad.module.widgets.buttons

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.amuz.mobile_gamepad.module.activitys.defaultMode.DefaultModeView
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.module.activitys.layoutCustomList.LayoutCustomListView
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.innerShadow

class PortalButton(private val controller: IActivityController) {

    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }
        val isPressed = remember { mutableStateOf(false) }

        val portalButton = isDarkService.getButtonColor().toArgb()
        val portalButtonColor by remember { mutableStateOf(SolidColor(Color(portalButton))) }
        var portalButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var portalButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var portalButtonBorderColor by remember { mutableStateOf(Color(portalButton)) }
        var portalButtonTextColor by remember { mutableStateOf(isDarkService.getTextColor()) }

        if (controller.isDark.value == true) {
            // 다크 모드일 때
            portalButtonBorderWidth = 3.dp
            // 기본 버튼일 때
            if (isEnable && isPressed.value) {
                // 기본 버튼 눌렀을 때
                portalButtonBrush = Brush.verticalGradient(
                    colors = listOf(
                        AppColor.DarkMode.pressBorderColor,
                        isDarkService.getBorderColor()
                    )
                )
                portalButtonBorderColor = AppColor.DarkMode.pressBorderColor
            } else {
                // 기본 버튼 안 눌렀을 때
                portalButtonBrush = portalButtonColor
                portalButtonBorderColor = isDarkService.getBorderColor()
                portalButtonTextColor = isDarkService.getTextColor()
            }
        } else {
            // 라이트 모드일 때
            // 기본 버튼일 때
            if (isEnable && isPressed.value) {
                // 기본 버튼 눌렀을 때
                portalButtonBrush = Brush.verticalGradient(
                    colors = listOf(
                        AppColor.LightMode.pressBorderColor,
                        isDarkService.getButtonColor()
                    )
                )
                portalButtonBorderWidth = 3.dp
                portalButtonBorderColor = AppColor.LightMode.pressBorderColor
                portalButtonTextColor = isDarkService.getTextColor()
            } else {
                // 기본 버튼 안 눌렀을 때
                portalButtonBrush = portalButtonColor
                portalButtonBorderWidth = 1.5.dp
                portalButtonBorderColor =
                    isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
            }
        }

        BoxWithConstraints {
            val size = maxWidth / 2

//            Box(
//                modifier = Modifier
//                    .size(size = size + 8.dp)
//                    .offset(x = (-4).dp, y = (-4).dp)
//                    .background(
//                        Brush.radialGradient(
//                            colors = listOf(isDarkService.getButtonColor(), Color.Transparent),
//                        ),
//                        shape = RoundedCornerShape(15.dp)
//                    )
//            ) {
//
//            }
//
//            Box(
//                modifier = Modifier
//                    .size(size = size + 4.dp)
//                    .offset(x = (+2).dp, y = (+2).dp)
//                    .background(
//                        Brush.radialGradient(
//                            colors = listOf(isDarkService.getBorderColor(), Color.Transparent),
//                        ),
//                        shape = RoundedCornerShape(15.dp)
//                    )
//            ) {
//
//            }

            Box(
                modifier = Modifier
                    .size(size = size)
//                    .innerShadow(
//                        spread = 5.dp,
//                        blur = 10.dp,
//                        color = isDarkService.getBackgroundColor(),
//                        cornersRadius = 15.dp
//                    )
                    .background(
                        brush = portalButtonBrush,
                        shape = RoundedCornerShape(15.dp))
                    .border(
                        width = portalButtonBorderWidth,
                        color = portalButtonBorderColor,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .pointerInput(Unit) {
                        if (!isEnable) return@pointerInput
                        detectTapGestures(
                            onPress = {
                                isPressed.value = true
                                tryAwaitRelease()
                                isPressed.value = false
                            },
                            onTap = {
                                Toast
                                    .makeText(context, "Preparing to link to the game portal.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mapping),
                    contentDescription = "포털 버튼",
                    modifier = Modifier
                        .size(size = size / 2)
                )
            }

        }
    }
}