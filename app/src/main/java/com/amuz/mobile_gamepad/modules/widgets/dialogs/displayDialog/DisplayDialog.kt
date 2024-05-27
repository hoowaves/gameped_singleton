package com.amuz.mobile_gamepad.modules.widgets.dialogs.displayDialog

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.amuz.mobile_gamepad.constants.Theme
import com.amuz.mobile_gamepad.modules.home.HomeController

@Composable
fun DisplayDialog(
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

    var isDropDownTheme by remember { mutableStateOf(false) }
    var isDropDownPowerSaving by remember { mutableStateOf(false) }
    var isTouchEffectChecked by remember { mutableStateOf(displayDialogController.getModelTouchEffect()) }
    var isVibrationChecked by remember { mutableStateOf(displayDialogController.getModelVibration()) }
    var brightnessPosition by remember {
        mutableFloatStateOf(
            displayDialogController.getModelBrightness()
                .toFloat()
        )
    }

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
                        color = HomeController.getBorderColor(),
                        shape = RoundedCornerShape(16.dp)
                    ),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(HomeController.getBackgroundColor())
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
                                                HomeController.getButtonColor(),
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
                                                        color = HomeController.getTextColor()
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
                                                        .background(HomeController.getBackgroundColor()),
                                                    expanded = isDropDownTheme,
                                                    onDismissRequest = { isDropDownTheme = false }
                                                ) {
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "다크 모드",
                                                                color = HomeController.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.getModelTheme() == Theme.DARK) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.setViewTheme(
                                                                Theme.DARK
                                                            )
                                                            isDropDownTheme = false
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                    Divider(
                                                        modifier = Modifier.padding(
                                                            start = 8.dp,
                                                            end = 8.dp
                                                        ),
                                                        color = HomeController.getButtonColor()
                                                    )
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "라이트 모드",
                                                                color = HomeController.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.getModelTheme() == Theme.LIGHT) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.setViewTheme(
                                                                Theme.LIGHT
                                                            )
                                                            isDropDownTheme = false
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
                                                    text = displayDialogController.viewThemeText,
                                                    style = TextStyle(
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 14.sp,
                                                        color = HomeController.getTextColor()
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
                                                    tint = HomeController.getTextColor(),
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
                                                HomeController.getButtonColor(),
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
                                                        color = HomeController.getTextColor()
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
                                                        displayDialogController.setViewTouchEffect(
                                                            it
                                                        )
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
                                                HomeController.getButtonColor(),
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
                                                        color = HomeController.getTextColor()
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
                                                        .background(HomeController.getBackgroundColor()),
                                                    expanded = isDropDownPowerSaving,
                                                    onDismissRequest = {
                                                        isDropDownPowerSaving = false
                                                    }
                                                ) {
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "안함",
                                                                color = HomeController.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.getModelPowerSaving() == 0) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.setViewPowerSaving(
                                                                0
                                                            )
                                                            isDropDownPowerSaving = false
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                    Divider(
                                                        modifier = Modifier.padding(
                                                            start = 8.dp,
                                                            end = 8.dp
                                                        ),
                                                        color = HomeController.getButtonColor()
                                                    )
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "1분",
                                                                color = HomeController.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.getModelPowerSaving() == 1) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.setViewPowerSaving(
                                                                1
                                                            )
                                                            isDropDownPowerSaving = false
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                    Divider(
                                                        modifier = Modifier.padding(
                                                            start = 8.dp,
                                                            end = 8.dp
                                                        ),
                                                        color = HomeController.getButtonColor()
                                                    )
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "5분",
                                                                color = HomeController.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.getModelPowerSaving() == 5) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.setViewPowerSaving(
                                                                5
                                                            )
                                                            isDropDownPowerSaving = false
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                    Divider(
                                                        modifier = Modifier.padding(
                                                            start = 8.dp,
                                                            end = 8.dp
                                                        ),
                                                        color = HomeController.getButtonColor()
                                                    )
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "15분",
                                                                color = HomeController.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.getModelPowerSaving() == 15) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.setViewPowerSaving(
                                                                15
                                                            )
                                                            isDropDownPowerSaving = false
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                    Divider(
                                                        modifier = Modifier.padding(
                                                            start = 8.dp,
                                                            end = 8.dp
                                                        ),
                                                        color = HomeController.getButtonColor()
                                                    )
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "30분",
                                                                color = HomeController.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.getModelPowerSaving() == 30) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.setViewPowerSaving(
                                                                30
                                                            )
                                                            isDropDownPowerSaving = false
                                                        },
                                                        modifier = Modifier
                                                            .height(40.dp)
                                                    )
                                                    Divider(
                                                        modifier = Modifier.padding(
                                                            start = 8.dp,
                                                            end = 8.dp
                                                        ),
                                                        color = HomeController.getButtonColor()
                                                    )
                                                    DropdownMenuItem(
                                                        text = {
                                                            Text(
                                                                text = "60분",
                                                                color = HomeController.getTextColor()
                                                            )
                                                        },
                                                        leadingIcon = {
                                                            if (displayDialogController.getModelPowerSaving() == 60) {
                                                                Icon(
                                                                    imageVector = Icons.Default.Check,
                                                                    contentDescription = null,
                                                                    tint = Color.Green
                                                                )
                                                            }
                                                        },
                                                        onClick = {
                                                            displayDialogController.setViewPowerSaving(
                                                                60
                                                            )
                                                            isDropDownPowerSaving = false
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
                                                    text = displayDialogController.viewPowerSavingText,
                                                    style = TextStyle(
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 14.sp,
                                                        color = HomeController.getTextColor()
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
                                                    tint = HomeController.getTextColor(),
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
                                                HomeController.getButtonColor(),
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
                                                        color = HomeController.getTextColor()
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
                                                        displayDialogController.setViewVibration(it)
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
                                        HomeController.getButtonColor(),
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
                                                    color = HomeController.getTextColor()
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
                                                    inactiveTrackColor = HomeController.getBackgroundColor(),
                                                ),
                                                valueRange = 0f..255f,
                                                value = brightnessPosition,
                                                onValueChange = {
                                                    brightnessPosition = it
                                                    displayDialogController.setModelBrightness(it.toInt())
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
                            color = HomeController.getButtonColor()
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
                                        displayDialogController.setDefaultBrightness()
                                        onDismissRequest()
                                    },
                            ) {
                                Text(
                                    text = "닫기",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = HomeController.getTextColor()
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
                                        displayDialogController.update()
                                        Toast
                                            .makeText(context, "설정이 완료되었습니다.", Toast.LENGTH_SHORT)
                                            .show()
                                        onDismissRequest()
                                    },
                            ) {
                                Text(
                                    text = "저장",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 14.sp,
                                        color = HomeController.getTextColor()
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