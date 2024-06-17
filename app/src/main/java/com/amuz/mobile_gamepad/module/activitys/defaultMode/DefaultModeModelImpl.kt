package com.amuz.mobile_gamepad.module.activitys.defaultMode

import android.content.Context
import com.amuz.mobile_gamepad.module.activitys.IActivityModel
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingEntity
import com.amuz.mobile_gamepad.module.settings.database.AppDatabase
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingRepository
import com.amuz.mobile_gamepad.module.settings.layoutSetting.LayoutSettingEntity
import com.amuz.mobile_gamepad.module.settings.layoutSetting.LayoutSettingRepository

class DefaultModeModelImpl(context: Context) : IActivityModel {
    override val database = AppDatabase.getDatabase(context)
    override val appRepository = AppSettingRepository(database.appSettingDao())
    override val layoutRepository = LayoutSettingRepository(database.layoutSettingDao())

    override var appId: Int = 0
    override var layout: Int = 0
    override var isDark: Boolean = false
    override var touchEffect: Boolean = false
    override var powerSaving: Int = 0
    override var isVibration: Boolean = false

    override var layoutId: Int = 0
    override var name: String = ""
    override var ltButton: Int = 0
    override var lbButton: Int = 0
    override var rtButton: Int = 0
    override var rbButton: Int = 0
    override var ltsButton: Int = 0
    override var rtsButton: Int = 0
    override var directionButton: Int = 0
    override var yButton: Int = 0
    override var bButton: Int = 0
    override var xButton: Int = 0
    override var aButton: Int = 0

    override suspend fun dataInit() {
        val appData = appRepository.getSetting()
        val layoutData = layoutRepository.getSetting(0)

        if (appData != null) {
            this.appId = appData.id
            this.layout = appData.layout
            this.isDark = appData.isDark
            this.touchEffect = appData.touchEffect
            this.powerSaving = appData.powerSaving
            this.isVibration = appData.isVibration
        }

        if (layoutData != null) {
            this.layoutId = layoutData.id
            this.name = layoutData.name
            this.ltButton = layoutData.ltButton
            this.lbButton = layoutData.lbButton
            this.rtButton = layoutData.rtButton
            this.rbButton = layoutData.rbButton
            this.ltsButton = layoutData.ltsButton
            this.rtsButton = layoutData.rtsButton
            this.directionButton = layoutData.directionButton
            this.yButton = layoutData.yButton
            this.xButton = layoutData.xButton
            this.aButton = layoutData.aButton
        }
    }

    override suspend fun reset(layoutSettingEntity: LayoutSettingEntity) {
        layoutRepository.saveSetting(
            layoutSettingEntity
        )
    }

    override suspend fun dataInit(layoutId: Int) {}

    override suspend fun update(appSettingEntity: AppSettingEntity, layoutSettingEntity: LayoutSettingEntity) {}


}