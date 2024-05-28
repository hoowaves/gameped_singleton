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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.modules.home.HomeView
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.modules.home.HomeModel
import com.amuz.mobile_gamepad.settings.AppDatabase
import com.amuz.mobile_gamepad.settings.app.AppSettingEntity
import com.amuz.mobile_gamepad.settings.app.AppSettingsRepository
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingEntity
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingRepository
import kotlinx.coroutines.delay

class SplashView : ComponentActivity() {

    private val delay: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
//            val intent = Intent(context, LayoutSettingView::class.java)
            context.startActivity(intent)
            finish()
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("GamePad", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
    }

    @Composable
    fun AppRepositoryInit() {
        val context = LocalContext.current
        val database = AppDatabase.getDatabase(context)
        val repository = AppSettingsRepository(database.appSettingDao())
        var setting by remember { mutableStateOf<AppSettingEntity?>(null) }
        LaunchedEffect(Unit) {
            setting = repository.getSetting()
            if (setting == null) {
                Log.d("test", "생성")
                repository.saveSetting(
                    AppSettingEntity(
                        id = 0,
                        layout = 0,
                        isDark = true,
                        touchEffect = false,
                        powerSaving = 0,
                        isVibration = false
                    )
                )
            }
            val homeModel = HomeModel(context)
            HomeController.init(homeModel)
        }
    }

    @Composable
    fun LayoutRepositoryInit() {
        val context = LocalContext.current
        val database = AppDatabase.getDatabase(context)
        val repository = LayoutSettingRepository(database.layoutSettingDao())
        var setting by remember { mutableStateOf<LayoutSettingEntity?>(null) }
        LaunchedEffect(Unit) {
            setting = repository.getSetting(0)
            if (setting == null) {
                repository.saveSetting(
                    LayoutSettingEntity(
                        id = 0,
                        name = "Game Controller",
                        ltButton = 0,
                        lbButton = 0,
                        rtButton = 0,
                        rbButton = 0,
                        ltsButton = 0,
                        rtsButton = 0,
                        directionButton = 0,
                        yButton = 0,
                        bButton = 0,
                        xButton = 0,
                        aButton = 0,
                    )
                )
            }
            setting = repository.getSetting(1)
            if (setting == null) {
                repository.saveSetting(
                    LayoutSettingEntity(
                        id = 1,
                        name = "Driving 1",
                        ltButton = 0,
                        lbButton = 0,
                        rtButton = 0,
                        rbButton = 0,
                        ltsButton = 0,
                        rtsButton = 0,
                        directionButton = 0,
                        yButton = 0,
                        bButton = 0,
                        xButton = 0,
                        aButton = 0,
                    )
                )
            }
            setting = repository.getSetting(2)
            if (setting == null) {
                repository.saveSetting(
                    LayoutSettingEntity(
                        id = 2,
                        name = "Driving 2",
                        ltButton = 0,
                        lbButton = 0,
                        rtButton = 0,
                        rbButton = 0,
                        ltsButton = 0,
                        rtsButton = 0,
                        directionButton = 0,
                        yButton = 0,
                        bButton = 0,
                        xButton = 0,
                        aButton = 0,
                    )
                )
            }
            setting = repository.getSetting(3)
            if (setting == null) {
                repository.saveSetting(
                    LayoutSettingEntity(
                        id = 3,
                        name = "Casual",
                        ltButton = 0,
                        lbButton = 0,
                        rtButton = 0,
                        rbButton = 0,
                        ltsButton = 0,
                        rtsButton = 0,
                        directionButton = 0,
                        yButton = 0,
                        bButton = 0,
                        xButton = 0,
                        aButton = 0,
                    )
                )
            }
        }
    }

}