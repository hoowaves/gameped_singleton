package com.amuz.mobile_gamepad.module.activitys.driving1

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
import androidx.compose.ui.zIndex
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

class Driving1View : ComponentActivity(), SensorEventListener {
    private lateinit var controller: Driving1ControllerImpl
    private var isDataInitialized by mutableStateOf(false)

    private val y = mutableFloatStateOf(0f)
    private val leftProgress = mutableFloatStateOf(0f)
    private val rightProgress = mutableFloatStateOf(0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = Driving1ControllerImpl(this)

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
                                                        val ybxaButton = YBXAButton(controller)
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
                                                        val directionButton =
                                                            DirectionButton(controller)
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
                                        contentAlignment = Alignment.CenterStart
                                    ) {
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