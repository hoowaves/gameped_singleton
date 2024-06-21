package com.amuz.mobile_gamepad.module.activitys.defaultMode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.amuz.mobile_gamepad.R
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.widgets.buttons.DirectionButton
import com.amuz.mobile_gamepad.module.widgets.buttons.LBButton
import com.amuz.mobile_gamepad.module.widgets.buttons.LTButton
import com.amuz.mobile_gamepad.module.widgets.buttons.LTSButton
import com.amuz.mobile_gamepad.module.widgets.buttons.MenuButton
import com.amuz.mobile_gamepad.module.widgets.buttons.PortalButton
import com.amuz.mobile_gamepad.module.widgets.buttons.RBButton
import com.amuz.mobile_gamepad.module.widgets.buttons.RTButton
import com.amuz.mobile_gamepad.module.widgets.buttons.RTSButton
import com.amuz.mobile_gamepad.module.widgets.buttons.SettingButton
import com.amuz.mobile_gamepad.module.widgets.buttons.ViewButton
import com.amuz.mobile_gamepad.module.widgets.buttons.YBXAButton
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.LoadingPage
import com.amuz.mobile_gamepad.module.widgets.joysticks.LeftJoystick
import com.amuz.mobile_gamepad.module.widgets.joysticks.RightJoystick

class DefaultModeView : ComponentActivity() {
    private lateinit var controller: DefaultModeControllerImpl
    private var isDataInitialized by mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = DefaultModeControllerImpl(this)

        setContent {
            LaunchedEffect(Unit) {
                controller.dataInit()
                isDataInitialized = true
            }

            LoadingPage()

            AnimatedVisibility(
                visible = isDataInitialized,
                enter = fadeIn(animationSpec = tween(durationMillis = 500))
            ) {
                Render(controller)
            }

        }
    }

    @Composable
    fun Render(controller: IActivityController) {
        val isDarkService = IsDarkService(controller.isDark.value ?: false)


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(isDarkService.getBackgroundColor())
        ) {
            // 위쪽
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4.5f)
                    .padding(top = 20.dp)
                    .zIndex(1f)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(0.5f)
                                .fillMaxHeight()
                        ) {

                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val ltButton = LTButton(controller)
                                    ltButton.Render()
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                        .offset(y = (+10).dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val lbButton = LBButton(controller)
                                    lbButton.Render()
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            val directionButton = DirectionButton(controller)
                            directionButton.Render()
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            val viewButton = ViewButton(controller)
                            viewButton.Render()
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val portalButton = PortalButton(controller)
                                    portalButton.Render()
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val settingButton = SettingButton(controller)
                                    settingButton.Render()
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            val menuButton = MenuButton(controller)
                            menuButton.Render()
                        }
                        Box(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            val ybxaButton = YBXAButton(controller)
                            ybxaButton.Render()
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                        .offset(y = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val rtButton = RTButton(controller)
                                    rtButton.Render()
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                        .offset(y = 20.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val rbButton = RBButton(controller)
                                    rbButton.Render()
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(0.5f)
                                .fillMaxHeight()
                        ) {

                        }
                    }
                }

            }

            // 아래쪽
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(5f)
            ) {
                Row(modifier = Modifier.fillMaxHeight()) {
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                    ) {
                    }
                    Box(
                        modifier = Modifier
                            .weight(4f)
                            .fillMaxHeight()
                    ) {
                        val leftJoystick = LeftJoystick(controller)
                        leftJoystick.Render()
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .zIndex(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        val ltsButton = LTSButton(controller)
                        ltsButton.Render()
                        val rtsButton = RTSButton(controller)
                        rtsButton.Render()
                    }
                    Box(
                        modifier = Modifier
                            .weight(4f)
                            .fillMaxHeight()
                            .background(
                                Color.Unspecified,
                                shape = CutCornerShape(topEnd = 50.dp, bottomStart = 50.dp)
                            )
                    ) {
                        val rightJoystick = RightJoystick(controller)
                        rightJoystick.Render()
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                    ) {
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            ) {

            }
        }
    }
}