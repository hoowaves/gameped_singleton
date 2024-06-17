package com.amuz.mobile_gamepad.module.activitys

import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.module.system.SystemRepository
import com.amuz.mobile_gamepad.module.widgets.commons.IsDarkService

interface IActivityController {
    val activityModel: IActivityModel
    val systemRepository: SystemRepository

    var appId: MutableLiveData<Int>
    var layout: MutableLiveData<Int>
    var isDark: MutableLiveData<Boolean>
    var touchEffect: MutableLiveData<Boolean>
    var powerSaving: MutableLiveData<Int>
    var isVibration: MutableLiveData<Boolean>

    val layoutId: MutableLiveData<Int>
    val name: MutableLiveData<String>
    val ltButton: MutableLiveData<Int>
    val lbButton: MutableLiveData<Int>
    val rtButton: MutableLiveData<Int>
    val rbButton: MutableLiveData<Int>
    val ltsButton: MutableLiveData<Int>
    val rtsButton: MutableLiveData<Int>
    val directionButton: MutableLiveData<Int>
    val yButton: MutableLiveData<Int>
    val bButton: MutableLiveData<Int>
    val xButton: MutableLiveData<Int>
    val aButton: MutableLiveData<Int>
    val isDefault: MutableLiveData<Boolean>

    suspend fun dataInit()
    suspend fun reset()
    suspend fun update()
}