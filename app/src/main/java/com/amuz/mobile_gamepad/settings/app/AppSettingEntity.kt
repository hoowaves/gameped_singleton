package com.amuz.mobile_gamepad.settings.app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_settings")
data class AppSettingEntity(
    @PrimaryKey
    val id: Int,
    val layout: Int,
    var isDark: Boolean,
    var touchEffect: Boolean,
    var powerSaving: Int,
    var isVibration: Boolean
)