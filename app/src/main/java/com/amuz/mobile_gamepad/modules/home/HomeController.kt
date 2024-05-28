package com.amuz.mobile_gamepad.modules.home

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.modules.layoutSetting.LayoutSettingController
import com.amuz.mobile_gamepad.settings.app.AppSettingEntity
import com.amuz.mobile_gamepad.settings.app.AppSettingsRepository
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingEntity
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingRepository
import kotlinx.coroutines.runBlocking

object HomeController {
    var appRepository: MutableLiveData<AppSettingsRepository> = MutableLiveData()
    var layoutRepository: MutableLiveData<LayoutSettingRepository> = MutableLiveData()

    val appLayout: MutableLiveData<Int> = MutableLiveData()
    var appIsDark: MutableLiveData<Boolean> = MutableLiveData()
    var appTouchEffect: MutableLiveData<Boolean> = MutableLiveData()
    var appPowerSaving: MutableLiveData<Int> = MutableLiveData()
    var appIsVibration: MutableLiveData<Boolean> = MutableLiveData()

    val layoutEntity: MutableLiveData<LayoutSettingEntity> = MutableLiveData()
    var name: MutableLiveData<String> = MutableLiveData()
    var ltButton: MutableLiveData<Int> = MutableLiveData()
    var lbButton: MutableLiveData<Int> = MutableLiveData()
    var rtButton: MutableLiveData<Int> = MutableLiveData()
    var rbButton: MutableLiveData<Int> = MutableLiveData()
    var ltsButton: MutableLiveData<Int> = MutableLiveData()
    var rtsButton: MutableLiveData<Int> = MutableLiveData()
    var directionButton: MutableLiveData<Int> = MutableLiveData()
    var yButton: MutableLiveData<Int> = MutableLiveData()
    var bButton: MutableLiveData<Int> = MutableLiveData()
    var xButton: MutableLiveData<Int> = MutableLiveData()
    var aButton: MutableLiveData<Int> = MutableLiveData()

    fun init(homeModel: HomeModel) {
        this.appRepository.value = homeModel.getAppRepository()
        this.layoutRepository.value = homeModel.getLayoutRepository()
        this.appLayout.value = homeModel.getAppSetting()?.layout
        this.appIsDark.value = homeModel.getAppSetting()?.isDark
        this.appTouchEffect.value = homeModel.getAppSetting()?.touchEffect
        this.appPowerSaving.value = homeModel.getAppSetting()?.powerSaving
        this.appIsVibration.value = homeModel.getAppSetting()?.isVibration
    }

    init {
        appLayout.observeForever { value ->
            runBlocking {
                layoutEntity.value = layoutRepository.value?.getSetting(value)
            }
            this.name.value = layoutEntity.value?.name
            this.ltButton.value = layoutEntity.value?.ltButton
            this.lbButton.value = layoutEntity.value?.lbButton
            this.rtButton.value = layoutEntity.value?.rtButton
            this.rbButton.value = layoutEntity.value?.rbButton
            this.ltsButton.value = layoutEntity.value?.ltsButton
            this.rtsButton.value = layoutEntity.value?.rtsButton
            this.directionButton.value = layoutEntity.value?.directionButton
            this.yButton.value = layoutEntity.value?.yButton
            this.bButton.value = layoutEntity.value?.bButton
            this.xButton.value = layoutEntity.value?.xButton
            this.aButton.value = layoutEntity.value?.aButton
        }
    }

    suspend fun saveAppSetting(value: AppSettingEntity) {
        appRepository.value?.saveSetting(value)
    }

    suspend fun saveLayoutSetting(value: LayoutSettingEntity){
        layoutRepository.value?.saveSetting(value)
    }

    suspend fun getLayoutSetting(id: Int): LayoutSettingEntity? {
        return layoutRepository.value?.getSetting(id)
    }

    fun setLayout(value: Int) {
        appLayout.postValue(value)
    }

    fun setIsDark(value: Boolean) {
        appIsDark.postValue(value)
    }

    fun setIsTouchEffect(value: Boolean) {
        appTouchEffect.postValue(value)
    }

    fun setPowerSaving(value: Int) {
        appPowerSaving.postValue(value)
    }

    fun setIsVibration(value: Boolean) {
        appIsVibration.postValue(value)
    }

    fun setLtButton(value: Int) {
        ltButton.postValue(value)
    }

    fun setLbButton(value: Int) {
        lbButton.postValue(value)
    }

    fun setRtButton(value: Int) {
        rtButton.postValue(value)
    }

    fun setRbButton(value: Int) {
        rbButton.postValue(value)
    }

    fun setLtsButton(value: Int) {
        ltsButton.postValue(value)
    }

    fun setRtsButton(value: Int) {
        rtsButton.postValue(value)
    }

    fun setDirectionButton(value: Int) {
        directionButton.postValue(value)
    }

    fun setYButton(value: Int) {
        yButton.postValue(value)
    }

    fun setBButton(value: Int) {
        bButton.postValue(value)
    }


    fun setXButton(value: Int) {
        xButton.postValue(value)
    }

    fun setAButton(value: Int) {
        aButton.postValue(value)
    }

    fun getBackgroundColor(): Color {
        return if (appIsDark.value == true) {
            AppColor.DarkMode.backgroundColor
        } else {
            AppColor.LightMode.backgroundColor
        }
    }

    fun getButtonColor(): Color {
        return if (appIsDark.value == true) {
            AppColor.DarkMode.buttonColor
        } else {
            AppColor.LightMode.buttonColor
        }
    }

    fun getBorderColor(): Color {
        return if (appIsDark.value == true) {
            AppColor.DarkMode.borderColor
        } else {
            AppColor.LightMode.borderColor
        }
    }

    fun getTextColor(): Color {
        return if (appIsDark.value == true) {
            AppColor.DarkMode.textColor
        } else {
            AppColor.LightMode.textColor
        }
    }

}