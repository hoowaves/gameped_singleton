package com.amuz.mobile_gamepad.modules.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.modules.home.HomeView
import com.amuz.mobile_gamepad.settings.app.AppDatabase
import com.amuz.mobile_gamepad.settings.app.AppSettingEntity
import com.amuz.mobile_gamepad.settings.app.AppSettingsModel
import com.amuz.mobile_gamepad.settings.app.AppSettingsRepository
import kotlinx.coroutines.delay

class SplashView : ComponentActivity() {

    private val delay: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Render()
        }
    }

    @Composable
    fun Render() {
        val context = LocalContext.current

        LaunchedEffect(key1 = true) {
            delay(delay)

            val intent = Intent(context, HomeView::class.java)
//            val intent = Intent(context, LayoutSettingView::class.java)
            context.startActivity(intent)
            finish()
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("GamePad", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
    }

}