package com.amuz.mobile_gamepad.module.activitys.casual

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.activitys.IActivityModel
import com.amuz.mobile_gamepad.module.activitys.layoutCustom.LayoutCustomView
import com.amuz.mobile_gamepad.module.activitys.layoutCustomList.LayoutCustomListView
import com.amuz.mobile_gamepad.module.settings.layoutSetting.LayoutSettingEntity
import com.amuz.mobile_gamepad.module.system.SystemRepository

class CasualControllerImpl(context: Context) : IActivityController {
    override val activityModel: IActivityModel = CasualModelImpl(context)
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
    override var yButton: MutableLiveData<Int> = MutableLiveData()
    override var bButton: MutableLiveData<Int> = MutableLiveData()
    override var xButton: MutableLiveData<Int> = MutableLiveData()
    override var aButton: MutableLiveData<Int> = MutableLiveData()
    override var upButton: MutableLiveData<Int> = MutableLiveData()
    override var rightButton: MutableLiveData<Int> = MutableLiveData()
    override var downButton: MutableLiveData<Int> = MutableLiveData()
    override var leftButton: MutableLiveData<Int> = MutableLiveData()
    override var isDefault: MutableLiveData<Boolean> = MutableLiveData()

    override var isGuide: MutableLiveData<Boolean> = MutableLiveData()
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
        this.yButton.value = activityModel.yButton
        this.bButton.value = activityModel.bButton
        this.xButton.value = activityModel.xButton
        this.aButton.value = activityModel.aButton
        this.upButton.value = activityModel.upButton
        this.rightButton.value = activityModel.rightButton
        this.downButton.value = activityModel.downButton
        this.leftButton.value = activityModel.leftButton
        this.isDefault.value = listOf(
            activityModel.ltButton,
            activityModel.lbButton,
            activityModel.rtButton,
            activityModel.rbButton,
            activityModel.ltsButton,
            activityModel.rtsButton,
            activityModel.upButton,
            activityModel.rightButton,
            activityModel.downButton,
            activityModel.leftButton,
            activityModel.yButton,
            activityModel.bButton,
            activityModel.xButton,
            activityModel.aButton
        ).all { it == 0 }

        this.isGuide.value = false

    }

    override suspend fun reset() {
        activityModel.reset(
            LayoutSettingEntity(
                id = 3,
                name = "Casual",
                ltButton = 0,
                lbButton = 0,
                rtButton = 0,
                rbButton = 0,
                ltsButton = 0,
                rtsButton = 0,
                yButton = 0,
                bButton = 0,
                xButton = 0,
                aButton = 0,
                upButton = 0,
                rightButton = 0,
                downButton = 0,
                leftButton = 0
            )
        )
    }

    override suspend fun update() { }
}