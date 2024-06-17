package com.amuz.mobile_gamepad.module.widgets.dialogs.displayDialog

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.system.SystemRepository
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import kotlinx.coroutines.launch

@Composable
fun DisplayDialog(
    controller: IActivityController,
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties()
) {
    val context = LocalContext.current

    if (!Settings.System.canWrite(context)) {
        Toast
            .makeText(context, "설정 변경을 위한 권한이 필요합니다.", Toast.LENGTH_SHORT)
            .show()
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS).apply {
            data = Uri.parse("package:" + context.packageName)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
        onDismissRequest()
        return
    }

    val displayDialogController = DisplayDialogController(context)
    val systemRepository = SystemRepository(context)

    var isTouchEffectChecked by remember { mutableStateOf(false) }
    var brightnessPosition by remember { mutableFloatStateOf(0F) }
    var isVibrationChecked by remember { mutableStateOf(false) }
    var isChanged by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        displayDialogController.dataInit()
        isTouchEffectChecked = displayDialogController.touchEffect.value ?: false
        brightnessPosition = displayDialogController.getModelBrightness().toFloat()
        isVibrationChecked = displayDialogController.isVibration.value ?: false
        isChanged = displayDialogController.isChanged.value ?: false
    }

    val isDarkService = IsDarkService(controller.isDark.value ?: false)
    var isDropDownTheme by remember { mutableStateOf(false) }
    var isDropDownPowerSaving by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties.let {
            DialogProperties(
                dismissOnBackPress = it.dismissOnBackPress,
                dismissOnClickOutside = it.dismissOnClickOutside,
                securePolicy = it.securePolicy,
                usePlatformDefaultWidth = false
            )
        },
        content = {
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(250.dp)
                    .border(
                        1.5.dp,
                        color = isDarkService.getBorderColor(),
                        shape = RoundedCornerShape(16.dp)
                    ),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(isDarkService.getBackgroundColor())
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(8f)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(20.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(4f)
                                    .fillMaxHeight()
                                    .padding(end = 10.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(5f)
                                            .padding(bottom = 10.dp)
                                            .background(
                                                isDarkService.getButtonColor(),
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .weight(4f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "테마",
                                                    style = TextStyle(
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 14.sp,
                                                        color = isDarkService.getTextColor()
                                                    ),
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(start = 15.dp)
                                                )
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(2.5f)
                                                    .fillMaxHeight()
                                            ) {
                                                DropdownMenu(
                                                    modifier = Modifier
                                                        .width(140.dp)
                                                        .height(90.dp)
                                                        .background(isDarkService.getBackgroundColor()),
                                                    expanded = isDropDownTheme,
                                                    onDismissRequest = { isDropDownTheme = false }
                                                ) {
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "다크 모드",
                                                                color = isDarkService.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.isDark.value == true) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.isDark.value =
                                                                true
                                                            isDropDownTheme = false
                                                            displayDialogController.isChangedUpdate()
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                    Divider(
                                                        modifier = Modifier.padding(
                                                            start = 8.dp,
                                                            end = 8.dp
                                                        ),
                                                        color = isDarkService.getButtonColor()
                                                    )
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "라이트 모드",
                                                                color = isDarkService.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.isDark.value == false) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.isDark.value =
                                                                false
                                                            isDropDownTheme = false
                                                            displayDialogController.isChangedUpdate()
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                }
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(2f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.CenterStart
                                            ) {
                                                Text(
                                                    text = displayDialogController.isDarkText,
                                                    style = TextStyle(
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 14.sp,
                                                        color = isDarkService.getTextColor()
                                                    ),
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                )
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(1.5f)
                                                    .fillMaxHeight()
                                                    .clickable { isDropDownTheme = true },
                                                contentAlignment = Alignment.CenterStart
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.KeyboardArrowDown,
                                                    contentDescription = null,
                                                    tint = isDarkService.getTextColor(),
                                                )
                                            }
                                        }
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(5f)
                                            .padding(top = 10.dp)
                                            .background(
                                                isDarkService.getButtonColor(),
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .weight(4f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "터치 효과*",
                                                    style = TextStyle(
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 14.sp,
                                                        color = isDarkService.getTextColor()
                                                    ),
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(start = 15.dp)
                                                )
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(2.5f)
                                                    .fillMaxHeight()
                                            ) {
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(3.5f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Switch(
                                                    checked = isTouchEffectChecked,
                                                    onCheckedChange = {
                                                        isTouchEffectChecked = it
                                                        displayDialogController.touchEffect.value =
                                                            it
                                                        displayDialogController.isChangedUpdate()
                                                    },
                                                    colors = SwitchDefaults.colors(
                                                        checkedThumbColor = Color.White,
                                                        checkedTrackColor = Color.Green,
                                                        uncheckedThumbColor = Color.Gray,
                                                        uncheckedTrackColor = Color.DarkGray,
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .weight(4f)
                                    .fillMaxHeight()
                                    .padding(start = 10.dp, end = 10.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(5f)
                                            .padding(bottom = 10.dp)
                                            .background(
                                                isDarkService.getButtonColor(),
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .weight(4f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "자동 절전*",
                                                    style = TextStyle(
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 14.sp,
                                                        color = isDarkService.getTextColor()
                                                    ),
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(start = 15.dp)
                                                )
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(2.5f)
                                                    .fillMaxHeight()
                                            ) {
                                                DropdownMenu(
                                                    modifier = Modifier
                                                        .width(140.dp)
                                                        .height(150.dp)
                                                        .background(isDarkService.getBackgroundColor()),
                                                    expanded = isDropDownPowerSaving,
                                                    onDismissRequest = {
                                                        isDropDownPowerSaving = false
                                                    }
                                                ) {
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "안함",
                                                                color = isDarkService.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.powerSaving.value == 0) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.powerSaving.value =
                                                                0
                                                            isDropDownPowerSaving = false
                                                            displayDialogController.isChangedUpdate()
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                    Divider(
                                                        modifier = Modifier.padding(
                                                            start = 8.dp,
                                                            end = 8.dp
                                                        ),
                                                        color = isDarkService.getButtonColor()
                                                    )
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "1분",
                                                                color = isDarkService.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.powerSaving.value == 1) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.powerSaving.value =
                                                                1
                                                            isDropDownPowerSaving = false
                                                            displayDialogController.isChangedUpdate()
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                    Divider(
                                                        modifier = Modifier.padding(
                                                            start = 8.dp,
                                                            end = 8.dp
                                                        ),
                                                        color = isDarkService.getButtonColor()
                                                    )
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "5분",
                                                                color = isDarkService.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.powerSaving.value == 5) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.powerSaving.value =
                                                                5
                                                            isDropDownPowerSaving = false
                                                            displayDialogController.isChangedUpdate()
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                    Divider(
                                                        modifier = Modifier.padding(
                                                            start = 8.dp,
                                                            end = 8.dp
                                                        ),
                                                        color = isDarkService.getButtonColor()
                                                    )
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "15분",
                                                                color = isDarkService.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.powerSaving.value == 15) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.powerSaving.value =
                                                                15
                                                            isDropDownPowerSaving = false
                                                            displayDialogController.isChangedUpdate()
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                    Divider(
                                                        modifier = Modifier.padding(
                                                            start = 8.dp,
                                                            end = 8.dp
                                                        ),
                                                        color = isDarkService.getButtonColor()
                                                    )
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "30분",
                                                                color = isDarkService.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.powerSaving.value == 30) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.powerSaving.value =
                                                                30
                                                            isDropDownPowerSaving = false
                                                            displayDialogController.isChangedUpdate()
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                    Divider(
                                                        modifier = Modifier.padding(
                                                            start = 8.dp,
                                                            end = 8.dp
                                                        ),
                                                        color = isDarkService.getButtonColor()
                                                    )
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "60분",
                                                                color = isDarkService.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.powerSaving.value == 60) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.powerSaving.value =
                                                                60
                                                            isDropDownPowerSaving = false
                                                            displayDialogController.isChangedUpdate()
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                }
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(2f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.CenterStart
                                            ) {
                                                Text(
                                                    text = displayDialogController.powerSavingText,
                                                    style = TextStyle(
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 14.sp,
                                                        color = isDarkService.getTextColor()
                                                    ),
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                )
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(1.5f)
                                                    .fillMaxHeight()
                                                    .clickable { isDropDownPowerSaving = true },
                                                contentAlignment = Alignment.CenterStart
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.KeyboardArrowDown,
                                                    contentDescription = null,
                                                    tint = isDarkService.getTextColor(),
                                                )
                                            }
                                        }
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(5f)
                                            .padding(top = 10.dp)
                                            .background(
                                                isDarkService.getButtonColor(),
                                                shape = RoundedCornerShape(16.dp)
                                            )
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .weight(4f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "진동 설정",
                                                    style = TextStyle(
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 14.sp,
                                                        color = isDarkService.getTextColor()
                                                    ),
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(start = 15.dp)
                                                )
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(2.5f)
                                                    .fillMaxHeight()
                                            ) {
                                            }
                                            Box(
                                                modifier = Modifier
                                                    .weight(3.5f)
                                                    .fillMaxHeight(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Switch(
                                                    checked = isVibrationChecked,
                                                    onCheckedChange = {
                                                        isVibrationChecked = it
                                                        displayDialogController.isVibration.value =
                                                            it
                                                        displayDialogController.isChangedUpdate()
                                                    },
                                                    colors = SwitchDefaults.colors(
                                                        checkedThumbColor = Color.White,
                                                        checkedTrackColor = Color.Green,
                                                        uncheckedThumbColor = Color.Gray,
                                                        uncheckedTrackColor = Color.DarkGray,
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .weight(2f)
                                    .fillMaxHeight()
                                    .padding(start = 10.dp)
                                    .background(
                                        isDarkService.getButtonColor(),
                                        shape = RoundedCornerShape(16.dp)
                                    )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(3f)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "밝기",
                                                style = TextStyle(
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 14.sp,
                                                    color = isDarkService.getTextColor()
                                                )
                                            )
                                        }
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(7f)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(bottom = 10.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Slider(
                                                modifier = Modifier
                                                    .graphicsLayer {
                                                        rotationZ = 270f
                                                    },
                                                colors = SliderDefaults.colors(
                                                    thumbColor = Color.White,
                                                    activeTrackColor = Color.Green,
                                                    inactiveTrackColor = isDarkService.getBackgroundColor(),
                                                ),
                                                valueRange = 0f..255f,
                                                value = brightnessPosition,
                                                onValueChange = {
                                                    brightnessPosition = it
                                                    displayDialogController.brightness.value = it.toInt()
                                                    systemRepository.setBrightness(it.toInt())
                                                    displayDialogController.isChangedUpdate()
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Divider(
                            modifier = Modifier.padding(
                                start = 8.dp,
                                end = 8.dp
                            ),
                            color = isDarkService.getButtonColor()
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(5f)
                                    .fillMaxHeight()
                                    .clickable {
                                        systemRepository.setBrightness(displayDialogController.getModelBrightness())
                                        onDismissRequest()
                                    },
                            ) {
                                Text(
                                    text = "닫기",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = isDarkService.getTextColor()
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(5f)
                                    .fillMaxHeight()
                                    .alpha(if (displayDialogController.isChanged.value == true) 1.0f else 0.3f)
                                    .clickable(enabled = displayDialogController.isChanged.value == true) {
                                        scope.launch {
                                            displayDialogController.update()
                                            Toast
                                                .makeText(
                                                    context,
                                                    "설정이 완료되었습니다.",
                                                    Toast.LENGTH_SHORT
                                                )
                                                .show()
                                            when (controller.layout.value) {
                                                0 -> displayDialogController.routeDefaultMode()
                                                1 -> displayDialogController.routeDriving1()
                                                2 -> displayDialogController.routeDriving2()
                                                3 -> displayDialogController.routeCasual()
                                            }
                                            if (context is ComponentActivity) {
                                                context.finish()
                                                context.overridePendingTransition(0, 0)
                                            }
                                            onDismissRequest()
                                        }
                                    },
                            ) {
                                Text(
                                    text = "저장",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = isDarkService.getTextColor()
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    )


}