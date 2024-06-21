package com.amuz.mobile_gamepad.module.widgets.dialogs.displayDialog

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.module.activitys.casual.CasualView
import com.amuz.mobile_gamepad.module.activitys.defaultMode.DefaultModeView
import com.amuz.mobile_gamepad.module.activitys.driving1.Driving1View
import com.amuz.mobile_gamepad.module.activitys.driving2.Driving2View
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingEntity


class DisplayDialogController(private val context: Context) {
    private val model = DisplayDialogModel(context)

    var id: MutableLiveData<Int> = MutableLiveData()
    var layout: MutableLiveData<Int> = MutableLiveData()
    var isDark: MutableLiveData<Boolean> = MutableLiveData()
    var touchEffect: MutableLiveData<Boolean> = MutableLiveData()
    var powerSaving: MutableLiveData<Int> = MutableLiveData()
    var isVibration: MutableLiveData<Boolean> = MutableLiveData()
    var brightness: MutableLiveData<Int> = MutableLiveData()

    val isDarkText: String get() = if (isDark.value == true) "Dark" else "Light"
    val powerSavingText: String get() = if (powerSaving.value == 0) "Off" else "${powerSaving.value}min"
    val isChanged: MutableLiveData<Boolean> = MutableLiveData()

    suspend fun dataInit() {
        model.dataInit()
        this.id.value = model.id
        this.layout.value = model.layout
        this.isDark.value = model.isDark
        this.touchEffect.value = model.touchEffect
        this.powerSaving.value = model.powerSaving
        this.isVibration.value = model.isVibration
        this.brightness.value = model.brightness
    }

    fun getModelBrightness(): Int {
        return model.brightness
    }

    fun isChangedUpdate() {
        this.isChanged.value = isDark.value != model.isDark ||
                touchEffect.value != model.touchEffect ||
                powerSaving.value != model.powerSaving ||
                isVibration.value != model.isVibration ||
                brightness.value != model.brightness
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

    fun routeDefaultMode() {
        context.startActivity(Intent(context, DefaultModeView::class.java))
    }

    fun routeDriving1() {
        context.startActivity(Intent(context, Driving1View::class.java))
    }

    fun routeDriving2() {
        context.startActivity(Intent(context, Driving2View::class.java))
    }

    fun routeCasual() {
        context.startActivity(Intent(context, CasualView::class.java))
    }

}