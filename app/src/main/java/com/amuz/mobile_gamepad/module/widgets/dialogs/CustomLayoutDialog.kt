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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomControllerImpl
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.shadowCustom

@Composable
fun CustomLayoutDialog(
    controller: LayoutCustomControllerImpl,
    onDismissRequest: () -> Unit
) {
    val isDarkService = IsDarkService(controller.isDark.value ?: false)
    var isCheck by remember { mutableStateOf(controller.isDefault.value) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0f)
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp)
                .background(
                    color = isDarkService
                        .getButtonColor()
                        .copy(alpha = 0.8f),
                    shape = RoundedCornerShape(16.dp)
                )
                .blur(5.dp)
        ) {

        }
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp)
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
                        .weight(2f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Settings",
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
                        .weight(3f)
                        .padding(start = 15.dp, end = 15.dp, top = 15.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                    ) {
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
                                        .background(
                                            color = if (isCheck == true) AppColor.CustomColor.check else Color.Unspecified,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .border(
                                            width = if (isCheck == false) 1.5.dp else 0.dp,
                                            color = if (isCheck == false) isDarkService.getTextColor() else Color.Unspecified,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .clickable {
                                            if (controller.isDefault.value == true) {
                                                controller.isDefault.value = false
                                                controller.defaultLayout()
                                            } else {
                                                controller.isDefault.value = true
                                                controller.layout.value = controller.layoutId.value
                                            }
                                            isCheck = !isCheck!!
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (isCheck == true) {
                                        BoxWithConstraints {
                                            Icon(
                                                imageVector = Icons.Default.Check,
                                                contentDescription = null,
                                                tint = Color.Black,
                                                modifier = Modifier.size((maxHeight.value * 0.7).dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .weight(8f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "Set as default",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = isDarkService.getTextColor()
                                )
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(5f)
                ) {

                }
            }
        }
    }
}