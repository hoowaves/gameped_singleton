package com.amuz.mobile_gamepad.module.widgets.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.verticalScrollbar
@Composable
fun LicenseDialog(onDismissRequest: () -> Unit, controller: IActivityController) {

    val isDarkService = IsDarkService(controller.isDark.value ?: false)
    val scrollState = rememberScrollState()

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(1.1f)
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
                    .fillMaxWidth()
                    .background(isDarkService.getBackgroundColor())
                    .padding(10.dp)
            ) {
                Text(
                    text = "오픈소스 라이선스",
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f)
                    .background(isDarkService.getBackgroundColor())
                    .verticalScroll(scrollState)
                    .verticalScrollbar(scrollState)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = isDarkService.getTextColor()
                    ),
                    text = "Licensed under the Apache License, Verseion 2.0 (the “License”); you may not use this file except in compliance withe the License. You may obtain a copy of the License at\n" +
                            "\n" +
                            "http://www.apache.org/licenses/LICENSE-2.0",
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(isDarkService.getBackgroundColor())
                    .padding(top = 10.dp)
            ) {
                Divider(color = isDarkService.getButtonColor())
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(isDarkService.getBackgroundColor())
                    .clickable { onDismissRequest() },
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
        }
    }
}