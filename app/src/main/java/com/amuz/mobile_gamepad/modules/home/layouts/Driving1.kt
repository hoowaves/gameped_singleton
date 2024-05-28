package com.amuz.mobile_gamepad.modules.home.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.amuz.mobile_gamepad.modules.widgets.joysticks.RightJoystick

class Driving1 {
    @Composable
    fun Render() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HomeController.getBackgroundColor())
        ) {
            // 상단 게이지
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .background(Color.Red)
            ) {

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
                                        val lbButton = LBButton()
                                        lbButton.Render()
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.CenterEnd
                                    ) {
                                        val rbButton = RBButton()
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
                                        val viewButton = ViewButton()
                                        viewButton.Render()
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        val portalButton = PortalButton()
                                        portalButton.Render()
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        val menuButton = MenuButton()
                                        menuButton.Render()
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        val settingButton = SettingButton()
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
                                                        val ybxaButton = YBXAButton()
                                                        ybxaButton.Render()
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
                                                        val directionButton = DirectionButton()
                                                        directionButton.Render()
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
                                                        val ltsButton = LTSButton()
                                                        ltsButton.Render()
                                                        val rtsButton = RTSButton()
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
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        val rightJoystick = RightJoystick()
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
                                val ltButton = LTButton()
                                ltButton.Render()
                            }
                            Box(
                                modifier = Modifier
                                    .weight(5f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                val rtButton = RTButton()
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