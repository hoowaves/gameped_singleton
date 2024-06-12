package com.amuz.mobile_gamepad.settings.app

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
                touchEffect = false,
                powerSaving = 0,
                isVibration = false
            )
        )
    }
}