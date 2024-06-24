package com.amuz.mobile_gamepad.module.splash

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingEntity
import com.amuz.mobile_gamepad.module.settings.appSetting.AppSettingRepository
import com.amuz.mobile_gamepad.module.settings.layoutSetting.LayoutSettingRepository
import com.amuz.mobile_gamepad.module.activitys.casual.CasualView
import com.amuz.mobile_gamepad.module.activitys.defaultMode.DefaultModeView
import com.amuz.mobile_gamepad.module.activitys.driving1.Driving1View
import com.amuz.mobile_gamepad.module.activitys.driving2.Driving2View

class SplashController(private val context: Context) {
    private val splashModel = SplashModel(context)
    private val appSettingRepository: AppSettingRepository = splashModel.getAppRepository()
    private val layoutSettingRepository: LayoutSettingRepository = splashModel.getLayoutRepository()
    var appSettingEntity: MutableLiveData<AppSettingEntity> = MutableLiveData()

    suspend fun dataInit() {
        this.appSettingEntity.value = appSettingRepository.getSetting()
    }

    // app_settings table init
    suspend fun appInit() {
        if (appSettingRepository.getSetting() == null) {
            appSettingRepository.createDefault()
        }
    }

    // layout_settings table init
    suspend fun layoutInit() {
        if (layoutSettingRepository.getSetting(0) == null) {
            layoutSettingRepository.createDefaultGameController()
        }
        if (layoutSettingRepository.getSetting(1) == null) {
            layoutSettingRepository.createDefaultDriving1()
        }
        if (layoutSettingRepository.getSetting(2) == null) {
            layoutSettingRepository.createDefaultDriving2()
        }
        if (layoutSettingRepository.getSetting(3) == null) {
            layoutSettingRepository.createDefaultCasual()
        }
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