package com.amuz.mobile_gamepad.module.widgets.dialogs.guideDialog

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.activitys.casual.CasualView
import com.amuz.mobile_gamepad.module.activitys.defaultMode.DefaultModeView
import com.amuz.mobile_gamepad.module.activitys.driving1.Driving1View
import com.amuz.mobile_gamepad.module.activitys.driving2.Driving2View
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService

@Composable
fun GuideDialog(onDismissRequest: () -> Unit, controller: IActivityController) {
    val context = LocalContext.current
    val isDarkService = IsDarkService(controller.isDark.value ?: false)
    controller.isGuide.value = true
    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { onDismissRequest() }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            onDismissRequest()
                        }
                    )
                }
        ) {
            when (controller.layoutId.value) {
                0 -> {
                    val defaultModeView = DefaultModeView()
                    defaultModeView.Render(controller)
                }

                1 -> {
                    val driving1View = Driving1View()
                    driving1View.Render(controller)
                }

                2 -> {
                    val driving2View = Driving2View()
                    driving2View.Render(controller)
                }

                3 -> {
                    val casualView = CasualView()
                    casualView.Render(controller)
                }
            }
        }
    }
}