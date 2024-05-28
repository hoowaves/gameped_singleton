package com.amuz.mobile_gamepad.settings.app

class AppSettingsRepository(private val appSettingDao: AppSettingDao) {
    suspend fun getSetting(): AppSettingEntity? {
        return appSettingDao.getSetting()
    }

    suspend fun saveSetting(setting: AppSettingEntity) {
        appSettingDao.insert(setting)
    }
}