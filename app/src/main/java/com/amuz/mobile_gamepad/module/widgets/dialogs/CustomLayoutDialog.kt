package com.amuz.mobile_gamepad.module.widgets.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomControllerImpl
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService

@Composable
fun CustomLayoutDialog(
    controller: LayoutCustomControllerImpl,
    onDismissRequest: () -> Unit
) {
    val isDarkService = IsDarkService(controller.isDark.value ?: false)
    var isCheck by remember { mutableStateOf(controller.isDefault.value) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0f)
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp)
                .border(
                    1.5.dp,
                    color = isDarkService.getBorderColor(),
                    shape = RoundedCornerShape(16.dp)
                ),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(isDarkService.getBackgroundColor())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(isDarkService.getBackgroundColor()),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "설정",
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
                        .weight(4f)
                        .padding(start = 15.dp, end = 15.dp, top = 15.dp)
                ) {
                    Text(
                        text = "레이아웃",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = isDarkService.getTextColor()
                        )
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 5.dp)
                            .border(
                                1.dp,
                                color = isDarkService.getTextColor(),
                            ),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(5f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "기본으로 설정하기",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = isDarkService.getTextColor()
                                    )
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(3f)
                                    .fillMaxHeight()
                            ) {

                            }
                            Box(
                                modifier = Modifier
                                    .weight(2f)
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                BoxWithConstraints {
                                    Box(
                                        modifier = Modifier
                                            .size(maxHeight / 2)
                                            .border(
                                                1.dp,
                                                color = isDarkService.getTextColor(),
                                            )
                                            .background(if (isCheck == true) Color.Green else isDarkService.getButtonColor())
                                            .clickable {
                                                controller.isDefault.value =
                                                    controller.isDefault.value != true
                                                controller.layout.value = controller.layoutId.value
                                                isCheck = !isCheck!!
                                            },
                                    ) {
                                        if (isCheck == true) {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(4f)
                ) {

                }
            }
        }
    }
}