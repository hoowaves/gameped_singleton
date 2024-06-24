package com.amuz.mobile_gamepad.module.widgets.buttons

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.draw.shadow
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
import com.amuz.mobile_gamepad.module.widgets.dialogs.displayDialog.DisplayDialog
import com.amuz.mobile_gamepad.module.widgets.dialogs.LayoutListDialog
import com.amuz.mobile_gamepad.module.widgets.dialogs.LicenseDialog
import com.amuz.mobile_gamepad.module.widgets.dialogs.SettingDialog
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.innerShadow

class SettingButton(private val controller: IActivityController) {

    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }
        val isPressed = remember { mutableStateOf(false) }

        var showDialog by remember { mutableStateOf(false) }
        var selectedItem by remember { mutableStateOf<Int?>(null) }

        val settingButton = isDarkService.getButtonColor().toArgb()
        val settingButtonColor by remember { mutableStateOf(SolidColor(Color(settingButton))) }
        var settingButtonBrush by remember { mutableStateOf<Brush>(SolidColor(isDarkService.getButtonColor())) }
        var settingButtonBorderWidth by remember { mutableStateOf(Dp.Unspecified) }
        var settingButtonBorderColor by remember { mutableStateOf(Color(settingButton)) }
        var settingButtonTextColor by remember { mutableStateOf(isDarkService.getTextColor()) }

        if (controller.isDark.value == true) {
            // 다크 모드일 때
            settingButtonBorderWidth = 3.dp
            // 기본 버튼일 때
            if (isEnable && isPressed.value) {
                // 기본 버튼 눌렀을 때
                settingButtonBrush = Brush.verticalGradient(
                    colors = listOf(
                        AppColor.DarkMode.pressBorderColor,
                        isDarkService.getBorderColor()
                    )
                )
                settingButtonBorderColor = AppColor.DarkMode.pressBorderColor
            } else {
                // 기본 버튼 안 눌렀을 때
                settingButtonBrush = settingButtonColor
                settingButtonBorderColor = isDarkService.getBorderColor()
                settingButtonTextColor = isDarkService.getTextColor()
            }
        } else {
            // 라이트 모드일 때
            // 기본 버튼일 때
            if (isEnable && isPressed.value) {
                // 기본 버튼 눌렀을 때
                settingButtonBrush = Brush.verticalGradient(
                    colors = listOf(
                        AppColor.LightMode.pressBorderColor,
                        isDarkService.getButtonColor()
                    )
                )
                settingButtonBorderWidth = 3.dp
                settingButtonBorderColor = AppColor.LightMode.pressBorderColor
                settingButtonTextColor = isDarkService.getTextColor()
            } else {
                // 기본 버튼 안 눌렀을 때
                settingButtonBrush = settingButtonColor
                settingButtonBorderWidth = 1.5.dp
                settingButtonBorderColor =
                    isDarkService.getDarken(isDarkService.getButtonColor(), 0.7f)
            }
        }

        BoxWithConstraints {
            val size = maxWidth / 2
//            Box(
//                modifier = Modifier
//                    .size(size = size + 2.dp)
//                    .offset(x = (+2).dp, y = (+2).dp)
//                    .shadow(elevation = 10.dp,
//                        shape = RoundedCornerShape(15.dp),
//                        ambientColor = Color.Black)
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
//                    .neu(
//                        lightShadowColor = Color(0xFF2d2d2d),
//                        darkShadowColor = Color(0xFF080808),
//                        shadowElevation = 6.dp,
//                        lightSource = LightSource.LEFT_TOP,
//                        shape = Flat(RoundedCorner(15.dp))
//                    )
                    .background(
                        brush = settingButtonBrush,
                        shape = RoundedCornerShape(15.dp))
                    .border(
                        width = settingButtonBorderWidth,
                        color = settingButtonBorderColor,
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
                    painter = if (controller.isDark.value == true) painterResource(id = R.drawable.setting_default) else painterResource(id = R.drawable.setting_light),
                    contentDescription = "설정 버튼",
                    modifier = Modifier
                        .size(size = size / 2)
                )
            }

            if (showDialog) {
                SettingDialog(
                    controller = controller,
                    onDismissRequest = { showDialog = false },
                    onItemClick = { index ->
                        selectedItem = index
                        showDialog = false
                    }
                )
            }

            selectedItem?.let { index ->
                when (index) {
                    0 -> {
                        LayoutListDialog(
                            controller = controller,
                            onDismissRequest = { selectedItem = null })
                    }

                    1 -> {
                        DisplayDialog(
                            iActivityController = controller,
                            onDismissRequest = { selectedItem = null })
                    }

                    2 -> {
                        val appPackageName = "com.lgeha.nuts"
                        val appIntent =
                            context.packageManager.getLaunchIntentForPackage(appPackageName)
                        try {
                            if (appIntent != null) {
                                context.startActivity(appIntent)
                            } else {
                                throw ActivityNotFoundException()
                            }
                        } catch (e: ActivityNotFoundException) {
                            val playStoreIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                            )
                            context.startActivity(playStoreIntent)
                        }
                        selectedItem = null
                    }

                    3 -> {
                        LicenseDialog(
                            controller = controller,
                            onDismissRequest = { selectedItem = null })
                    }
                }
            }
        }
    }
}