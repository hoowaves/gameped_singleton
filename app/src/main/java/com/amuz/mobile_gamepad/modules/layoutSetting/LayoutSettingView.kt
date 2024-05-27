package com.amuz.mobile_gamepad.modules.layoutSetting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.constants.Theme
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.modules.home.layouts.Casual
import com.amuz.mobile_gamepad.modules.home.layouts.Driving1
import com.amuz.mobile_gamepad.modules.home.layouts.Driving2
import com.amuz.mobile_gamepad.modules.home.layouts.GameController

class LayoutSettingView : ComponentActivity() {
    private val gameController = GameController()
    private val driving1 = Driving1()
    private val driving2 = Driving2()
    private val casual = Casual()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Render()
        }
    }

    @Composable
    fun Render() {
        val layoutState by HomeController.layoutState.observeAsState()
        val layoutTheme by HomeController.layoutTheme.observeAsState()

        when (layoutState) {
            "Game Controller" -> {
                gameController.Render()
            }

            "Driving 1" -> {
                driving1.Render()
            }

            "Driving 2" -> {
                driving2.Render()
            }

            "Casual" -> {
                casual.Render()
            }

            else -> {
                gameController.Render()
            }
        }

        when (layoutTheme) {
            Theme.DARK -> {
                when (layoutState) {
                    "Game Controller" -> {
                        gameController.Render()
                    }

                    "Driving 1" -> {
                        driving1.Render()
                    }

                    "Driving 2" -> {
                        driving2.Render()
                    }

                    "Casual" -> {
                        casual.Render()
                    }

                    else -> {
                        gameController.Render()
                    }
                }
            }

            Theme.LIGHT -> {
                when (layoutState) {
                    "Game Controller" -> {
                        gameController.Render()
                    }

                    "Driving 1" -> {
                        driving1.Render()
                    }

                    "Driving 2" -> {
                        driving2.Render()
                    }

                    "Casual" -> {
                        casual.Render()
                    }

                    else -> {
                        gameController.Render()
                    }
                }
            }

            null -> {
                gameController.Render()
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(4.5f)
                    ) {

                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 20.dp)
                                .background(
                                    color = AppColor.CustomColor.tap,
                                    shape = RoundedCornerShape(24.dp)
                                )
                                .border(
                                    1.5.dp,
                                    color = HomeController.getBorderColor(),
                                    shape = RoundedCornerShape(24.dp)
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxHeight()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(5f)
                                        .clickable {
                                            finish()
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = null,
                                        tint = HomeController.getTextColor(),
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(5f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = null,
                                        tint = HomeController.getTextColor(),
                                    )
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(4.5f)
                    ) {
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8.5f)
            ) {
            }
        }
    }

}