package com.amuz.mobile_gamepad.module.activitys.layoutCustomList

import android.content.Context
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingEntity
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingRepository
import com.amuz.mobile_gamepad.module.settings.database.AppDatabase

class LayoutCustomListModel(context: Context) {
    val database = AppDatabase.getDatabase(context)
    val appRepository = AppSettingRepository(database.appSettingDao())

    var appId: Int = 0
    var layout: Int = 0
    var isDark: Boolean = false
    var touchEffect: Boolean = false
    var powerSaving: Int = 0
    var isVibration: Boolean = false

    suspend fun dataInit() {
        val appData = appRepository.getSetting()
        if (appData != null) {
            this.appId = appData.id
            this.layout = appData.layout
            this.isDark = appData.isDark
            this.touchEffect = appData.touchEffect
            this.powerSaving = appData.powerSaving
            this.isVibration = appData.isVibration
        }
    }

    suspend fun update(appSettingEntity: AppSettingEntity) {
        appRepository.saveSetting(
            appSettingEntity
        )
    }

}