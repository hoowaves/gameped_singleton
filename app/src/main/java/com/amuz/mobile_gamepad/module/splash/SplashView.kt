package com.amuz.mobile_gamepad.module.splash

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.module.network.DiscoveryListener
import kotlinx.coroutines.delay

class SplashView : ComponentActivity() {
    private lateinit var controller: SplashController
    private var isDataInitialized by mutableStateOf(false)
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
            finish()
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("GamePad", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
    }

}