package com.amuz.mobile_gamepad.modules.widgets.buttons

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.amuz.mobile_gamepad.R
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.modules.home.HomeView

class PortalButton {

    @Composable
    fun Render() {
        val context = LocalContext.current
        val isEnable = remember { context is HomeView }

        BoxWithConstraints {
            val isPressed = remember { mutableStateOf(false) }
            val gradientColor = remember { mutableStateOf(Color.White) }
            val gradientBrush = Brush.linearGradient(
                colors = listOf(gradientColor.value, Color.Transparent),
                start = Offset(0f, 0f),
                end = Offset(maxWidth.value, maxHeight.value)
            )
            val defaultBrush = SolidColor(HomeController.getButtonColor())
            val brush = if (isPressed.value) gradientBrush else defaultBrush

            Image(
                painter = painterResource(id = R.drawable.mapping),
                contentDescription = "포털 버튼",
                modifier = Modifier
                    .size(size = maxWidth / 2)
                    .clip(CircleShape)
                    .background(brush)
                    .border(1.5.dp, HomeController.getBorderColor(), CircleShape)
                    .padding(PaddingValues(10.dp))
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
                                    .makeText(context, "게임 포털 연동 준비 중입니다.", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        )
                    }
            )
        }
    }
}