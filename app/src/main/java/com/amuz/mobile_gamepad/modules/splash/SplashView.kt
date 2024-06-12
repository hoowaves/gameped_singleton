package com.amuz.mobile_gamepad.modules.splash

import android.content.Intent
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.modules.home.HomeView
import com.amuz.mobile_gamepad.modules.network.DiscoveryListener
import com.amuz.mobile_gamepad.settings.AppDatabase
import com.amuz.mobile_gamepad.settings.app.AppSettingEntity
import com.amuz.mobile_gamepad.settings.app.AppSettingModel
import com.amuz.mobile_gamepad.settings.app.AppSettingRepository
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingEntity
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingRepository
import kotlinx.coroutines.delay

class SplashView : ComponentActivity() {

    private val delay: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiscoveryListener.initialize(this)
        setContent {
            AppRepositoryInit()
            LayoutRepositoryInit()
            Render()
        }
    }

    @Composable
    fun Render() {
        val context = LocalContext.current

        LaunchedEffect(key1 = true) {
            delay(delay)

            val intent = Intent(context, HomeView::class.java)
            context.startActivity(intent)
            finish()
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("GamePad", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
    }

    @Composable
    fun AppRepositoryInit() {
        // app_settings table init
        val context = LocalContext.current
        val database = AppDatabase.getDatabase(context)
        val repository = AppSettingRepository(database.appSettingDao())
        var setting by remember { mutableStateOf<AppSettingEntity?>(null) }
        LaunchedEffect(Unit) {
            setting = repository.getSetting()
            if (setting == null) {
                repository.createDefault()
            }
            setting = repository.getSetting()
            AppSettingModel.init(setting)
        }
    }

    @Composable
    fun LayoutRepositoryInit() {
        // layout_settings table init
        val context = LocalContext.current
        val database = AppDatabase.getDatabase(context)
        val repository = LayoutSettingRepository(database.layoutSettingDao())
        var setting by remember { mutableStateOf<LayoutSettingEntity?>(null) }
        LaunchedEffect(Unit) {
            setting = repository.getSetting(0)
            if (setting == null) {
                repository.createDefaultGameController()
            }
            setting = repository.getSetting(1)
            if (setting == null) {
                repository.createDefaultDriving1()
            }
            setting = repository.getSetting(2)
            if (setting == null) {
                repository.createDefaultDriving2()
            }
            setting = repository.getSetting(3)
            if (setting == null) {
                repository.createDefaultCasual()
            }
        }
    }

}