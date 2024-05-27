package com.amuz.mobile_gamepad.modules.layoutSettingList

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.modules.home.layouts.Casual
import com.amuz.mobile_gamepad.modules.home.layouts.Driving1
import com.amuz.mobile_gamepad.modules.home.layouts.Driving2
import com.amuz.mobile_gamepad.modules.home.layouts.GameController
import com.amuz.mobile_gamepad.modules.layoutSetting.LayoutSettingView

class LayoutSettingListView : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Render()
        }
    }

    @Composable
    fun Render() {
        val context = LocalContext.current

        var isDropDownGameController by remember { mutableStateOf(false) }
        var isDropDownDriving1 by remember { mutableStateOf(false) }
        var isDropDownDriving2 by remember { mutableStateOf(false) }
        var isDropDownCasual by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HomeController.getBackgroundColor())
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
                                finish()
                            },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = HomeController.getTextColor(),
                        )
                        Text(
                            text = "레이아웃",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = HomeController.getTextColor()
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
                                color = HomeController.getBorderColor(),
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .alpha(0.3f)
                                .clickable {
                                    val intent = Intent(context, LayoutSettingView::class.java)
                                    context.startActivity(intent)
                                },
                        ) {
                            val gameController = GameController()
                            gameController.Render()
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
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .padding(start = 10.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = Color.Green,
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(7f)
                                            .fillMaxHeight()
                                    ) {
                                        Text(
                                            text = "Game Controller",
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 14.sp,
                                                color = HomeController.getTextColor()
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
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
                                                .background(HomeController.getBackgroundColor()),
                                            expanded = isDropDownGameController,
                                            onDismissRequest = { isDropDownGameController = false }
                                        ) {
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "기본으로 설정",
                                                        color = HomeController.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
                                            )
                                            DropdownMenuItem(
                                                text = {
                                                    Text(
                                                        text = "초기화",
                                                        color = HomeController.getTextColor()
                                                    )
                                                },
                                                onClick = {
                                                },
                                                modifier = Modifier
                                                    .height(40.dp)
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
                                            tint = HomeController.getTextColor(),
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
                                color = HomeController.getBorderColor(),
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .alpha(0.3f)
                        ) {
                            val driving1 = Driving1()
                            driving1.Render()
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
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .padding(start = 10.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = Color.Green,
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(8f)
                                            .fillMaxHeight()
                                    ) {
                                        Text(
                                            text = "Driving 1",
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 14.sp,
                                                color = HomeController.getTextColor()
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = null,
                                            tint = HomeController.getTextColor(),
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
                                color = HomeController.getBorderColor(),
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .alpha(0.3f)
                        ) {
                            val driving2 = Driving2()
                            driving2.Render()
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
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .padding(start = 10.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = Color.Green,
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(8f)
                                            .fillMaxHeight()
                                    ) {
                                        Text(
                                            text = "Driving 2",
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 14.sp,
                                                color = HomeController.getTextColor()
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = null,
                                            tint = HomeController.getTextColor(),
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
                                color = HomeController.getBorderColor(),
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .alpha(0.3f)
                        ) {
                            val casual = Casual()
                            casual.Render()
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
                                            .weight(1f)
                                            .fillMaxHeight()
                                            .padding(start = 10.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = Color.Green,
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(8f)
                                            .fillMaxHeight()
                                    ) {
                                        Text(
                                            text = "Casual",
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 14.sp,
                                                color = HomeController.getTextColor()
                                            ),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .fillMaxHeight(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.MoreVert,
                                            contentDescription = null,
                                            tint = HomeController.getTextColor(),
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