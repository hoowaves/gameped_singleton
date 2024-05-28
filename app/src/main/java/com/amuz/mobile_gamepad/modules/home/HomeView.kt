package com.amuz.mobile_gamepad.modules.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.amuz.mobile_gamepad.modules.home.layouts.Driving1
import com.amuz.mobile_gamepad.modules.home.layouts.Driving2
import com.amuz.mobile_gamepad.modules.home.layouts.GameController
import com.amuz.mobile_gamepad.modules.home.layouts.Casual

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
        val layout by HomeController.appLayout.observeAsState()
        val isDark by HomeController.appIsDark.observeAsState()
        when (layout) {
            0 -> gameController.Render()
            1 -> driving1.Render()
            2 -> driving2.Render()
            3 -> casual.Render()
            else -> gameController.Render()
        }

        when (isDark) {
            true -> {
                when (layout) {
                    0 -> gameController.Render()
                    1 -> driving1.Render()
                    2 -> driving2.Render()
                    3 -> casual.Render()
                    else -> gameController.Render()
                }
            }

            false -> {
                when (layout) {
                    0 -> gameController.Render()
                    1 -> driving1.Render()
                    2 -> driving2.Render()
                    3 -> casual.Render()
                    else -> gameController.Render()
                }
            }

            null -> {
                gameController.Render()
            }
        }


    }
}