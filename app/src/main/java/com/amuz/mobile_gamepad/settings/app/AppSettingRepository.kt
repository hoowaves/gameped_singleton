package com.amuz.mobile_gamepad.settings.app

class AppSettingsRepository(private val appSettingDao: AppSettingDao) {

    suspend fun getSettings(): AppSettingEntity? {
        return appSettingDao.getSettings()
    }

    suspend fun saveSettings(settings: AppSettingEntity) {
        appSettingDao.insertSettings(settings)
    }

    suspend fun updateSettings(settings: AppSettingEntity) {
        appSettingDao.updateSettings(settings)
    }

    suspend fun deleteSettings(settings: AppSettingEntity) {
        appSettingDao.deleteSettings(settings)
    }
}