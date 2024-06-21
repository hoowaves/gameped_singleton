package com.amuz.mobile_gamepad.module.widgets.commons

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import com.amuz.mobile_gamepad.constants.AppColor

class SensorProgress(context: Context, isDark: Boolean) : SensorEventListener {
    private val sensorManager by lazy {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    private val y = mutableFloatStateOf(0f)
    private val leftProgress = mutableFloatStateOf(0f)
    private val rightProgress = mutableFloatStateOf(0f)

    private val isDarkService = IsDarkService(isDark)

    init {
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME)
    }

    fun unregister() {
        sensorManager.unregisterListener(this)
    }

    @Composable
    fun LeftProgressRender() {
        LinearProgressIndicator(
            progress = animateFloatAsState(targetValue = leftProgress.floatValue).value,
            color = AppColor.CustomColor.check,
            trackColor = isDarkService.getButtonColor(),
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(rotationZ = 180f),
        )
    }

    @Composable
    fun RightProgressRender() {
        LinearProgressIndicator(
            progress = animateFloatAsState(targetValue = rightProgress.floatValue).value,
            color = AppColor.CustomColor.check,
            trackColor = isDarkService.getButtonColor(),
            modifier = Modifier.fillMaxSize(),
        )
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