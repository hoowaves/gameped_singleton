package com.amuz.mobile_gamepad.module.widgets.dialogs

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService

@Composable
fun CustomColorDialog(
    onDismissRequest: (color: Color) -> Unit,
    controller: IActivityController,
    defaultColor: Int
) {
    val isDarkService = IsDarkService(controller.isDark.value ?: false)

    Dialog(onDismissRequest = { onDismissRequest(Color(defaultColor)) }) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0f)
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(160.dp)
                .background(
                    color = isDarkService
                        .getButtonColor()
                        .copy(alpha = 0.8f),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    1.5.dp,
                    color = isDarkService.getBorderColor(),
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2.5f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Colors",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = isDarkService.getTextColor()
                            ),
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2.25f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(0.9f)
                                .fillMaxHeight()
                        ) {

                        }
                        Box(
                            modifier = Modifier
                                .weight(1.25f)
                                .fillMaxHeight()
                                .background(
                                    color = isDarkService.getButtonColor(),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    1.dp,
                                    color = isDarkService.getBorderColor(),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onDismissRequest(isDarkService.getButtonColor())
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (defaultColor == isDarkService.getButtonColor().toArgb()) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White
                                )
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
                                .weight(1.25f)
                                .fillMaxHeight()
                                .background(
                                    color = AppColor.CustomColor.orange,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    1.dp,
                                    color = isDarkService.getBorderColor(),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.orange)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (Color(defaultColor) == AppColor.CustomColor.orange) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White
                                )
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
                                .weight(1.25f)
                                .fillMaxHeight()
                                .background(
                                    color = AppColor.CustomColor.strawberry,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    1.dp,
                                    color = isDarkService.getBorderColor(),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.strawberry)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (Color(defaultColor) == AppColor.CustomColor.strawberry) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White
                                )
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
                                .weight(1.25f)
                                .fillMaxHeight()
                                .background(
                                    color = AppColor.CustomColor.lemon,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    1.dp,
                                    color = isDarkService.getBorderColor(),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.lemon)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (Color(defaultColor) == AppColor.CustomColor.lemon) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White
                                )
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
                                .weight(1.25f)
                                .fillMaxHeight()
                                .background(
                                    color = AppColor.CustomColor.magenta,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    1.dp,
                                    color = isDarkService.getBorderColor(),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.magenta)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (Color(defaultColor) == AppColor.CustomColor.magenta) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(0.9f)
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2.25f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(0.9f)
                                .fillMaxHeight()
                        ) {

                        }
                        Box(
                            modifier = Modifier
                                .weight(1.25f)
                                .fillMaxHeight()
                                .background(
                                    color = AppColor.CustomColor.ultramarineBlue,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    1.dp,
                                    color = isDarkService.getBorderColor(),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.ultramarineBlue)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (Color(defaultColor) == AppColor.CustomColor.ultramarineBlue) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White
                                )
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
                                .weight(1.25f)
                                .fillMaxHeight()
                                .background(
                                    color = AppColor.CustomColor.cyan,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    1.dp,
                                    color = isDarkService.getBorderColor(),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.cyan)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (Color(defaultColor) == AppColor.CustomColor.cyan) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White
                                )
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
                                .weight(1.25f)
                                .fillMaxHeight()
                                .background(
                                    color = AppColor.CustomColor.violet,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    1.dp,
                                    color = isDarkService.getBorderColor(),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.violet)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (Color(defaultColor) == AppColor.CustomColor.violet) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White
                                )
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
                                .weight(1.25f)
                                .fillMaxHeight()
                                .background(
                                    color = AppColor.CustomColor.lime,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    1.dp,
                                    color = isDarkService.getBorderColor(),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.lime)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (Color(defaultColor) == AppColor.CustomColor.lime) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White
                                )
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
                                .weight(1.25f)
                                .fillMaxHeight()
                                .background(
                                    color = AppColor.CustomColor.realRed,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    1.dp,
                                    color = isDarkService.getBorderColor(),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.realRed)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (Color(defaultColor) == AppColor.CustomColor.realRed) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(0.9f)
                        ) {

                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f)
                ) {

                }
            }
        }
    }
}