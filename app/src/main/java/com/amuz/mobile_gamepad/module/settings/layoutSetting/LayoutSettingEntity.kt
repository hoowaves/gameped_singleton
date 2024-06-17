package com.amuz.mobile_gamepad.module.settings.layoutSetting

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "layout_settings")
data class LayoutSettingEntity(
    @PrimaryKey
    var id: Int,
    var name: String,
    var ltButton: Int,
    var lbButton: Int,
    var rtButton: Int,
    var rbButton: Int,
    var ltsButton: Int,
    var rtsButton: Int,
    var directionButton: Int,
    var yButton: Int,
    var bButton: Int,
    var xButton: Int,
    var aButton: Int
)