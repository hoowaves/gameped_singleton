package com.amuz.mobile_gamepad.module.widgets.dialogs

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.activitys.casual.CasualView
import com.amuz.mobile_gamepad.module.activitys.defaultMode.DefaultModeView
import com.amuz.mobile_gamepad.module.activitys.driving1.Driving1View
import com.amuz.mobile_gamepad.module.activitys.driving2.Driving2View
import com.amuz.mobile_gamepad.module.activitys.layoutCustomList.LayoutCustomListView
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.verticalScrollbar

@Composable
fun LayoutListDialog(onDismissRequest: () -> Unit, controller: IActivityController) {

    val context = LocalContext.current
    val isDarkService = IsDarkService(controller.isDark.value ?: false)
    var selectedItem by remember { mutableStateOf<Int?>(null) }
    val scrollState = rememberScrollState()

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(230.dp)
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "레이아웃",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = isDarkService.getTextColor()
                            ),
                        )
                    }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(start = 10.dp, end = 10.dp)
                        .background(isDarkService.getBackgroundColor())
                        .verticalScroll(scrollState)
                        .verticalScrollbar(scrollState)
                ) {
                    val layouts = listOf("Game Controller", "Driving 1", "Driving 2", "Casual")

                    layouts.forEachIndexed { index, text ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedItem = index
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (controller.name.value == text) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.Green,
                                )
                            }
                            Text(
                                text = text,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = isDarkService.getTextColor()
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                textAlign = TextAlign.Start
                            )
                        }
                        if (index < layouts.size - 1) {
                            Divider(color = isDarkService.getButtonColor())
                        }
                    }
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
                        .clickable {
                            context.startActivity(Intent(context, LayoutCustomListView::class.java))
                            (context as Activity).finish()
                            context.overridePendingTransition(0, 0)
                        },
                ) {
                    Text(
                        text = "설정 >",
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

    selectedItem?.let { index ->
        when (index) {
            0 -> context.startActivity(Intent(context, DefaultModeView::class.java))
            1 -> context.startActivity(Intent(context, Driving1View::class.java))
            2 -> context.startActivity(Intent(context, Driving2View::class.java))
            3 -> context.startActivity(Intent(context, CasualView::class.java))
            else -> context.startActivity(Intent(context, DefaultModeView::class.java))
        }
        if (context is ComponentActivity) {
            context.finish()
            context.overridePendingTransition(0, 0)
        }
        onDismissRequest()
    }

}