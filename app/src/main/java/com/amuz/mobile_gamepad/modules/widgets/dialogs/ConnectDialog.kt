package com.amuz.mobile_gamepad.modules.widgets.dialogs

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.amuz.mobile_gamepad.modules.network.DiscoveryListener
import com.amuz.mobile_gamepad.modules.network.WebOSManager
import com.amuz.mobile_gamepad.settings.app.AppSettingModel

@Composable
fun ConnectDialog(onDismissRequest: () -> Unit) {
    val context = LocalContext.current
    var isScan by remember { mutableStateOf(false) }
    val deviceList by DiscoveryListener.mDeviceList.observeAsState(emptyList())

    Dialog(onDismissRequest = { onDismissRequest() }) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp)
                .border(
                    1.5.dp,
                    color = AppSettingModel.getBorderColor(),
                    shape = RoundedCornerShape(16.dp)
                ),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppSettingModel.getBackgroundColor())
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Box(
                        modifier = Modifier
                            .weight(9f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = WebOSManager.getDevice()?.friendlyName ?: "WebOS TV를 연결해주세요.",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = AppSettingModel.getTextColor()
                            ),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onPress = {
                                        if (isScan) {
                                            isScan = false
                                            DiscoveryListener.stopScan()
                                        } else {
                                            isScan = true
                                            DiscoveryListener.startScan()
                                        }
                                    }
                                )
                            },
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        if (isScan) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = AppSettingModel.getBorderColor(),
                                trackColor = AppSettingModel.getTextColor(),
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null,
                                tint = AppSettingModel.getTextColor(),
                            )
                        }

                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.25f)
                ) {
                    LazyColumn {
                        items(deviceList) { device ->
                            Text(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .pointerInput(Unit) {
                                        detectTapGestures(
                                            onTap = {
                                                WebOSManager.initialize(device)
                                                Toast
                                                    .makeText(
                                                        context,
                                                        WebOSManager.getDevice()?.friendlyName + " 연결이 완료되었습니다.",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                                onDismissRequest()
                                            }
                                        )
                                    },
                                text = device.friendlyName,
                                color = AppSettingModel.getTextColor()
                            )
                        }
                    }
                    Divider(color = AppSettingModel.getButtonColor())
                }
            }
        }
    }
}