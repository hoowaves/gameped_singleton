package com.amuz.mobile_gamepad.module.widgets.dialogs.displayDialog

import android.content.Context
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingEntity
import com.amuz.mobile_gamepad.module.settings.database.AppDatabase
import com.amuz.mobile_gamepad.module.system.SystemRepository
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingRepository

class DisplayDialogModel(context: Context) {
    private val database = AppDatabase.getDatabase(context)
    private val appRepository = AppSettingRepository(database.appSettingDao())
    private val systemRepository = SystemRepository(context)

    var id: Int = 0
    var layout: Int = 0
    var isDark: Boolean = false
    var touchEffect: Boolean = false
    var powerSaving: Int = 0
    var isVibration: Boolean = false
    var brightness: Int = 0

    suspend fun dataInit() {
        val appData = appRepository.getSetting()

        if (appData != null) {
            this.id = appData.id
            this.layout = appData.layout
            this.isDark = appData.isDark
            this.touchEffect = appData.touchEffect
            this.powerSaving = appData.powerSaving
            this.isVibration = appData.isVibration
            this.brightness = systemRepository.getBrightness()
        }
    }

    suspend fun update(appSettingEntity: AppSettingEntity) {
        appRepository.saveSetting(
            appSettingEntity
        )
    }
}