package com.amuz.mobile_gamepad.module.splash

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.R
import com.amuz.mobile_gamepad.module.network.DiscoveryListener
import kotlinx.coroutines.delay

class SplashView : ComponentActivity() {
    private lateinit var controller: SplashController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = SplashController(this)
        DiscoveryListener.initialize(this)

        setContent {
            Render()
        }
    }

    @Composable
    fun Render() {
        val delay: Long = 1000
        LaunchedEffect(key1 = true) {
            delay(delay)
            // db initialize
            controller.appInit()
            controller.layoutInit()
            // data Load
            controller.dataInit()

            when (controller.appSettingEntity.value?.layout) {
                0 -> controller.routeDefaultMode()
                1 -> controller.routeDriving1()
                2 -> controller.routeDriving2()
                3 -> controller.routeCasual()
            }
            overridePendingTransition(0, 0)
            finish()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xFF000000), Color(0xFF363636)),
                        start = androidx.compose.ui.geometry.Offset(0f, 0f),
                        end = androidx.compose.ui.geometry.Offset(0f, Float.POSITIVE_INFINITY)
                    ),
                    shape = RectangleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "MOBILE\nGAMEPAD",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            color = Color.White,
                            letterSpacing = 0.03.em,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.wrapContentSize()
                    )
                    Box(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .width(114.dp)
                            .height(70.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.splash),
                            contentDescription = "splash"
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp), // Adjust padding as needed
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "Copyright 2024 LG Electronics.",
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        color = Color(0xFF949494),
                        letterSpacing = 0.03.em,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.wrapContentSize()
                )
            }

        }
    }
}