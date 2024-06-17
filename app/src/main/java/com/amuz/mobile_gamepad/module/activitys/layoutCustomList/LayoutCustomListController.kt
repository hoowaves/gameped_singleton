package com.amuz.mobile_gamepad.module.activitys.layoutCustomList

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.module.activitys.casual.CasualControllerImpl
import com.amuz.mobile_gamepad.module.activitys.casual.CasualView
import com.amuz.mobile_gamepad.module.activitys.defaultMode.DefaultModeControllerImpl
import com.amuz.mobile_gamepad.module.activitys.defaultMode.DefaultModeView
import com.amuz.mobile_gamepad.module.activitys.driving1.Driving1ControllerImpl
import com.amuz.mobile_gamepad.module.activitys.driving1.Driving1View
import com.amuz.mobile_gamepad.module.activitys.driving2.Driving2ControllerImpl
import com.amuz.mobile_gamepad.module.activitys.driving2.Driving2View
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingEntity

class LayoutCustomListController(private val context: Context) {
    private val model: LayoutCustomListModel = LayoutCustomListModel(context)

    val defaultModeView = DefaultModeView()
    val driving1View = Driving1View()
    val driving2View = Driving2View()
    val casualView = CasualView()

    var defaultModeController: MutableLiveData<DefaultModeControllerImpl> = MutableLiveData()
    var driving1Controller: MutableLiveData<Driving1ControllerImpl> = MutableLiveData()
    var driving2Controller: MutableLiveData<Driving2ControllerImpl> = MutableLiveData()
    var casualController: MutableLiveData<CasualControllerImpl> = MutableLiveData()


    var appId: MutableLiveData<Int> = MutableLiveData()
    var layout: MutableLiveData<Int> = MutableLiveData()
    var isDark: MutableLiveData<Boolean> = MutableLiveData()
    var touchEffect: MutableLiveData<Boolean> = MutableLiveData()
    var powerSaving: MutableLiveData<Int> = MutableLiveData()
    var isVibration: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun dataInit() {
        model.dataInit()
        defaultModeController.value = DefaultModeControllerImpl(context)
        driving1Controller.value = Driving1ControllerImpl(context)
        driving2Controller.value = Driving2ControllerImpl(context)
        casualController.value = CasualControllerImpl(context)
        defaultModeController.value!!.dataInit()
        driving1Controller.value!!.dataInit()
        driving2Controller.value!!.dataInit()
        casualController.value!!.dataInit()

        this.appId.value = model.appId
        this.layout.value = model.layout
        this.isDark.value = model.isDark
        this.touchEffect.value = model.touchEffect
        this.powerSaving.value = model.powerSaving
        this.isVibration.value = model.isVibration

    }

    suspend fun update() {
        model.update(
            AppSettingEntity(
                id = 0,
                layout = this.layout.value ?: 0,
                isDark = this.isDark.value ?: true,
                touchEffect = this.touchEffect.value ?: false,
                powerSaving = this.powerSaving.value ?: 0,
                isVibration = this.isVibration.value ?: false,
            )
        )
    }

    fun reloadActivity() {
        context.startActivity(Intent(context, LayoutCustomListView::class.java))
    }

}