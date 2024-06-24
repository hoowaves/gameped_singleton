package com.amuz.mobile_gamepad.module.settings.appSetting

import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingDao
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingEntity

class AppSettingRepository(private val appSettingDao: AppSettingDao) {
    suspend fun getSetting(): AppSettingEntity? {
        return appSettingDao.getSetting()
    }

    suspend fun saveSetting(setting: AppSettingEntity) {
        appSettingDao.insert(setting)
    }

    suspend fun createDefault() {
        appSettingDao.insert(
            AppSettingEntity(
                id = 0,
                layout = 0,
                isDark = true,
                touchEffect = true,
                powerSaving = 0,
                isVibration = true
            )
        )
    }
}