package com.amuz.mobile_gamepad.modules.widgets.dialogs.displayDialog

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.settings.AppDatabase
import com.amuz.mobile_gamepad.settings.app.AppSettingEntity
import com.amuz.mobile_gamepad.settings.app.AppSettingModel
import com.amuz.mobile_gamepad.settings.app.AppSettingRepository


class DisplayDialogController(context: Context) {
    private val model = DisplayDialogModel(context)
    private val database = AppDatabase.getDatabase(context)
    private val repository = AppSettingRepository(database.appSettingDao())

    private val _viewIsDark: MutableLiveData<Boolean> = MutableLiveData(model.getIsDark())
    val viewIsDark: LiveData<Boolean> get() = _viewIsDark
    val viewThemeText: String get() = if (viewIsDark.value == true) "다크" else "라이트"

    private val _viewPowerSaving: MutableLiveData<Int> =
        MutableLiveData(model.getPowerSaving())
    val viewPowerSaving: LiveData<Int> get() = _viewPowerSaving
    val viewPowerSavingText: String get() = if (viewPowerSaving.value == 0) "안함" else "${viewPowerSaving.value}분"

    private val _viewTouchEffect: MutableLiveData<Boolean> =
        MutableLiveData(model.getTouchEffect())
    val viewTouchEffect: LiveData<Boolean> get() = _viewTouchEffect

    private val _viewIsVibration: MutableLiveData<Boolean> =
        MutableLiveData(model.getIsVibration())
    val viewIsVibration: LiveData<Boolean> get() = _viewIsVibration

    private var _viewBrightness: MutableLiveData<Int> =
        MutableLiveData(model.getBrightness())
    val viewBrightness: LiveData<Int> get() = _viewBrightness
    val defaultBrightness = model.getBrightness()

    private val _isChanged: MutableLiveData<Boolean> = MutableLiveData(false)
    val isChanged: LiveData<Boolean> get() = _isChanged

    init {
        viewIsDark.observeForever { value ->
            _isChanged.value = value != model.getIsDark()
        }
        viewPowerSaving.observeForever { value ->
            _isChanged.value = value != model.getPowerSaving()
        }
        viewTouchEffect.observeForever { value ->
            _isChanged.value = value != model.getTouchEffect()
        }
        viewIsVibration.observeForever { value ->
            _isChanged.value = value != model.getIsVibration()
        }
        viewBrightness.observeForever { value ->
            model.setBrightness(value)
            _isChanged.value = value != model.getBrightness()
        }
    }

    fun getModelIsDark(): Boolean? {
        return model.getIsDark()
    }

    fun setIsDark(value: Boolean) {
        _viewIsDark.value = value
    }

    fun getModelPowerSaving(): Int? {
        return model.getPowerSaving()
    }

    fun setViewPowerSaving(value: Int) {
        _viewPowerSaving.value = value
    }

    fun getModelTouchEffect(): Boolean? {
        return model.getTouchEffect()
    }

    fun setViewTouchEffect(value: Boolean) {
        _viewTouchEffect.value = value
    }

    fun getModelVibration(): Boolean? {
        return model.getIsVibration()
    }

    fun setViewVibration(value: Boolean) {
        _viewIsVibration.value = value
    }

    fun getModelBrightness(): Int {
        return model.getBrightness()
    }

    fun setModelBrightness(value: Int) {
        _viewBrightness.value = value
    }

    fun setDefaultBrightness() {
        model.setBrightness(defaultBrightness)
    }

    suspend fun update() {
        // 현재 페이지에 적용
        AppSettingModel.isDark.value = viewIsDark.value ?: true
        AppSettingModel.powerSaving.value = viewPowerSaving.value ?: 0
        AppSettingModel.touchEffect.value = viewTouchEffect.value ?: false
        AppSettingModel.isVibration.value = viewIsVibration.value ?: false
        repository.saveSetting(
            AppSettingEntity(
                id = 0,
                layout = AppSettingModel.layout.value ?: 0,
                isDark = viewIsDark.value ?: true,
                touchEffect = viewTouchEffect.value ?: false,
                powerSaving = viewPowerSaving.value ?: 0,
                isVibration = viewIsVibration.value ?: false,
            )
        )
    }
}