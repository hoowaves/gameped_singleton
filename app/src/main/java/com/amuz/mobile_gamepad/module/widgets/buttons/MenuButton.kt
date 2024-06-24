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
import com.amuz.mobile_gamepad.module.widgets.dialogs.ConnectDialog
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService

class MenuButton(private val controller: IActivityController) {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }

        val menuButton = isDarkService.getButtonColor().toArgb()
        val menuButtonColor by remember { mutableStateOf(SolidColor(Color(menuButton))) }
        var menuButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var menuButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var menuButtonBorderColor by remember { mutableStateOf(Color(menuButton)) }
        var menuButtonTextColor by remember { mutableStateOf(isDarkService.getTextColor()) }

        val isPressed = remember { mutableStateOf(false) }

        if (controller.isDark.value == true) {
            // 다크 모드일 때
            menuButtonBorderWidth = 3.dp
            // 기본 버튼일 때
            if (isEnable && isPressed.value) {
                // 기본 버튼 눌렀을 때
                menuButtonBrush = Brush.verticalGradient(
                    colors = listOf(
                        AppColor.DarkMode.pressBorderColor,
                        isDarkService.getBorderColor()
                    )
                )
                menuButtonBorderColor = AppColor.DarkMode.pressBorderColor
            } else {
                // 기본 버튼 안 눌렀을 때
                menuButtonBrush = menuButtonColor
                menuButtonBorderColor = isDarkService.getBorderColor()
                menuButtonTextColor = isDarkService.getTextColor()
            }
        } else {
            // 라이트 모드일 때
            // 기본 버튼일 때
            if (isEnable && isPressed.value) {
                // 기본 버튼 눌렀을 때
                menuButtonBrush = Brush.verticalGradient(
                    colors = listOf(
                        AppColor.LightMode.pressBorderColor,
                        isDarkService.getButtonColor()
                    )
                )
                menuButtonBorderWidth = 3.dp
                menuButtonBorderColor = AppColor.LightMode.pressBorderColor
                menuButtonTextColor = isDarkService.getTextColor()
            } else {
                // 기본 버튼 안 눌렀을 때
                menuButtonBrush = menuButtonColor
                menuButtonBorderWidth = 1.5.dp
                menuButtonBorderColor =
                    isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
            }
        }

        BoxWithConstraints {

            var showDialog by remember { mutableStateOf(false) }
            val size = maxWidth / 2

            Box(
                modifier = Modifier
                    .size(size = size)
                    .background(
                        brush = menuButtonBrush,
                        shape = RoundedCornerShape(15.dp))
                    .border(
                        width = menuButtonBorderWidth,
                        color = menuButtonBorderColor,
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
                                showDialog = true
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = if (controller.isDark.value == true) painterResource(id = R.drawable.menu_default) else painterResource(id = R.drawable.menu_light),
                    contentDescription = "메뉴 버튼",
                    modifier = Modifier
                        .size(size = size / 2)
                )
            }
            if (showDialog) {
                ConnectDialog(
                    controller = controller,
                    onDismissRequest = { showDialog = false },
                )
            }
        }
    }
}