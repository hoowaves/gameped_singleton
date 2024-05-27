package com.amuz.mobile_gamepad.modules.home


import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.constants.AppColor
import com.amuz.mobile_gamepad.constants.Theme

object HomeController {
    // 앱 실행 중 상태
    private val model = HomeModel()

    private val _layoutState: MutableLiveData<String> = MutableLiveData(model.getState())
    val layoutState: LiveData<String> get() = _layoutState
    private val _layoutTheme: MutableLiveData<Theme> = MutableLiveData(model.getTheme())
    val layoutTheme: LiveData<Theme> get() = _layoutTheme
    private val _IsVibrator: MutableLiveData<Boolean> = MutableLiveData(model.getIsVibrator())
    val IsVibrator: LiveData<Boolean> get() = _IsVibrator

    fun getLayoutState(): String {
        return _layoutState.value ?: ""
    }

    fun setLayoutState(state: String) {
        _layoutState.postValue(state)
    }

    fun getLayoutTheme(): Theme? {
        return _layoutTheme.value
    }

    fun setLayoutTheme(theme: Theme) {
        _layoutTheme.postValue(theme)
    }

    fun getIsVibrator(): Boolean? {
        return _IsVibrator.value
    }

    fun setIsVibrator(value: Boolean) {
        _IsVibrator.value = value
    }

    fun getBackgroundColor(): Color {
        return if (_layoutTheme.value == Theme.DARK) {
            AppColor.DarkMode.backgroundColor
        } else {
            AppColor.LightMode.backgroundColor
        }
    }

    fun getButtonColor(): Color {
        return if (_layoutTheme.value == Theme.DARK) {
            AppColor.DarkMode.buttonColor
        } else {
            AppColor.LightMode.buttonColor
        }
    }

    fun getBorderColor(): Color {
        return if (_layoutTheme.value == Theme.DARK) {
            AppColor.DarkMode.borderColor
        } else {
            AppColor.LightMode.borderColor
        }
    }

    fun getTextColor(): Color {
        return if (_layoutTheme.value == Theme.DARK) {
            AppColor.DarkMode.textColor
        } else {
            AppColor.LightMode.textColor
        }
    }
}
