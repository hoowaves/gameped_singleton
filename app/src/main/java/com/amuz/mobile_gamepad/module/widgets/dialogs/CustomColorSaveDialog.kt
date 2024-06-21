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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomControllerImpl
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService

@Composable
fun CustomColorSave(onDismissRequest: (isSave: Boolean) -> Unit, controller: LayoutCustomControllerImpl) {
    val isDarkService = IsDarkService(controller.isDark.value ?: false)
    val isSave by remember { mutableStateOf(false) }
    Dialog(onDismissRequest = { onDismissRequest(isSave) }) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0f)
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(180.dp)
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
                        .weight(7.5f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(isDarkService.getBackgroundColor())
                        , contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Save Changes?",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = isDarkService.getTextColor()
                            )
                        )
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
                        .weight(2.5f)
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
                                    onDismissRequest(false)
                                },
                        ) {
                            Text(
                                text = "Cancel",
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
                                .clickable {
                                    onDismissRequest(true)
                                },
                        ) {
                            Text(
                                text = "Save",
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
}