package com.amuz.mobile_gamepad.modules.home

import android.content.Context
import com.amuz.mobile_gamepad.settings.AppDatabase
import com.amuz.mobile_gamepad.settings.app.AppSettingEntity
import com.amuz.mobile_gamepad.settings.app.AppSettingsRepository
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingEntity
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingRepository
import kotlinx.coroutines.runBlocking

class HomeModel(context: Context) {
    private val database = AppDatabase.getDatabase(context)
    private val appRepository = AppSettingsRepository(database.appSettingDao())
    private var layoutRepository = LayoutSettingRepository(database.layoutSettingDao())
    private var appSetting: AppSettingEntity? = null

    init {
        runBlocking {
            appSetting = appRepository.getSetting()
        }
    }

    fun getAppRepository(): AppSettingsRepository {
        return this.appRepository
    }

    fun getAppSetting(): AppSettingEntity? {
        return this.appSetting
    }

    fun getLayoutRepository(): LayoutSettingRepository {
        return this.layoutRepository
    }
}