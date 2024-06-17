package com.amuz.mobile_gamepad.module.activitys.driving2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
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
import com.amuz.mobile_gamepad.module.widgets.joysticks.RightJoystick

class Driving2View : ComponentActivity(), SensorEventListener {
    private lateinit var controller: Driving2ControllerImpl
    private var isDataInitialized by mutableStateOf(false)

    private val y = mutableFloatStateOf(0f)
    private val leftProgress = mutableFloatStateOf(0f)
    private val rightProgress = mutableFloatStateOf(0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = Driving2ControllerImpl(this)

        setContent {
            LaunchedEffect(Unit) {
                controller.dataInit()
                isDataInitialized = true
            }
            if (isDataInitialized) {
                Render(controller)
            }
        }
    }

    @Composable
    fun Render(controller: IActivityController) {
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val sensorManager =
            LocalContext.current.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        accelerometer.also { accel ->
            sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL)
        }

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
                    Box(
                        modifier = Modifier
                            .weight(5f)
                            .fillMaxHeight()
                    ) {
                        LinearProgressIndicator(
                            progress = animateFloatAsState(targetValue = leftProgress.floatValue).value,
                            color = Color.Green,
                            trackColor = isDarkService.getButtonColor(),
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer(rotationZ = 180f),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(5f)
                            .fillMaxHeight()
                    ) {
                        LinearProgressIndicator(
                            progress = animateFloatAsState(targetValue = rightProgress.floatValue).value,
                            color = Color.Green,
                            trackColor = isDarkService.getButtonColor(),
                            modifier = Modifier.fillMaxSize(),
                        )
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

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            y.floatValue = it.values[1]
            if (y.floatValue > 0) {
                rightProgress.floatValue = normalize(y.floatValue, 0f, 9.81f)
                leftProgress.floatValue = 0f
            } else {
                leftProgress.floatValue = 1 - normalize(y.floatValue, -9.81f, 0f)
                rightProgress.floatValue = 0f
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    private fun normalize(value: Float, minVal: Float, maxVal: Float): Float {
        return (value - minVal) / (maxVal - minVal)
    }

}