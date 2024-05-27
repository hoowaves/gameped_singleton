package com.amuz.mobile_gamepad.modules.widgets.dialogs.displayDialog

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.constants.Theme
import com.amuz.mobile_gamepad.modules.home.HomeController


class DisplayDialogController(context: Context) {
    private val displayDialogModel = DisplayDialogModel(context)

    private val _viewTheme: MutableLiveData<Theme> = MutableLiveData(displayDialogModel.getTheme())
    val viewTheme: LiveData<Theme> get() = _viewTheme
    val viewThemeText: String get() = if (viewTheme.value == Theme.DARK) "다크" else "라이트"

    private val _viewPowerSaving: MutableLiveData<Int> =
        MutableLiveData(displayDialogModel.getPowerSaving())
    val viewPowerSaving: LiveData<Int> get() = _viewPowerSaving
    val viewPowerSavingText: String get() = if (viewPowerSaving.value == 0) "안함" else "${viewPowerSaving.value}분"

    private val _viewTouchEffect: MutableLiveData<Boolean> =
        MutableLiveData(displayDialogModel.getTouchEffect())
    val viewTouchEffect: LiveData<Boolean> get() = _viewTouchEffect

    private val _viewIsVibration: MutableLiveData<Boolean> =
        MutableLiveData(displayDialogModel.getIsVibration())
    val viewIsVibration: LiveData<Boolean> get() = _viewIsVibration

    private var _viewBrightness: MutableLiveData<Int> =
        MutableLiveData(displayDialogModel.getBrightness())
    val viewBrightness: LiveData<Int> get() = _viewBrightness
    val defaultBrightness = displayDialogModel.getBrightness()

    private val _isChanged: MutableLiveData<Boolean> = MutableLiveData(false)
    val isChanged: LiveData<Boolean> get() = _isChanged

    init {
        viewTheme.observeForever { theme ->
            _isChanged.value = theme != displayDialogModel.getTheme()
        }
        viewPowerSaving.observeForever { value ->
            _isChanged.value = value != displayDialogModel.getPowerSaving()
        }
        viewTouchEffect.observeForever { value ->
            _isChanged.value = value != displayDialogModel.getTouchEffect()
        }
        viewIsVibration.observeForever { value ->
            _isChanged.value = value != displayDialogModel.getIsVibration()
        }
        viewBrightness.observeForever { value ->
            displayDialogModel.setBrightness(value)
            _isChanged.value = value != displayDialogModel.getBrightness()
        }
    }

    fun getModelTheme(): Theme? {
        return displayDialogModel.getTheme()
    }

    fun setViewTheme(theme: Theme) {
        _viewTheme.value = theme
    }

    fun getModelPowerSaving(): Int {
        return displayDialogModel.getPowerSaving()
    }

    fun setViewPowerSaving(value: Int) {
        _viewPowerSaving.value = value
    }

    fun getModelTouchEffect(): Boolean {
        return displayDialogModel.getTouchEffect()
    }

    fun setViewTouchEffect(value: Boolean) {
        _viewTouchEffect.value = value
    }

    fun getModelVibration(): Boolean {
        return displayDialogModel.getIsVibration()
    }

    fun setViewVibration(value: Boolean) {
        _viewIsVibration.value = value
    }

    fun getModelBrightness(): Int {
        return displayDialogModel.getBrightness()
    }

    fun setModelBrightness(value: Int) {
        _viewBrightness.value = value
    }

    fun setDefaultBrightness() {
        displayDialogModel.setBrightness(defaultBrightness)
    }

    fun update() {
        // 다른 요소 추가해주기
        HomeController.setLayoutTheme(viewTheme.value ?: Theme.DARK)
        // 현재 페이지에 적용
        HomeController.setIsVibrator(viewIsVibration.value ?: false)
        if (viewIsVibration.value == true) {
            displayDialogModel.enableIsVibration()
        } else {
            displayDialogModel.disableIsVibration()
        }
    }

}