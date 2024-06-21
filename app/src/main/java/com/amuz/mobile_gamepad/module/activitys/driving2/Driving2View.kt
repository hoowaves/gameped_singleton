package com.amuz.mobile_gamepad.module.activitys.driving2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
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
import com.amuz.mobile_gamepad.module.widgets.commons.SensorProgress
import com.amuz.mobile_gamepad.module.widgets.joysticks.RightJoystick

class Driving2View : ComponentActivity() {
    private lateinit var controller: Driving2ControllerImpl
    private var isDataInitialized by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = Driving2ControllerImpl(this)

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
            // 상단 게이지
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    val sensorProgress =
                        SensorProgress(LocalContext.current, controller.isDark.value ?: false)
                    Box(
                        modifier = Modifier
                            .weight(5f)
                            .fillMaxHeight()
                    ) {
                        sensorProgress.LeftProgressRender()
                    }
                    Box(
                        modifier = Modifier
                            .weight(5f)
                            .fillMaxHeight()
                    ) {
                        sensorProgress.RightProgressRender()
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

            }
            // 게이지 밑
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
            ) {
                Row(
                    modifier = Modifier
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
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(6.5f)
                            ) {
                                val ltButton = LTButton(controller)
                                ltButton.Render()
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(3.5f)
                            ) {

                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(3.5f)
                            ) {
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(6.5f)
                            ) {
                                val lbButton = LBButton(controller)
                                lbButton.Render()
                            }
                        }
                    }
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
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(2.5f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.TopCenter
                            ) {
                                val viewButton = ViewButton(controller)
                                viewButton.Render()
                            }
                            Box(
                                modifier = Modifier
                                    .weight(5f)
                                    .fillMaxHeight()
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(5f)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .weight(5f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.TopCenter
                                            ) {
                                                val portalButton = PortalButton(controller)
                                                portalButton.Render()
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(5f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.TopCenter
                                            ) {
                                                val menuButton = MenuButton(controller)
                                                menuButton.Render()
                                            }
                                        }
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(5f)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .weight(2.5f)
                                                    .fillMaxHeight()
                                            ) {

                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(5f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                val ltsButton = LTSButton(controller)
                                                ltsButton.Render()
                                                val rtsButton = RTSButton(controller)
                                                rtsButton.Render()
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(2.5f)
                                                    .fillMaxHeight()
                                            ) {

                                            }
                                        }

                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .weight(2.5f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.TopCenter
                            ) {
                                val settingButton = SettingButton(controller)
                                settingButton.Render()
                            }
                        }
                    }
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
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(3.5f)
                            ) {

                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(6.5f),
                                horizontalAlignment = Alignment.End
                            ) {
                                val rbButton = RBButton(controller)
                                rbButton.Render()
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(6.5f),
                                horizontalAlignment = Alignment.End
                            ) {
                                val rtButton = RTButton(controller)
                                rtButton.Render()
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(3.5f)
                            ) {

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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {

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
                            .weight(4f)
                            .fillMaxHeight()
                    ) {
                        val rightJoystick = RightJoystick(controller)
                        rightJoystick.Render()
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

                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

            }
        }
    }
}