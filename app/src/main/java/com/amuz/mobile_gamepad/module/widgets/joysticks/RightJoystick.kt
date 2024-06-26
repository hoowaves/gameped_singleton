package com.amuz.mobile_gamepad.module.widgets.joysticks

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.module.activitys.layoutCustomList.LayoutCustomListView
import com.amuz.mobile_gamepad.module.system.SystemRepository
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class RightJoystick(private val controller: IActivityController) {
    private var size by mutableStateOf(50.dp)

    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val systemRepository = SystemRepository(context)
        val isEnable = remember { context !is LayoutCustomView && context !is LayoutCustomListView }

        val density = LocalDensity.current
        val sizePx = with(density) { size.toPx() }
        val middleCircleRadiusPx = sizePx * 0.65f // 중간 원은 조이스틱 크기의 65%
        val handleRadiusPx = sizePx * 0.45f // 핸들은 조이스틱 크기의 45%

        var touchPosition by remember { mutableStateOf<Offset?>(null) }
        var handlePosition by remember { mutableStateOf<Offset?>(null) }
        var middlePosition by remember { mutableStateOf<Offset?>(null) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Unspecified,
                    shape = CutCornerShape(topStart = 50.dp, bottomEnd = 50.dp)
                )
                .pointerInput(Unit) {
                    if(!isEnable) return@pointerInput
                    detectDragGestures(
                        onDragStart = { offset ->
                            touchPosition = offset // 터치 위치 설정
                            handlePosition = offset // 핸들 위치를 터치한 지점으로
                            middlePosition = offset // 핸들 위치를 터치한 지점으로
                            if (controller.isVibration.value == true) {
                                systemRepository.setVibration()
                            }
                        },
                        onDragEnd = {
                            // 드래그가 끝나면 핸들 위치를 초기 중심으로 리셋
                            touchPosition = null
                            handlePosition = null
                            middlePosition = null
                        })
                    { change, _ ->
                        touchPosition?.let { touchPos ->
                            val newPosition = change.position
                            val angle =
                                atan2(newPosition.y - touchPos.y, newPosition.x - touchPos.x)
                            val handleDistance = min(newPosition.distanceTo(touchPos), sizePx)
                            handlePosition = Offset(
                                x = touchPos.x + cos(angle) * handleDistance,
                                y = touchPos.y + sin(angle) * handleDistance
                            )
                            // 터치한 곳 기준 x,y값을 -100 ~ 100까지 셋팅
                            val relativeChange = change.position - touchPos
                            val limitedX = relativeChange.x.coerceIn(-100f, 100f)
                            val limitedY = relativeChange.y.coerceIn(-100f, 100f)
                            Log.d(
                                "joystick : ",
                                "x : ${limitedX}, y : ${limitedY}"
                            )
                        }
                    }
                }
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                touchPosition?.let {
                    drawCircle(
                        color = AppColor.CustomColor.check,
                        center = it,
                        radius = sizePx,
                    )

                    drawCircle(
                        color = isDarkService.getBackgroundColor(),
                        center = it,
                        radius = ((sizePx*0.85).toFloat()),
                    )

                    handlePosition?.let {
                        val middleCircleBrush =
                            if (controller.isDark.value == true) {
                                Brush.verticalGradient(
                                    colors = listOf(Color(0xFF3b3b3b), Color(0xFF2c2c2c)),
                                    startY = it.y - 50f,
                                    endY = it.y + 100f,
                                )
                            } else {
                                Brush.verticalGradient(
                                    colors = listOf(Color(0xFFb8bdc8), Color(0xFF6e747e)),
                                    startY = it.y - 50f,
                                    endY = it.y + 100f,
                                )
                            }

                        val handleCircleBrush =
                            if (controller.isDark.value == true) {
                                Brush.verticalGradient(
                                    colors = listOf(Color(0xFF2b2b2b), Color(0xFFa1a1a1)),
                                    startY = it.y - 50f,
                                    endY = it.y + 200f,
                                )
                            } else {
                                Brush.verticalGradient(
                                    colors = listOf(Color(0xFF88919e), Color(0xFFffffff)),
                                    startY = it.y - 50f,
                                    endY = it.y + 200f,
                                )
                            }

                        drawCircle(
                            brush = middleCircleBrush,
                            center = it,
                            radius = middleCircleRadiusPx,
                        )

                        drawCircle(
                            brush = handleCircleBrush,
                            center = it,
                            radius = handleRadiusPx,
                        )
                    }
                }
            }
        }
    }

    private fun Offset.distanceTo(other: Offset): Float =
        sqrt((x - other.x).pow(2) + (y - other.y).pow(2))
}