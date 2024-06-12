package com.amuz.mobile_gamepad.modules.layoutCustom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.modules.home.layouts.Casual
import com.amuz.mobile_gamepad.modules.home.layouts.DefaultMode
import com.amuz.mobile_gamepad.modules.home.layouts.Driving1
import com.amuz.mobile_gamepad.modules.home.layouts.Driving2
import com.amuz.mobile_gamepad.modules.layoutCustomList.LayoutCustomListView
import com.amuz.mobile_gamepad.modules.widgets.dialogs.CustomColorSave
import com.amuz.mobile_gamepad.modules.widgets.dialogs.CustomLayoutDialog
import com.amuz.mobile_gamepad.settings.AppDatabase
import com.amuz.mobile_gamepad.settings.app.AppSettingModel
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingEntity
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingModel
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingRepository
import kotlinx.coroutines.launch

class LayoutCustomView : ComponentActivity() {
    private val defaultModel = DefaultMode()
    private val driving1 = Driving1()
    private val driving2 = Driving2()
    private val casual = Casual()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val layout = intent.getIntExtra("layout", 0)
            Render(layout)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LayoutCustomListView::class.java))
        finish()
        overridePendingTransition(0, 0)
    }

    @Composable
    fun Render(layout: Int) {
        val context = LocalContext.current
        val isDark by AppSettingModel.isDark.observeAsState()
        val scope = rememberCoroutineScope()
        var isSaveDialog by remember { mutableStateOf(false) }
        var isLayoutDialog by remember { mutableStateOf(false) }

        val database = AppDatabase.getDatabase(context)
        val layoutRepository = LayoutSettingRepository(database.layoutSettingDao())
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

        LaunchedEffect(Unit) {
            layoutEntity = layoutRepository.getSetting(layout)
            layoutModel = layoutEntity?.let { LayoutSettingModel(it) }
            layoutCustomController = layoutModel?.let { LayoutCustomController(it, database) }
        }

        layoutCustomController?.let {
            when (layout) {
                0 -> defaultModel.Render(it)
                1 -> driving1.Render(it)
                2 -> driving2.Render(it)
                3 -> casual.Render(it)
                else -> defaultModel.Render(it)
            }

            when (isDark) {
                true -> {
                    when (layout) {
                        0 -> defaultModel.Render(it)
                        1 -> driving1.Render(it)
                        2 -> driving2.Render(it)
                        3 -> casual.Render(it)
                        else -> defaultModel.Render(it)
                    }
                }

                false -> {
                    when (layout) {
                        0 -> defaultModel.Render(it)
                        1 -> driving1.Render(it)
                        2 -> driving2.Render(it)
                        3 -> casual.Render(it)
                        else -> defaultModel.Render(it)
                    }
                }

                null -> {
                    defaultModel.Render(it)
                }
            }

        }


        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.5f),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(4.5f)
                    ) {

                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 20.dp)
                                .background(
                                    color = AppColor.CustomColor.tap,
                                    shape = RoundedCornerShape(24.dp)
                                )
                                .border(
                                    1.5.dp,
                                    color = AppSettingModel.getBorderColor(),
                                    shape = RoundedCornerShape(24.dp)
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxHeight()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(5f)
                                        .clickable {
                                            if (layoutCustomController?.isChanged?.value == true) {
                                                isSaveDialog = true
                                            } else {
                                                onBackPressed()
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = null,
                                        tint = AppSettingModel.getTextColor(),
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(5f)
                                        .clickable {
                                            isLayoutDialog = true
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = null,
                                        tint = AppSettingModel.getTextColor(),
                                    )
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(4.5f)
                    ) {
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8.5f)
            ) {
            }
        }

        if (isSaveDialog) {
            CustomColorSave(
                onDismissRequest = { isSave ->
                    isSaveDialog = false
                    if (isSave) {
                        scope.launch {
                            layoutCustomController?.layoutUpdate()
                            layoutCustomController?.appUpdate()
                            Toast.makeText(context, "설정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                            onBackPressed()
                        }
                    }
                }
            )
        }

        if (isLayoutDialog) {
            CustomLayoutDialog(
                layoutCustomController
            ) {
                isLayoutDialog = false
            }
        }
    }

}