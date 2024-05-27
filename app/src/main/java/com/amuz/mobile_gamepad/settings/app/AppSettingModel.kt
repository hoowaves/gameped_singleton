package com.amuz.mobile_gamepad.settings.app

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppSettingsModel(private val repository: AppSettingsRepository) {
    private var _settings: AppSettingEntity? = null
    val settings: AppSettingEntity?
        get() = _settings

    suspend fun loadSettings() {
        withContext(Dispatchers.IO) {
            _settings = repository.getSettings()
        }
    }

    suspend fun saveSettings(settings: AppSettingEntity) {
        withContext(Dispatchers.IO) {
            repository.saveSettings(settings)
            _settings = settings
        }
    }

    suspend fun updateSettings(settings: AppSettingEntity) {
        withContext(Dispatchers.IO) {
            repository.updateSettings(settings)
            _settings = settings
        }
    }

    suspend fun deleteSettings(settings: AppSettingEntity) {
        withContext(Dispatchers.IO) {
            repository.deleteSettings(settings)
            _settings = null
        }
    }
}