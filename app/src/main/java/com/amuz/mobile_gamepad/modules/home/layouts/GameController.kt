package com.amuz.mobile_gamepad.modules.home.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.modules.widgets.buttons.DirectionButton
import com.amuz.mobile_gamepad.modules.widgets.buttons.LBButton
import com.amuz.mobile_gamepad.modules.widgets.buttons.LTButton
import com.amuz.mobile_gamepad.modules.widgets.buttons.LTSButton
import com.amuz.mobile_gamepad.modules.widgets.buttons.MenuButton
import com.amuz.mobile_gamepad.modules.widgets.buttons.PortalButton
import com.amuz.mobile_gamepad.modules.widgets.buttons.RBButton
import com.amuz.mobile_gamepad.modules.widgets.buttons.RTButton
import com.amuz.mobile_gamepad.modules.widgets.buttons.RTSButton
import com.amuz.mobile_gamepad.modules.widgets.buttons.SettingButton
import com.amuz.mobile_gamepad.modules.widgets.buttons.ViewButton
import com.amuz.mobile_gamepad.modules.widgets.buttons.YBXAButton
import com.amuz.mobile_gamepad.modules.widgets.joysticks.LeftJoystick
import com.amuz.mobile_gamepad.modules.widgets.joysticks.RightJoystick

class GameController {

    @Composable
    fun Render() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HomeController.getBackgroundColor())
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
                                    val ltButton = LTButton()
                                    ltButton.Render()
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                        .offset(y = (+10).dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val lbButton = LBButton()
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
                            val directionButton = DirectionButton()
                            directionButton.Render()
                        }
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            val viewButton = ViewButton()
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
                                    val portalButton = PortalButton()
                                    portalButton.Render()
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val settingButton = SettingButton()
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
                            val menuButton = MenuButton()
                            menuButton.Render()
                        }
                        Box(
                            modifier = Modifier
                                .weight(2f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            val ybxaButton = YBXAButton()
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
                                    val rtButton = RTButton()
                                    rtButton.Render()
                                }
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                        .offset(y = 20.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    val rbButton = RBButton()
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
                        val leftJoystick = LeftJoystick()
                        leftJoystick.Render()
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .zIndex(1f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        val ltsButton = LTSButton()
                        ltsButton.Render()
                        val rtsButton = RTSButton()
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
                        val rightJoystick = RightJoystick()
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