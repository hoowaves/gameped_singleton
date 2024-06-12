package com.amuz.mobile_gamepad.modules.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.modules.home.layouts.Driving1
import com.amuz.mobile_gamepad.modules.home.layouts.Driving2
import com.amuz.mobile_gamepad.modules.home.layouts.Casual
import com.amuz.mobile_gamepad.modules.home.layouts.DefaultMode
import com.amuz.mobile_gamepad.modules.layoutCustom.LayoutCustomController
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingModel
import com.amuz.mobile_gamepad.settings.AppDatabase
import com.amuz.mobile_gamepad.settings.app.AppSettingModel
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingEntity
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingRepository

class HomeView : ComponentActivity() {
    private val defaultModel = DefaultMode()
    private val driving1 = Driving1()
    private val driving2 = Driving2()
    private val casual = Casual()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Render()
        }
    }

    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDark by AppSettingModel.isDark.observeAsState()
        val layout by AppSettingModel.layout.observeAsState()

        val database = AppDatabase.getDatabase(context)
        val repository = LayoutSettingRepository(database.layoutSettingDao())
        var layoutEntity by remember { mutableStateOf<LayoutSettingEntity?>(null) }
        var layoutModel by remember { mutableStateOf<LayoutSettingModel?>(null) }
        var layoutCustomController by remember { mutableStateOf<LayoutCustomController?>(null) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppSettingModel.getBackgroundColor()),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Loading . . .",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = AppSettingModel.getTextColor()
                ),
            )
        }

        LaunchedEffect(layout) {
            layoutEntity = repository.getSetting(layout ?: 0)
            layoutModel = layoutEntity?.let { LayoutSettingModel(it) }
            layoutCustomController = layoutModel?.let { LayoutCustomController(it, database) }
        }

        when (layout) {
            0 -> layoutCustomController?.let { defaultModel.Render(it) }
            1 -> layoutCustomController?.let { driving1.Render(it) }
            2 -> layoutCustomController?.let { driving2.Render(it) }
            3 -> layoutCustomController?.let { casual.Render(it) }
            else -> layoutCustomController?.let { defaultModel.Render(it) }
        }

        when (isDark) {
            true -> {
                when (layout) {
                    0 -> layoutCustomController?.let { defaultModel.Render(it) }
                    1 -> layoutCustomController?.let { driving1.Render(it) }
                    2 -> layoutCustomController?.let { driving2.Render(it) }
                    3 -> layoutCustomController?.let { casual.Render(it) }
                    else -> layoutCustomController?.let { defaultModel.Render(it) }
                }
            }

            false -> {
                when (layout) {
                    0 -> layoutCustomController?.let { defaultModel.Render(it) }
                    1 -> layoutCustomController?.let { driving1.Render(it) }
                    2 -> layoutCustomController?.let { driving2.Render(it) }
                    3 -> layoutCustomController?.let { casual.Render(it) }
                    else -> layoutCustomController?.let { defaultModel.Render(it) }
                }
            }

            null -> {
                layoutCustomController?.let { defaultModel.Render(it) }
            }
        }

    }
}