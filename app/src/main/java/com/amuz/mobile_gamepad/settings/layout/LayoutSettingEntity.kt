package com.amuz.mobile_gamepad.settings.layout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "layout_settings")
data class LayoutSettingEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val ltButton: Int,
    val lbButton: Int,
    val rtButton: Int,
    val rbButton: Int,
    val ltsButton: Int,
    val rtsButton: Int,
    val directionButton: Int,
    val yButton: Int,
    val bButton: Int,
    val xButton: Int,
    val aButton: Int
)