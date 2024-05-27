package com.amuz.mobile_gamepad.modules.widgets.joysticks

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.modules.home.HomeView
import com.amuz.mobile_gamepad.settings.SystemRepository
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

class LeftJoystick {

    private var size by mutableStateOf(50.dp)

    @Composable
    fun Render() {
        val context = LocalContext.current
        val systemRepository = SystemRepository(context)
        val isEnable = remember { context is HomeView }

        val density = LocalDensity.current
        val sizePx = with(density) { size.toPx() }
        val middleCircleRadiusPx = sizePx * 0.6f // 중간 원은 조이스틱 크기의 60%
        val handleRadiusPx = sizePx * 0.4f // 핸들은 조이스틱 크기의 40%

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
                            if (HomeController.getIsVibrator() == true) {
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
                        color = Color.Green,
                        center = it,
                        radius = sizePx,
                    )

                    drawCircle(
                        color = HomeController.getBackgroundColor(),
                        center = it,
                        radius = ((sizePx*0.8).toFloat()),
                    )

                    handlePosition?.let {
                        drawCircle(
                            color = Color.Gray,
                            center = it,
                            radius = middleCircleRadiusPx,
                        )

                        drawCircle(
                            color = Color.DarkGray,
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