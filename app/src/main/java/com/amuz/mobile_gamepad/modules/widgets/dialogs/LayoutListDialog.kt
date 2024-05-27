package com.amuz.mobile_gamepad.modules.widgets.dialogs

import android.content.Intent
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
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.modules.layoutSettingList.LayoutSettingListView

@Composable
fun LayoutListDialog(onDismissRequest: () -> Unit) {

    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf<Int?>(null) }
    val scrollState = rememberScrollState()

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(230.dp)
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
                                color = HomeController.getTextColor()
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
                        .background(HomeController.getBackgroundColor())
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
                            if (HomeController.layoutState.value == text) {
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
                                    color = HomeController.getTextColor()
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                textAlign = TextAlign.Start
                            )
                        }
                        if (index < layouts.size - 1) {
                            Divider(color = HomeController.getButtonColor())
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(HomeController.getBackgroundColor())
                        .padding(top = 10.dp)
                ) {
                    Divider(color = HomeController.getButtonColor())
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(HomeController.getBackgroundColor())
                        .clickable {
                            val intent = Intent(context, LayoutSettingListView::class.java)
                            context.startActivity(intent)
                        },
                ) {
                    Text(
                        text = "설정 >",
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

    selectedItem?.let { index ->
        when (index) {
            1 -> {
                HomeController.setLayoutState("Driving 1")
            }

            2 -> {
                HomeController.setLayoutState("Driving 2")
            }

            3 -> {
                HomeController.setLayoutState("Casual")
            }

            else -> {
                HomeController.setLayoutState("Game Controller")
            }
        }
        onDismissRequest()
    }

}