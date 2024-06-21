package com.amuz.mobile_gamepad.module.widgets.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.verticalScrollbar

@Composable
fun SettingDialog(onDismissRequest: () -> Unit, onItemClick: (Int) -> Unit, controller: IActivityController) {

    val isDarkService = IsDarkService(controller.isDark.value ?: false)
    val scrollState = rememberScrollState()

    Dialog(onDismissRequest = { onDismissRequest() }) {
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
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {

                    }
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Settings",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = isDarkService.getTextColor()
                            ),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = isDarkService.getTextColor(),
                        )
                    }


                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.25f)
                        .verticalScroll(scrollState)
                        .verticalScrollbar(scrollState)
                ) {
                    val options = listOf("Layouts", "Display settings", "Go LG ThinQ", "Open source license")

                    options.forEachIndexed { index, text ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .clickable { onItemClick(index) },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = text,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = isDarkService.getTextColor()
                                ),
                            )
                        }
                        if (index < options.size - 1) {
                            Divider(color = isDarkService.getButtonColor())
                        }
                    }
                }
            }
        }
    }
}