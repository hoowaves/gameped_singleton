package com.amuz.mobile_gamepad.module.activitys.driving1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.amuz.mobile_gamepad.R
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.widgets.buttons.AButton
import com.amuz.mobile_gamepad.module.widgets.buttons.BButton
import com.amuz.mobile_gamepad.module.widgets.buttons.CenterButton
import com.amuz.mobile_gamepad.module.widgets.buttons.DirectionButton
import com.amuz.mobile_gamepad.module.widgets.buttons.DownButton
import com.amuz.mobile_gamepad.module.widgets.buttons.LBButton
import com.amuz.mobile_gamepad.module.widgets.buttons.LTButton
import com.amuz.mobile_gamepad.module.widgets.buttons.LTSButton
import com.amuz.mobile_gamepad.module.widgets.buttons.LeftButton
import com.amuz.mobile_gamepad.module.widgets.buttons.MenuButton
import com.amuz.mobile_gamepad.module.widgets.buttons.PortalButton
import com.amuz.mobile_gamepad.module.widgets.buttons.RBButton
import com.amuz.mobile_gamepad.module.widgets.buttons.RTButton
import com.amuz.mobile_gamepad.module.widgets.buttons.RTSButton
import com.amuz.mobile_gamepad.module.widgets.buttons.RightButton
import com.amuz.mobile_gamepad.module.widgets.buttons.SettingButton
import com.amuz.mobile_gamepad.module.widgets.buttons.UpButton
import com.amuz.mobile_gamepad.module.widgets.buttons.ViewButton
import com.amuz.mobile_gamepad.module.widgets.buttons.XButton
import com.amuz.mobile_gamepad.module.widgets.buttons.YButton
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.LoadingPage
import com.amuz.mobile_gamepad.module.widgets.commons.SensorProgress
import com.amuz.mobile_gamepad.module.widgets.commons.shadowCustom
import com.amuz.mobile_gamepad.module.widgets.joysticks.RightJoystick

class Driving1View : ComponentActivity() {
    private lateinit var controller: Driving1ControllerImpl
    private var isDataInitialized by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = Driving1ControllerImpl(this)

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

    override fun onDestroy() {
        super.onDestroy()
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
                    .weight(0.5f)
            ) {

            }
            // 게이지 밑
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8.5f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(8f)
                            .fillMaxHeight()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2.5f)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        val lbButton = LBButton(controller)
                                        lbButton.Render()
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        val rbButton = RBButton(controller)
                                        rbButton.Render()
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(2f)
                                            .fillMaxHeight()
                                    ) {
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        val viewButton = ViewButton(controller)
                                        viewButton.Render()
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        val portalButton = PortalButton(controller)
                                        portalButton.Render()
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        val menuButton = MenuButton(controller)
                                        menuButton.Render()
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        val settingButton = SettingButton(controller)
                                        settingButton.Render()
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(7.5f)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .weight(6f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
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
                                                    .weight(6f)
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
                                                            .weight(4f)
                                                            .fillMaxHeight(),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        Box(
                                                            modifier = Modifier
                                                                .fillMaxSize()
                                                                .graphicsLayer {
                                                                    rotationZ = 45f
                                                                },
                                                            contentAlignment = Alignment.Center
                                                        ) {

                                                            BoxWithConstraints {
                                                                val size = (maxHeight.value * 0.8).dp
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
                                                            .weight(1f)
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
                                                            val size = (maxHeight.value)
                                                            Box(
                                                                modifier = Modifier
                                                                    .size(size.dp)
                                                                    .shadowCustom(
                                                                        color = isDarkService.getDarkShadow(),
                                                                        offsetX = 10.dp,
                                                                        offsetY = 10.dp,
                                                                        blurRadius = 10.dp,
                                                                        shapeRadius = 100.dp,
                                                                    )
                                                                    .shadowCustom(
                                                                        color = isDarkService.getLightShadow(),
                                                                        offsetX = (-10).dp,
                                                                        offsetY = (-10).dp,
                                                                        blurRadius = 10.dp,
                                                                        shapeRadius = 100.dp,
                                                                    )
                                                            ) {
                                                            }
                                                            Box(
                                                                modifier = Modifier
                                                                    .size(size.dp)
                                                                    .graphicsLayer {
                                                                        rotationZ = 45f
                                                                    },
                                                                contentAlignment = Alignment.Center
                                                            ) {
                                                                Column(
                                                                    modifier = Modifier
                                                                        .fillMaxSize()
                                                                        .border(
                                                                            1.5.dp,
                                                                            color = isDarkService.getBorderColor(),
                                                                            shape = RoundedCornerShape((size / 2).dp)
                                                                        )
                                                                ) {
                                                                    Row(modifier = Modifier.weight(1f)) {
                                                                        Box(modifier = Modifier.weight(5f)) {
                                                                            val upButton = UpButton(controller)
                                                                            upButton.Render()
                                                                        }
                                                                        Box(modifier = Modifier.weight(5f)) {
                                                                            val rightButton = RightButton(controller)
                                                                            rightButton.Render()
                                                                        }
                                                                    }
                                                                    Row(modifier = Modifier.weight(1f)) {
                                                                        Box(modifier = Modifier.weight(5f)) {
                                                                            val leftButton = LeftButton(controller)
                                                                            leftButton.Render()
                                                                        }
                                                                        Box(modifier = Modifier.weight(5f)) {
                                                                            val downButton = DownButton(controller)
                                                                            downButton.Render()
                                                                        }
                                                                    }
                                                                }
                                                                val centerButton = CenterButton(controller)
                                                                centerButton.Render()
                                                            }
                                                        }

                                                    }
                                                }
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
                                                    Box(
                                                        modifier = Modifier
                                                            .weight(4f)
                                                            .fillMaxHeight()
                                                    ) {

                                                    }
                                                    Box(
                                                        modifier = Modifier
                                                            .weight(2f)
                                                            .fillMaxHeight()
                                                            .zIndex(1f),
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
                                                    ) {

                                                    }
                                                }
                                            }
                                        }
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
                                                    .size(size = (maxHeight.value * 0.7).dp)
                                            )
                                        }
                                        val rightJoystick = RightJoystick(controller)
                                        rightJoystick.Render()
                                    }
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(2f)
                            .fillMaxHeight()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(5f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                val ltButton = LTButton(controller)
                                ltButton.Render()
                            }
                            Box(
                                modifier = Modifier
                                    .weight(5f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                val rtButton = RTButton(controller)
                                rtButton.Render()
                            }
                        }
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