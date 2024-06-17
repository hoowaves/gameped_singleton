package com.amuz.mobile_gamepad.module.widgets.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amuz.mobile_gamepad.R
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.module.activitys.layoutCustomList.LayoutCustomListView
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.innerShadow

class ViewButton(private val controller: IActivityController) {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }

        BoxWithConstraints {
            val isPressed = remember { mutableStateOf(false) }
            val gradientColor = remember { mutableStateOf(Color.White) }
            val gradientBrush = Brush.linearGradient(
                colors = listOf(gradientColor.value, Color.Transparent),
                start = Offset(0f, 0f),
                end = Offset(maxWidth.value, maxHeight.value)
            )
            val defaultBrush = SolidColor(isDarkService.getButtonColor())
            val brush = if (isPressed.value) gradientBrush else defaultBrush
            val size = maxWidth / 2

            Box(
                modifier = Modifier
                    .size(size = size + 4.dp)
                    .offset(x = (-4).dp, y = (-4).dp)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(isDarkService.getButtonColor(), Color.Transparent),
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {

            }

            Box(
                modifier = Modifier
                    .size(size = size + 4.dp)
                    .offset(x = (+4).dp, y = (+4).dp)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(isDarkService.getBorderColor(), Color.Transparent),
                        ),
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {

            }

            Box(
                modifier = Modifier
                    .size(size = size + 4.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                isDarkService.getBackgroundColor(),
                                Color.Transparent
                            ),
                            start = Offset(0f, 0f),
                            end = Offset((size * 2).value, (size * 2).value)
                        ),
                        shape = RoundedCornerShape(15.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(size = size)
                        .innerShadow(
                            spread = 5.dp,
                            blur = 10.dp,
                            color = isDarkService.getBackgroundColor(),
                            cornersRadius = 15.dp
                        )
                        .background(brush = brush, shape = RoundedCornerShape(15.dp))
                        .border(
                            width = 1.5.dp,
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    isDarkService.getBorderColor(),
                                    Color.Transparent
                                ),
                                start = Offset((size * 2).value, (size * 2).value),
                                end = Offset(0f, 0f)
                            ),
//                            AppSettingModel.getBorderColor(),
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
                        painter = painterResource(id = R.drawable.view),
                        contentDescription = "보기 버튼",
                        modifier = androidx.compose.ui.Modifier
                            .size(size = size / 2)
                    )
                }
            }


        }
    }
}