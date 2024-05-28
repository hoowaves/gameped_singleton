package com.amuz.mobile_gamepad.modules.widgets.dialogs

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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.modules.home.HomeController

@Composable
fun CustomColorDialog(onDismissRequest: (color: Color) -> Unit) {

    Dialog(onDismissRequest = { onDismissRequest(HomeController.getButtonColor()) }) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0f)
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(160.dp)
                .border(
                    1.5.dp,
                    color = HomeController.getBorderColor(),
                    shape = RoundedCornerShape(16.dp)
                ),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HomeController.getBackgroundColor())
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
                            text = "색상",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = HomeController.getTextColor()
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
                                .background(HomeController.getButtonColor())
                                .border(
                                    1.dp,
                                    color = HomeController.getBorderColor()
                                )
                                .clickable {
                                    onDismissRequest(HomeController.getButtonColor())
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
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
                                .background(AppColor.CustomColor.skyBlue)
                                .border(
                                    1.dp,
                                    color = HomeController.getBorderColor()
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.skyBlue)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
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
                                .background(AppColor.CustomColor.red)
                                .border(
                                    1.dp,
                                    color = HomeController.getBorderColor()
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.red)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
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
                                .background(AppColor.CustomColor.pink)
                                .border(
                                    1.dp,
                                    color = HomeController.getBorderColor()
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.pink)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
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
                                .background(AppColor.CustomColor.ash)
                                .border(
                                    1.dp,
                                    color = HomeController.getBorderColor()
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.ash)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
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
                                .background(AppColor.CustomColor.redPink)
                                .border(
                                    1.dp,
                                    color = HomeController.getBorderColor()
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.redPink)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
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
                                .background(AppColor.CustomColor.yellow)
                                .border(
                                    1.dp,
                                    color = HomeController.getBorderColor()
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.yellow)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
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
                                .background(AppColor.CustomColor.orange)
                                .border(
                                    1.dp,
                                    color = HomeController.getBorderColor()
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.orange)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
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
                                .background(AppColor.CustomColor.lightGreen)
                                .border(
                                    1.dp,
                                    color = HomeController.getBorderColor()
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.lightGreen)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
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
                                .background(AppColor.CustomColor.green)
                                .border(
                                    1.dp,
                                    color = HomeController.getBorderColor()
                                )
                                .clickable {
                                    onDismissRequest(AppColor.CustomColor.green)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White
                            )
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