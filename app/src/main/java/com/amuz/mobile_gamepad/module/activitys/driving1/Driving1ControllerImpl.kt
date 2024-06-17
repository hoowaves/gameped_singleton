package com.amuz.mobile_gamepad.module.activitys.driving1

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.activitys.IActivityModel
import com.amuz.mobile_gamepad.module.settings.layoutSetting.LayoutSettingEntity
import com.amuz.mobile_gamepad.module.system.SystemRepository
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService

class Driving1ControllerImpl(context: Context) : IActivityController {
    override val activityModel: IActivityModel = Driving1ModelImpl(context)
    override val systemRepository: SystemRepository = SystemRepository(context)

    override var appId: MutableLiveData<Int> = MutableLiveData()
    override var layout: MutableLiveData<Int> = MutableLiveData()
    override var isDark: MutableLiveData<Boolean> = MutableLiveData()
    override var touchEffect: MutableLiveData<Boolean> = MutableLiveData()
    override var powerSaving: MutableLiveData<Int> = MutableLiveData()
    override var isVibration: MutableLiveData<Boolean> = MutableLiveData()

    override val layoutId: MutableLiveData<Int> = MutableLiveData()
    override val name: MutableLiveData<String> = MutableLiveData()
    override var ltButton: MutableLiveData<Int> = MutableLiveData()
    override var lbButton: MutableLiveData<Int> = MutableLiveData()
    override var rtButton: MutableLiveData<Int> = MutableLiveData()
    override var rbButton: MutableLiveData<Int> = MutableLiveData()
    override var ltsButton: MutableLiveData<Int> = MutableLiveData()
    override var rtsButton: MutableLiveData<Int> = MutableLiveData()
    override var directionButton: MutableLiveData<Int> = MutableLiveData()
    override var yButton: MutableLiveData<Int> = MutableLiveData()
    override var bButton: MutableLiveData<Int> = MutableLiveData()
    override var xButton: MutableLiveData<Int> = MutableLiveData()
    override var aButton: MutableLiveData<Int> = MutableLiveData()
    override var isDefault: MutableLiveData<Boolean> = MutableLiveData()

    override suspend fun dataInit() {
        activityModel.dataInit()

        this.appId.value = activityModel.appId
        this.layout.value = activityModel.layout
        this.isDark.value = activityModel.isDark
        this.touchEffect.value = activityModel.touchEffect
        this.powerSaving.value = activityModel.powerSaving
        this.isVibration.value = activityModel.isVibration

        this.layoutId.value = activityModel.layoutId
        this.name.value = activityModel.name
        this.ltButton.value = activityModel.ltButton
        this.lbButton.value = activityModel.lbButton
        this.rtButton.value = activityModel.rtButton
        this.rbButton.value = activityModel.rbButton
        this.ltsButton.value = activityModel.ltsButton
        this.rtsButton.value = activityModel.rtsButton
        this.directionButton.value = activityModel.directionButton
        this.yButton.value = activityModel.yButton
        this.bButton.value = activityModel.bButton
        this.xButton.value = activityModel.xButton
        this.aButton.value = activityModel.aButton
        this.isDefault.value = listOf(
            activityModel.ltButton,
            activityModel.lbButton,
            activityModel.rtButton,
            activityModel.rbButton,
            activityModel.ltsButton,
            activityModel.rtsButton,
            activityModel.directionButton,
            activityModel.yButton,
            activityModel.bButton,
            activityModel.xButton,
            activityModel.aButton
        ).all { it == 0 }
    }

    override suspend fun reset() {
        activityModel.reset(
            LayoutSettingEntity(
                id = 1,
                name = "Driving 1",
                ltButton = 0,
                lbButton = 0,
                rtButton = 0,
                rbButton = 0,
                ltsButton = 0,
                rtsButton = 0,
                directionButton = 0,
                yButton = 0,
                bButton = 0,
                xButton = 0,
                aButton = 0,
            )
        )
    }
    override suspend fun update() { }
}