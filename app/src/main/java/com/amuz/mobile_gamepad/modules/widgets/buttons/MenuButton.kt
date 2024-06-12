package com.amuz.mobile_gamepad.modules.widgets.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.R
import com.amuz.mobile_gamepad.modules.home.HomeView
import com.amuz.mobile_gamepad.modules.widgets.dialogs.ConnectDialog
import com.amuz.mobile_gamepad.settings.app.AppSettingModel

class MenuButton {
    @Composable
    fun Render() {
        val context = LocalContext.current
        val isEnable = remember { context is HomeView }

        BoxWithConstraints {
            val isPressed = remember { mutableStateOf(false) }
            var showDialog by remember { mutableStateOf(false) }
            val gradientColor = remember { mutableStateOf(Color.White) }
            val gradientBrush = Brush.linearGradient(
                colors = listOf(gradientColor.value, Color.Transparent),
                start = Offset(0f, 0f),
                end = Offset(maxWidth.value, maxHeight.value)
            )
            val defaultBrush = SolidColor(AppSettingModel.getButtonColor())
            val brush = if (isPressed.value) gradientBrush else defaultBrush
            val size = maxWidth / 2

            Box(
                modifier = Modifier
                    .size(size = size)
                    .innerShadow(
                        spread = 5.dp,
                        blur = 10.dp,
                        color = AppSettingModel.getBackgroundColor(),
                        cornersRadius = 15.dp
                    )
                    .background(brush = brush, shape = CircleShape)
                    .border(1.5.dp, AppSettingModel.getBorderColor(), CircleShape)
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
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = "메뉴 버튼",
                    modifier = Modifier
                        .size(size = size / 2)
                )
            }
            if (showDialog) {
                ConnectDialog(
                    onDismissRequest = { showDialog = false },
                )
            }
        }
    }
}