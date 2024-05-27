package com.amuz.mobile_gamepad.settings.app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_settings")
data class AppSettingEntity(
    @PrimaryKey
    val id: Int = 1,
    val appLayout: Int,
    val appIsDark: Boolean,
    val appTouchEffect: Boolean,
    val appPowerSaving: Int,
    val appIsVibration: Boolean
)