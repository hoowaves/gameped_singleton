package com.amuz.mobile_gamepad.module.splash

import android.content.Context
import com.amuz.mobile_gamepad.module.settings.database.AppDatabase
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingRepository
import com.amuz.mobile_gamepad.module.settings.layoutSetting.LayoutSettingRepository

class SplashModel(context: Context) {
    private val database = AppDatabase.getDatabase(context)
    private val appRepository = AppSettingRepository(database.appSettingDao())
    private val layoutRepository = LayoutSettingRepository(database.layoutSettingDao())

    fun getAppRepository(): AppSettingRepository {
        return this.appRepository
    }

    fun getLayoutRepository(): LayoutSettingRepository {
        return this.layoutRepository
    }
}