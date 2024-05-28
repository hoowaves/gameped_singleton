package com.amuz.mobile_gamepad.modules.home.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import com.amuz.mobile_gamepad.modules.home.HomeController
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

class Casual {
    @Composable
    fun Render() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HomeController.getBackgroundColor())
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
                                    val ltButton = LTButton()
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
                                    val lbButton = LBButton()
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
                                    val viewButton = ViewButton()
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
                                            val portalButton = PortalButton()
                                            portalButton.Render()
                                        }
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .weight(5f),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            val settingButton = SettingButton()
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
                                    val menuButton = MenuButton()
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
                                    val rbButton = RBButton()
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
                                    val rtButton = RTButton()
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
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            val ybxaButton = YBXAButton()
                            ybxaButton.Render()
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