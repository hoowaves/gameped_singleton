package com.amuz.mobile_gamepad

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.amuz.mobile_gamepad.modules.splash.SplashView
import com.amuz.mobile_gamepad.settings.app.AppDatabase
import com.amuz.mobile_gamepad.settings.app.AppSettingEntity
import com.amuz.mobile_gamepad.settings.app.AppSettingsModel
import com.amuz.mobile_gamepad.settings.app.AppSettingsRepository
import com.amuz.mobile_gamepad.ui.theme.Mobile_GamePadTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mobile_GamePadTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppDBSetting()
                    val context = LocalContext.current
                    val intent = Intent(context, SplashView::class.java)
                    context.startActivity(intent)
                    finish()
                }
            }
        }
    }

    @Composable
    fun AppDBSetting(){
        val context = LocalContext.current
        val database = AppDatabase.getDatabase(context)
        val repository = AppSettingsRepository(database.appSettingDao())
        val model = remember { AppSettingsModel(repository) }
        var settings by remember { mutableStateOf<AppSettingEntity?>(null) }
        LaunchedEffect(Unit) {
            model.saveSettings(
                AppSettingEntity(
                    id = 0,
                    appLayout = 0 ,
                    appIsDark = true,
                    appTouchEffect = false,
                    appPowerSaving = 0,
                    appIsVibration = false
                )
            )

            model.loadSettings()
            settings = model.settings
            println(settings?.id)
        }
    }

}