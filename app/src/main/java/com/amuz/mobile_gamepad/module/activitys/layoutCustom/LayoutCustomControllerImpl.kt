package com.amuz.mobile_gamepad.module.activitys.layoutCustom

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.module.activitys.IActivityController
import com.amuz.mobile_gamepad.module.activitys.IActivityModel
import com.amuz.mobile_gamepad.module.activitys.casual.CasualView
import com.amuz.mobile_gamepad.module.activitys.defaultMode.DefaultModeView
import com.amuz.mobile_gamepad.module.activitys.driving1.Driving1View
import com.amuz.mobile_gamepad.module.activitys.driving2.Driving2View
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingEntity
import com.amuz.mobile_gamepad.module.settings.layoutSetting.LayoutSettingEntity
import com.amuz.mobile_gamepad.module.system.SystemRepository

class LayoutCustomControllerImpl(context: Context) : IActivityController {
    override val activityModel: IActivityModel = LayoutCustomModelImpl(context)
    override val systemRepository: SystemRepository = SystemRepository(context)

    val defaultModeView = DefaultModeView()
    val driving1View = Driving1View()
    val driving2View = Driving2View()
    val casualView = CasualView()

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

    suspend fun dataInit(layoutId: Int) {
        activityModel.dataInit(layoutId)
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
        this.isDefault.value = activityModel.layout == layoutId
    }

    fun isChanged(): Boolean {
        return (this.layout.value != activityModel.layout
                || this.ltButton.value != activityModel.ltButton
                || this.lbButton.value != activityModel.lbButton
                || this.rtButton.value != activityModel.rtButton
                || this.rbButton.value != activityModel.rbButton
                || this.ltsButton.value != activityModel.ltsButton
                || this.rtsButton.value != activityModel.rtsButton
                || this.yButton.value != activityModel.yButton
                || this.bButton.value != activityModel.bButton
                || this.xButton.value != activityModel.xButton
                || this.aButton.value != activityModel.aButton
                || this.upButton.value != activityModel.upButton
                || this.rightButton.value != activityModel.rightButton
                || this.downButton.value != activityModel.downButton
                || this.leftButton.value != activityModel.leftButton
                )
    }

    fun defaultLayout() {
        // Set as default 체크 해제시
        this.layout.value = 0
    }

    override suspend fun update() {
        activityModel.update(
            AppSettingEntity(
                id = 0,
                layout = this.layout.value ?: 0,
                isDark = this.isDark.value ?: true,
                touchEffect = this.touchEffect.value ?: false,
                powerSaving = this.powerSaving.value ?: 0,
                isVibration = this.isVibration.value ?: false,
            ),
            LayoutSettingEntity(
                id = this.layoutId.value ?: 0,
                name = this.name.value ?: "",
                ltButton = this.ltButton.value ?: 0,
                lbButton = this.lbButton.value ?: 0,
                rtButton = this.rtButton.value ?: 0,
                rbButton = this.rbButton.value ?: 0,
                ltsButton = this.ltsButton.value ?: 0,
                rtsButton = this.rtsButton.value ?: 0,
                yButton = this.yButton.value ?: 0,
                bButton = this.bButton.value ?: 0,
                xButton = this.xButton.value ?: 0,
                aButton = this.aButton.value ?: 0,
                upButton = this.upButton.value ?: 0,
                rightButton = this.rightButton.value ?: 0,
                downButton = this.downButton.value ?: 0,
                leftButton = this.leftButton.value ?: 0
            )
        )
    }

    override suspend fun dataInit() {
    }

    override suspend fun reset() {
    }


}