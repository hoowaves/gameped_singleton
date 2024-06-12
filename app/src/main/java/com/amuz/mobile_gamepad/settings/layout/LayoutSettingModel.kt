package com.amuz.mobile_gamepad.settings.layout

import androidx.lifecycle.MutableLiveData

class LayoutSettingModel(layoutSettingEntity: LayoutSettingEntity?) {

    val id: MutableLiveData<Int> = MutableLiveData()
    val name: MutableLiveData<String> = MutableLiveData()
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

    init{
        id.value = layoutSettingEntity?.id
        name.value = layoutSettingEntity?.name
        ltButton.value = layoutSettingEntity?.ltButton
        lbButton.value = layoutSettingEntity?.lbButton
        rtButton.value = layoutSettingEntity?.rtButton
        rbButton.value = layoutSettingEntity?.rbButton
        ltsButton.value = layoutSettingEntity?.ltsButton
        rtsButton.value = layoutSettingEntity?.rtsButton
        directionButton.value = layoutSettingEntity?.directionButton
        yButton.value = layoutSettingEntity?.yButton
        bButton.value = layoutSettingEntity?.bButton
        xButton.value = layoutSettingEntity?.xButton
        aButton.value = layoutSettingEntity?.aButton
    }


}