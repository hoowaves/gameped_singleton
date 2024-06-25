package com.amuz.mobile_gamepad.module.activitys

import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingEntity
import com.amuz.mobile_gamepad.module.settings.database.AppDatabase
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingRepository
import com.amuz.mobile_gamepad.module.settings.layoutSetting.LayoutSettingEntity
import com.amuz.mobile_gamepad.module.settings.layoutSetting.LayoutSettingRepository

interface IActivityModel {
    val database: AppDatabase
    val appRepository: AppSettingRepository
    val layoutRepository: LayoutSettingRepository

    var appId: Int
    var layout: Int
    var isDark: Boolean
    var touchEffect: Boolean
    var powerSaving: Int
    var isVibration: Boolean

    var layoutId: Int
    var name: String
    var ltButton: Int
    var lbButton: Int
    var rtButton: Int
    var rbButton: Int
    var ltsButton: Int
    var rtsButton: Int
    var yButton: Int
    var bButton: Int
    var xButton: Int
    var aButton: Int
    var upButton: Int
    var rightButton: Int
    var downButton: Int
    var leftButton: Int

    suspend fun dataInit()
    suspend fun dataInit(layoutId: Int)
    suspend fun reset(layoutSettingEntity: LayoutSettingEntity)
    suspend fun update(appSettingEntity: AppSettingEntity, layoutSettingEntity: LayoutSettingEntity)
}