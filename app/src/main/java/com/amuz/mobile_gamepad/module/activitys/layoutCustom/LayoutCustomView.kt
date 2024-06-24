package com.amuz.mobile_gamepad.module.activitys.layoutCustom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.module.activitys.layoutCustomList.LayoutCustomListView
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import com.amuz.mobile_gamepad.module.widgets.commons.LoadingPage
import com.amuz.mobile_gamepad.module.widgets.dialogs.CustomColorSave
import com.amuz.mobile_gamepad.module.widgets.dialogs.CustomLayoutDialog
import kotlinx.coroutines.launch

class LayoutCustomView : ComponentActivity() {
    private lateinit var controller: LayoutCustomControllerImpl
    private var isDataInitialized by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = LayoutCustomControllerImpl(this)

        setContent {
            val layoutId = intent.getIntExtra("layout", 0)
            LaunchedEffect(Unit) {
                controller.dataInit(layoutId)
                isDataInitialized = true
            }

            LoadingPage()

            AnimatedVisibility(
                visible = isDataInitialized,
                enter = fadeIn(animationSpec = tween(durationMillis = 500))
            ) {
                when (controller.layoutId.value) {
                    0 -> controller.defaultModeView.Render(controller)
                    1 -> controller.driving1View.Render(controller)
                    2 -> controller.driving2View.Render(controller)
                    3 -> controller.casualView.Render(controller)
                }
            }

            AnimatedVisibility(
                visible = isDataInitialized,
                enter = fadeIn(animationSpec = tween(durationMillis = 500))
            ) {
                Render()
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LayoutCustomListView::class.java))
        finish()
        overridePendingTransition(0, 0)
    }

    @Composable
    fun Render() {
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val scope = rememberCoroutineScope()
        var isSaveDialog by remember { mutableStateOf(false) }
        var isLayoutDialog by remember { mutableStateOf(false) }

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
                            .weight(4.25f)
                    ) {

                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1.5f)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = isDarkService.getButtonColor(),
                                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
                                )
                                .border(
                                    1.5.dp,
                                    color = isDarkService.getBorderColor(),
                                    shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
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

                                            if (controller.isChanged()) {
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
                                        tint = isDarkService.getTextColor(),
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
                                        tint = isDarkService.getTextColor(),
                                    )
                                }
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(4.25f)
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
                controller = controller,
                onDismissRequest = { isSave ->
                    isSaveDialog = false
                    if (isSave) {
                        scope.launch {
                            controller.update()
                            Toast.makeText(
                                this@LayoutCustomView,
                                "Setup is complete.",
                                Toast.LENGTH_SHORT
                            ).show()
                            onBackPressed()
                        }
                    }
                }
            )
        }

        if (isLayoutDialog) {
            CustomLayoutDialog(
                controller = controller
            ) {
                isLayoutDialog = false
            }
        }
    }

}