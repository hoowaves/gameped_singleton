package com.amuz.mobile_gamepad.module.activitys.casual

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.amuz.mobile_gamepad.R
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.widgets.buttons.AButton
import com.amuz.mobile_gamepad.module.widgets.buttons.BButton
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
import com.amuz.mobile_gamepad.module.widgets.buttons.XButton
import com.amuz.mobile_gamepad.module.widgets.buttons.YButton
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.LoadingPage
import com.amuz.mobile_gamepad.module.widgets.commons.shadowCustom
import com.amuz.mobile_gamepad.module.widgets.joysticks.LeftJoystick

class CasualView : ComponentActivity() {
    private lateinit var controller: CasualControllerImpl
    private var isDataInitialized by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = CasualControllerImpl(this)

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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
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
                                        .weight(1.25f)
                                        .fillMaxHeight()
                                ) {

                                }
                                Box(
                                    modifier = Modifier
                                        .weight(2.5f)
                                        .fillMaxHeight(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val viewButton = ViewButton(controller)
                                    viewButton.Render()
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(2.5f)
                                        .fillMaxHeight()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(5f),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            val portalButton = PortalButton(controller)
                                            portalButton.Render()
                                        }
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(5f),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            val settingButton = SettingButton(controller)
                                            settingButton.Render()
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(2.5f)
                                        .fillMaxHeight(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val menuButton = MenuButton(controller)
                                    menuButton.Render()
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1.25f)
                                        .fillMaxHeight()
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
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(5.5f)
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
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            BoxWithConstraints {
                                Image(
                                    painter = painterResource(id = R.drawable.joystick),
                                    contentDescription = "조이스틱",
                                    modifier = Modifier
                                        .size(size = (maxHeight.value * 0.9).dp)
                                )
                            }
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
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            BoxWithConstraints {
                                val size = (maxHeight.value * 0.8).dp
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .graphicsLayer {
                                            rotationZ = 45f
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(size)
                                            .shadowCustom(
                                                color = isDarkService.getDarkShadow(),
                                                offsetX = 10.dp,
                                                offsetY = 0.dp,
                                                blurRadius = 10.dp,
                                                shapeRadius = 20.dp,
                                            )
                                            .shadowCustom(
                                                color = isDarkService.getLightShadow(),
                                                offsetX = (-10).dp,
                                                offsetY = 0.dp,
                                                blurRadius = 10.dp,
                                                shapeRadius = 20.dp,
                                            )
                                    ) {
                                    }
                                    Column(
                                        modifier = Modifier
                                            .size(size)
                                    ) {
                                        Row(modifier = Modifier.weight(1f)) {
                                            Box(modifier = Modifier.weight(5f)) {
                                                val yButton = YButton(controller)
                                                yButton.Render()
                                            }
                                            Box(modifier = Modifier.weight(5f)) {
                                                val bButton = BButton(controller)
                                                bButton.Render()
                                            }
                                        }
                                        Row(modifier = Modifier.weight(1f)) {
                                            Box(modifier = Modifier.weight(5f)) {
                                                val xButton = XButton(controller)
                                                xButton.Render()
                                            }
                                            Box(modifier = Modifier.weight(5f)) {
                                                val aButton = AButton(controller)
                                                aButton.Render()
                                            }
                                        }
                                    }
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {

            }
        }
    }
}