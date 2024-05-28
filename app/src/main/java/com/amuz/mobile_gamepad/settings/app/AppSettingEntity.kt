package com.amuz.mobile_gamepad.settings.app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_settings")
data class AppSettingEntity(
    @PrimaryKey
    val id: Int,
    val layout: Int,
    val isDark: Boolean,
    val touchEffect: Boolean,
    val powerSaving: Int,
    val isVibration: Boolean
)