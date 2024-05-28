package com.amuz.mobile_gamepad.settings.layout

class LayoutSettingRepository(private val layoutSettingDao: LayoutSettingDao) {

    suspend fun getSetting(id :Int): LayoutSettingEntity? {
        return layoutSettingDao.getLayoutById(id)
    }

    suspend fun saveSetting(setting: LayoutSettingEntity) {
        layoutSettingDao.insert(setting)
    }
}