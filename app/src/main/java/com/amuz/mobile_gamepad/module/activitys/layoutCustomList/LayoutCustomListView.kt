package com.amuz.mobile_gamepad.module.activitys.layoutCustomList

import androidx.activity.ComponentActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.module.activitys.casual.CasualView
import com.amuz.mobile_gamepad.module.activitys.defaultMode.DefaultModeView
import com.amuz.mobile_gamepad.module.activitys.driving1.Driving1View
import com.amuz.mobile_gamepad.module.activitys.driving2.Driving2View
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService
import kotlinx.coroutines.launch


class LayoutCustomListView : ComponentActivity() {
    private lateinit var controller: LayoutCustomListController
    private var isDataInitialized by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = LayoutCustomListController(this)

        setContent {
            LaunchedEffect(Unit) {
                controller.dataInit()
                isDataInitialized = true
            }
            if (isDataInitialized) {
                Render()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        when (controller.layout.value) {
            0 ->startActivity(Intent(this, DefaultModeView::class.java))
            1 -> startActivity(Intent(this, Driving1View::class.java))
            2 -> startActivity(Intent(this, Driving2View::class.java))
            3 -> startActivity(Intent(this, CasualView::class.java))
            else -> startActivity(Intent(this, DefaultModeView::class.java))
        }
        finish()
        overridePendingTransition(0, 0)
    }

    @Composable
    fun Render() {
        val context = LocalContext.current
        val isDarkService = IsDarkService(controller.isDark.value ?: false)
        val scope = rememberCoroutineScope()
        var isDropDownGameController by remember { mutableStateOf(false) }
        var isDropDownDriving1 by remember { mutableStateOf(false) }
        var isDropDownDriving2 by remember { mutableStateOf(false) }
        var isDropDownCasual by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(isDarkService.getBackgroundColor())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1.5f)
                            .fillMaxHeight()
                            .padding(start = 10.dp)
                            .clickable {
                                onBackPressed()
                            },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = isDarkService.getTextColor(),
                        )
                        Text(
                            text = "레이아웃",
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
                    Box(
                        modifier = Modifier
                            .weight(8.5f)
                            .fillMaxHeight()
                    ) {
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)
            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
                    .padding(bottom = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                    ) {

                    }
                    Box(
                        modifier = Modifier
                            .weight(4.25f)
                            .fillMaxHeight()
                            .border(
                                1.dp,
                                color = isDarkService.getBorderColor(),
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .alpha(0.3f)
                                .clickable {
                                    val intent = Intent(context, LayoutCustomView::class.java)
                                    intent.putExtra("layout", controller.defaultModeController.value?.layoutId?.value)
                                    context.startActivity(intent)
                                    overridePendingTransition(0, 0)
                                    finish()
                                },
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(isDarkService.getBackgroundColor()),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Loading . . .",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = isDarkService.getTextColor()
                                    ),
                                )
                            }
                            controller.defaultModeController.value?.let {
                                controller.defaultModeView.Render(
                                    it
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f)
                                    .padding(top = 10.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .weight(8f)
                                            .fillMaxHeight()
                                            .padding(start = 10.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            if (controller.layout.value == 0) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color.Green,
                                                    modifier = Modifier.padding()
                                                )
                                            }
                                            Text(
                                                text = "Game Controller",
                                                style = TextStyle(
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 14.sp,
                                                    color = isDarkService.getTextColor()
                                                ),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(start = 8.dp)
                                            )
                                        }
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                    ) {
                                        DropdownMenu(
                                            modifier = Modifier
                                                .width(140.dp)
                                                .height(90.dp)
                                                .background(isDarkService.getBackgroundColor()),
                                            expanded = isDropDownGameController,
                                            onDismissRequest = { isDropDownGameController = false }
                                        ) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "기본으로 설정",
                                                        color = isDarkService.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (controller.layout.value != 0) {
                                                        scope.launch {
                                                            controller.layout.value = 0
                                                            controller.update()
                                                            isDropDownGameController = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        controller.reloadActivity()
                                                        if (context is ComponentActivity) {
                                                            context.finish()
                                                            context.overridePendingTransition(0, 0)
                                                        }
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (controller.layout.value == 0) 0.3f else 1f)
                                            )
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "초기화",
                                                        color = isDarkService.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (controller.defaultModeController.value?.isDefault?.value == false) {
                                                        scope.launch {
                                                            controller.defaultModeController.value!!.reset()
                                                            isDropDownGameController = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        controller.reloadActivity()
                                                        if (context is ComponentActivity) {
                                                            context.finish()
                                                            context.overridePendingTransition(0, 0)
                                                        }
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (controller.defaultModeController.value?.isDefault?.value == false) 1f else 0.3f)
                                            )
                                        }
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .clickable { isDropDownGameController = true },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = null,
                                            tint = isDarkService.getTextColor(),
                                        )
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(8f)
                            ) {

                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                    ) {

                    }
                    Box(
                        modifier = Modifier
                            .weight(4.25f)
                            .fillMaxHeight()
                            .border(
                                1.dp,
                                color = isDarkService.getBorderColor(),
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .alpha(0.3f)
                                .clickable {
                                    val intent = Intent(context, LayoutCustomView::class.java)
                                    intent.putExtra("layout", controller.driving1Controller.value?.layoutId?.value)
                                    context.startActivity(intent)
                                    overridePendingTransition(0, 0)
                                    finish()
                                },
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(isDarkService.getBackgroundColor()),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Loading . . .",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = isDarkService.getTextColor()
                                    ),
                                )
                            }
                            controller.driving1Controller.value?.let {
                                controller.driving1View.Render(
                                    it
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f)
                                    .padding(top = 10.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .weight(8f)
                                            .fillMaxHeight()
                                            .padding(start = 10.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            if (controller.layout.value == 1) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color.Green,
                                                    modifier = Modifier.padding()
                                                )
                                            }
                                            Text(
                                                text = "Driving 1",
                                                style = TextStyle(
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 14.sp,
                                                    color = isDarkService.getTextColor()
                                                ),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(start = 8.dp)
                                            )
                                        }
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                    ) {
                                        DropdownMenu(
                                            modifier = Modifier
                                                .width(140.dp)
                                                .height(90.dp)
                                                .background(isDarkService.getBackgroundColor()),
                                            expanded = isDropDownDriving1,
                                            onDismissRequest = { isDropDownDriving1 = false }
                                        ) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "기본으로 설정",
                                                        color = isDarkService.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (controller.layout.value != 1) {
                                                        scope.launch {
                                                            controller.layout.value = 1
                                                            controller.update()
                                                            isDropDownGameController = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        controller.reloadActivity()
                                                        if (context is ComponentActivity) {
                                                            context.finish()
                                                            context.overridePendingTransition(0, 0)
                                                        }
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (controller.layout.value == 1) 0.3f else 1f)
                                            )
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "초기화",
                                                        color = isDarkService.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (controller.driving1Controller.value?.isDefault?.value == false) {
                                                        scope.launch {
                                                            controller.driving1Controller.value!!.reset()
                                                            isDropDownGameController = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        controller.reloadActivity()
                                                        if (context is ComponentActivity) {
                                                            context.finish()
                                                            context.overridePendingTransition(0, 0)
                                                        }
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (controller.driving1Controller.value?.isDefault?.value == false) 1f else 0.3f)
                                            )
                                        }
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .clickable { isDropDownDriving1 = true },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = null,
                                            tint = isDarkService.getTextColor(),
                                        )
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(8f)
                            ) {

                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                    ) {

                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(4f)
                    .padding(top = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                    ) {

                    }
                    Box(
                        modifier = Modifier
                            .weight(4.25f)
                            .fillMaxHeight()
                            .border(
                                1.dp,
                                color = isDarkService.getBorderColor(),
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .alpha(0.3f)
                                .clickable {
                                    val intent = Intent(context, LayoutCustomView::class.java)
                                    intent.putExtra("layout", controller.driving2Controller.value?.layoutId?.value)
                                    context.startActivity(intent)
                                    overridePendingTransition(0, 0)
                                    finish()
                                },
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(isDarkService.getBackgroundColor()),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Loading . . .",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = isDarkService.getTextColor()
                                    ),
                                )
                            }
                            controller.driving2Controller.value?.let {
                                controller.driving2View.Render(
                                    it
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f)
                                    .padding(top = 10.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .weight(8f)
                                            .fillMaxHeight()
                                            .padding(start = 10.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            if (controller.layout.value == 2) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color.Green,
                                                    modifier = Modifier.padding()
                                                )
                                            }
                                            Text(
                                                text = "Driving 2",
                                                style = TextStyle(
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 14.sp,
                                                    color = isDarkService.getTextColor()
                                                ),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(start = 8.dp)
                                            )
                                        }
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                    ) {
                                        DropdownMenu(
                                            modifier = Modifier
                                                .width(140.dp)
                                                .height(90.dp)
                                                .background(isDarkService.getBackgroundColor()),
                                            expanded = isDropDownDriving2,
                                            onDismissRequest = { isDropDownDriving2 = false }
                                        ) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "기본으로 설정",
                                                        color = isDarkService.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (controller.layout.value != 2) {
                                                        scope.launch {
                                                            controller.layout.value = 2
                                                            controller.update()
                                                            isDropDownGameController = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        controller.reloadActivity()
                                                        if (context is ComponentActivity) {
                                                            context.finish()
                                                            context.overridePendingTransition(0, 0)
                                                        }
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (controller.layout.value == 2) 0.3f else 1f)
                                            )
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "초기화",
                                                        color = isDarkService.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (controller.driving2Controller.value?.isDefault?.value == false) {
                                                        scope.launch {
                                                            controller.driving2Controller.value!!.reset()
                                                            isDropDownGameController = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        controller.reloadActivity()
                                                        if (context is ComponentActivity) {
                                                            context.finish()
                                                            context.overridePendingTransition(0, 0)
                                                        }
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (controller.driving2Controller.value?.isDefault?.value == false) 1f else 0.3f)
                                            )
                                        }
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .clickable { isDropDownDriving2 = true },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = null,
                                            tint = isDarkService.getTextColor(),
                                        )
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(8f)
                            ) {

                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                    ) {

                    }
                    Box(
                        modifier = Modifier
                            .weight(4.25f)
                            .fillMaxHeight()
                            .border(
                                1.dp,
                                color = isDarkService.getBorderColor(),
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .alpha(0.3f)
                                .clickable {
                                    val intent = Intent(context, LayoutCustomView::class.java)
                                    intent.putExtra("layout", controller.casualController.value?.layoutId?.value)
                                    context.startActivity(intent)
                                    overridePendingTransition(0, 0)
                                    finish()
                                },
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(isDarkService.getBackgroundColor()),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Loading . . .",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp,
                                        color = isDarkService.getTextColor()
                                    ),
                                )
                            }
                            controller.casualController.value?.let {
                                controller.casualView.Render(
                                    it
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f)
                                    .padding(top = 10.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .weight(8f)
                                            .fillMaxHeight()
                                            .padding(start = 10.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            if (controller.layout.value == 3) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color.Green,
                                                    modifier = Modifier.padding()
                                                )
                                            }
                                            Text(
                                                text = "Casual",
                                                style = TextStyle(
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 14.sp,
                                                    color = isDarkService.getTextColor()
                                                ),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(start = 8.dp)
                                            )
                                        }
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                    ) {
                                        DropdownMenu(
                                            modifier = Modifier
                                                .width(140.dp)
                                                .height(90.dp)
                                                .background(isDarkService.getBackgroundColor()),
                                            expanded = isDropDownCasual,
                                            onDismissRequest = { isDropDownCasual = false }
                                        ) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "기본으로 설정",
                                                        color = isDarkService.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (controller.layout.value != 3) {
                                                        scope.launch {
                                                            controller.layout.value = 3
                                                            controller.update()
                                                            isDropDownGameController = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        controller.reloadActivity()
                                                        if (context is ComponentActivity) {
                                                            context.finish()
                                                            context.overridePendingTransition(0, 0)
                                                        }
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (controller.layout.value == 3) 0.3f else 1f)
                                            )
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "초기화",
                                                        color = isDarkService.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (controller.casualController.value?.isDefault?.value == false) {
                                                        scope.launch {
                                                            controller.casualController.value!!.reset()
                                                            isDropDownGameController = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        controller.reloadActivity()
                                                        if (context is ComponentActivity) {
                                                            context.finish()
                                                            context.overridePendingTransition(0, 0)
                                                        }
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (controller.driving2Controller.value?.isDefault?.value == false) 1f else 0.3f)
                                            )
                                        }
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .clickable { isDropDownCasual = true },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = null,
                                            tint = isDarkService.getTextColor(),
                                        )
                                    }
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(8f)
                            ) {

                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                    ) {

                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
            ) {

            }
        }
    }
}