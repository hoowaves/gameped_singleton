package com.amuz.mobile_gamepad.modules.layoutCustomList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.amuz.mobile_gamepad.modules.home.HomeView
import com.amuz.mobile_gamepad.modules.home.layouts.Casual
import com.amuz.mobile_gamepad.modules.home.layouts.DefaultMode
import com.amuz.mobile_gamepad.modules.home.layouts.Driving1
import com.amuz.mobile_gamepad.modules.home.layouts.Driving2
import com.amuz.mobile_gamepad.modules.layoutCustom.LayoutCustomController
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingModel
import com.amuz.mobile_gamepad.modules.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.settings.AppDatabase
import com.amuz.mobile_gamepad.settings.app.AppSettingModel
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingEntity
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingRepository
import kotlinx.coroutines.launch

class LayoutCustomListView : ComponentActivity() {
    private val defaultModel = DefaultMode()
    private val driving1 = Driving1()
    private val driving2 = Driving2()
    private val casual = Casual()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppSettingModel.getBackgroundColor())
            ) {
                Render()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, HomeView::class.java))
        finish()
        overridePendingTransition(0, 0)
    }

    @Composable
    fun Render() {
        val context = LocalContext.current

        val database = AppDatabase.getDatabase(context)
        val repository = LayoutSettingRepository(database.layoutSettingDao())
        var layoutEntityList by remember { mutableStateOf<List<LayoutSettingEntity>?>(null) }
        LaunchedEffect(Unit) {
            layoutEntityList = repository.getSettingList()
        }

        val scope = rememberCoroutineScope()

        var isDropDownGameController by remember { mutableStateOf(false) }
        var isDropDownDriving1 by remember { mutableStateOf(false) }
        var isDropDownDriving2 by remember { mutableStateOf(false) }
        var isDropDownCasual by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppSettingModel.getBackgroundColor())
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
                            tint = AppSettingModel.getTextColor(),
                        )
                        Text(
                            text = "레이아웃",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = AppSettingModel.getTextColor()
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
                                color = AppSettingModel.getBorderColor(),
                            )
                    ) {
                        val layoutEntity = layoutEntityList?.get(0)
                        val layoutModel = layoutEntity?.let { LayoutSettingModel(it) }
                        val layoutController =
                            layoutModel?.let { LayoutCustomController(it, database) }
                        val isDefaultLayout = layoutController?.isDefaultLayout?.value ?: true

                        Box(
                            modifier = Modifier
                                .alpha(0.3f)
                                .clickable {
                                    val intent = Intent(context, LayoutCustomView::class.java)
                                    intent.putExtra("layout", 0)
                                    context.startActivity(intent)
                                    overridePendingTransition(0, 0)
                                    finish()
                                },
                        ) {
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
                                        fontSize = 16.sp,
                                        color = AppSettingModel.getTextColor()
                                    ),
                                )
                            }
                            layoutController?.let { defaultModel.Render(it) }
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
                                            if (AppSettingModel.layout.value == 0) {
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
                                                    color = AppSettingModel.getTextColor()
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
                                                .background(AppSettingModel.getBackgroundColor()),
                                            expanded = isDropDownGameController,
                                            onDismissRequest = { isDropDownGameController = false }
                                        ) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "기본으로 설정",
                                                        color = AppSettingModel.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (!isDefaultLayout) {
                                                        scope.launch {
                                                            layoutController?.isDefaultLayout?.value =
                                                                true
                                                            layoutController?.appUpdate()
                                                            isDropDownGameController = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (isDefaultLayout) 0.3f else 1f)
                                            )
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "초기화",
                                                        color = AppSettingModel.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (layoutController?.isReset?.value == true) {
                                                        scope.launch {
                                                            layoutController.layoutReset()
                                                            isDropDownGameController = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        startActivity(
                                                            Intent(
                                                                context,
                                                                LayoutCustomListView::class.java
                                                            )
                                                        )
                                                        finish()
                                                        overridePendingTransition(0, 0)
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (layoutController?.isReset?.value == true) 1f else 0.3f)
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
                                            tint = AppSettingModel.getTextColor(),
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
                                color = AppSettingModel.getBorderColor(),
                            )
                    ) {
                        val layoutEntity = layoutEntityList?.get(1)
                        val layoutModel = layoutEntity?.let { LayoutSettingModel(it) }
                        val layoutController =
                            layoutModel?.let { LayoutCustomController(it, database) }
                        val isDefaultLayout = layoutController?.isDefaultLayout?.value ?: true
                        Box(
                            modifier = Modifier
                                .alpha(0.3f)
                                .clickable {
                                    val intent = Intent(context, LayoutCustomView::class.java)
                                    intent.putExtra("layout", 1)
                                    context.startActivity(intent)
                                    overridePendingTransition(0, 0)
                                    finish()
                                },
                        ) {
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
                                        fontSize = 16.sp,
                                        color = AppSettingModel.getTextColor()
                                    ),
                                )
                            }
                            layoutController?.let { driving1.Render(it) }
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
                                            if (AppSettingModel.layout.value == 1) {
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
                                                    color = AppSettingModel.getTextColor()
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
                                                .background(AppSettingModel.getBackgroundColor()),
                                            expanded = isDropDownDriving1,
                                            onDismissRequest = { isDropDownDriving1 = false }
                                        ) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "기본으로 설정",
                                                        color = AppSettingModel.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (!isDefaultLayout) {
                                                        scope.launch {
                                                            layoutController?.isDefaultLayout?.value =
                                                                true
                                                            layoutController?.appUpdate()
                                                            isDropDownDriving1 = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (isDefaultLayout) 0.3f else 1f)
                                            )
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "초기화",
                                                        color = AppSettingModel.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (layoutController?.isReset?.value == true) {
                                                        scope.launch {
                                                            layoutController.layoutReset()
                                                            isDropDownDriving1 = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        startActivity(
                                                            Intent(
                                                                context,
                                                                LayoutCustomListView::class.java
                                                            )
                                                        )
                                                        finish()
                                                        overridePendingTransition(0, 0)
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (layoutController?.isReset?.value == true) 1f else 0.3f)
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
                                            tint = AppSettingModel.getTextColor(),
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
                                color = AppSettingModel.getBorderColor(),
                            )
                    ) {
                        val layoutEntity = layoutEntityList?.get(2)
                        val layoutModel = layoutEntity?.let { LayoutSettingModel(it) }
                        val layoutController =
                            layoutModel?.let { LayoutCustomController(it, database) }
                        val isDefaultLayout = layoutController?.isDefaultLayout?.value ?: true
                        Box(
                            modifier = Modifier
                                .alpha(0.3f)
                                .clickable {
                                    val intent = Intent(context, LayoutCustomView::class.java)
                                    intent.putExtra("layout", 2)
                                    context.startActivity(intent)
                                    overridePendingTransition(0, 0)
                                    finish()
                                },
                        ) {
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
                                        fontSize = 16.sp,
                                        color = AppSettingModel.getTextColor()
                                    ),
                                )
                            }
                            layoutController?.let { driving2.Render(it) }
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
                                            if (AppSettingModel.layout.value == 2) {
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
                                                    color = AppSettingModel.getTextColor()
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
                                                .background(AppSettingModel.getBackgroundColor()),
                                            expanded = isDropDownDriving2,
                                            onDismissRequest = { isDropDownDriving2 = false }
                                        ) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "기본으로 설정",
                                                        color = AppSettingModel.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (!isDefaultLayout) {
                                                        scope.launch {
                                                            layoutController?.isDefaultLayout?.value =
                                                                true
                                                            layoutController?.appUpdate()
                                                            isDropDownDriving2 = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (isDefaultLayout) 0.3f else 1f)
                                            )
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "초기화",
                                                        color = AppSettingModel.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (layoutController?.isReset?.value == true) {
                                                        scope.launch {
                                                            layoutController.layoutReset()
                                                            isDropDownDriving2 = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        startActivity(
                                                            Intent(
                                                                context,
                                                                LayoutCustomListView::class.java
                                                            )
                                                        )
                                                        finish()
                                                        overridePendingTransition(0, 0)
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (layoutController?.isReset?.value == true) 1f else 0.3f)
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
                                            tint = AppSettingModel.getTextColor(),
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
                                color = AppSettingModel.getBorderColor(),
                            )
                    ) {
                        val layoutEntity = layoutEntityList?.get(3)
                        val layoutModel = layoutEntity?.let { LayoutSettingModel(it) }
                        val layoutController =
                            layoutModel?.let { LayoutCustomController(it, database) }
                        val isDefaultLayout = layoutController?.isDefaultLayout?.value ?: true
                        Box(
                            modifier = Modifier
                                .alpha(0.3f)
                                .clickable {
                                    val intent = Intent(context, LayoutCustomView::class.java)
                                    intent.putExtra("layout", 3)
                                    context.startActivity(intent)
                                    overridePendingTransition(0, 0)
                                    finish()
                                },
                        ) {
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
                                        fontSize = 16.sp,
                                        color = AppSettingModel.getTextColor()
                                    ),
                                )
                            }
                            layoutController?.let { casual.Render(it) }
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
                                            if (AppSettingModel.layout.value == 3) {
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
                                                    color = AppSettingModel.getTextColor()
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
                                                .background(AppSettingModel.getBackgroundColor()),
                                            expanded = isDropDownCasual,
                                            onDismissRequest = { isDropDownCasual = false }
                                        ) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "기본으로 설정",
                                                        color = AppSettingModel.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (!isDefaultLayout) {
                                                        scope.launch {
                                                            layoutController?.isDefaultLayout?.value =
                                                                true
                                                            layoutController?.appUpdate()
                                                            isDropDownCasual = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (isDefaultLayout) 0.3f else 1f)
                                            )
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "초기화",
                                                        color = AppSettingModel.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                    if (layoutController?.isReset?.value == true) {
                                                        scope.launch {
                                                            layoutController.layoutReset()
                                                            isDropDownCasual = false
                                                        }
                                                        Toast.makeText(
                                                            context,
                                                            "설정이 완료되었습니다.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        startActivity(
                                                            Intent(
                                                                context,
                                                                LayoutCustomListView::class.java
                                                            )
                                                        )
                                                        finish()
                                                        overridePendingTransition(0, 0)
                                                    }
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                                    .alpha(if (layoutController?.isReset?.value == true) 1f else 0.3f)
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
                                            tint = AppSettingModel.getTextColor(),
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