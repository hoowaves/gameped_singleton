package com.amuz.mobile_gamepad.modules.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.amuz.mobile_gamepad.constants.Theme
import com.amuz.mobile_gamepad.modules.home.layouts.Driving1
import com.amuz.mobile_gamepad.modules.home.layouts.Driving2
import com.amuz.mobile_gamepad.modules.home.layouts.GameController
import com.amuz.mobile_gamepad.modules.home.layouts.Casual
import com.amuz.mobile_gamepad.settings.app.AppDatabase
import com.amuz.mobile_gamepad.settings.app.AppSettingEntity
import com.amuz.mobile_gamepad.settings.app.AppSettingsModel
import com.amuz.mobile_gamepad.settings.app.AppSettingsRepository
import kotlinx.coroutines.launch

class HomeView : ComponentActivity() {

    private val gameController = GameController()
    private val driving1 = Driving1()
    private val driving2 = Driving2()
    private val casual = Casual()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Render()
        }
    }

    @Composable
    fun Render() {
        val layoutState by HomeController.layoutState.observeAsState()
        val layoutTheme by HomeController.layoutTheme.observeAsState()

        when (layoutState) {
            "Game Controller" -> gameController.Render()
            "Driving 1" -> driving1.Render()
            "Driving 2" -> driving2.Render()
            "Casual" -> casual.Render()
            else -> gameController.Render()
        }

        when (layoutTheme) {
            Theme.DARK -> {
                when (layoutState) {
                    "Game Controller" -> gameController.Render()
                    "Driving 1" -> driving1.Render()
                    "Driving 2" -> driving2.Render()
                    "Casual" -> casual.Render()
                    else -> gameController.Render()
                }
            }

            Theme.LIGHT -> {
                when (layoutState) {
                    "Game Controller" -> gameController.Render()
                    "Driving 1" -> driving1.Render()
                    "Driving 2" -> driving2.Render()
                    "Casual" -> casual.Render()
                    else -> gameController.Render()
                }
            }

            null -> {
                gameController.Render()
            }
        }


    }
}