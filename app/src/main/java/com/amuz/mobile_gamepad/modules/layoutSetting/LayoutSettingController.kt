package com.amuz.mobile_gamepad.modules.layoutSetting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingEntity
import kotlinx.coroutines.runBlocking

object LayoutSettingController {
    private var layoutEntity: MutableLiveData<LayoutSettingEntity> = MutableLiveData()

    private val _appLayout: MutableLiveData<Int> = MutableLiveData()
    val appLayout: LiveData<Int> get() = _appLayout
    private val name: MutableLiveData<String> = MutableLiveData()

    private val selectedLayout: MutableLiveData<LayoutSettingEntity> = MutableLiveData()

    private var _viewLtButton: MutableLiveData<Int> = MutableLiveData()
    val viewLtButton: LiveData<Int> get() = _viewLtButton

    private val _viewLbButton: MutableLiveData<Int> = MutableLiveData()
    val viewLbButton: LiveData<Int> get() = _viewLbButton

    private val _viewRtButton: MutableLiveData<Int> = MutableLiveData()
    val viewRtButton: LiveData<Int> get() = _viewRtButton

    private val _viewRbButton: MutableLiveData<Int> = MutableLiveData()
    val viewRbButton: LiveData<Int> get() = _viewRbButton

    private val _viewLtsButton: MutableLiveData<Int> = MutableLiveData()
    val viewLtsButton: LiveData<Int> get() = _viewLtsButton

    private val _viewRtsButton: MutableLiveData<Int> = MutableLiveData()
    val viewRtsButton: LiveData<Int> get() = _viewRtsButton

    private val _viewDirectionButton: MutableLiveData<Int> = MutableLiveData()
    val viewDirectionButton: LiveData<Int> get() = _viewDirectionButton

    private val _viewYButton: MutableLiveData<Int> = MutableLiveData()
    val viewYButton: LiveData<Int> get() = _viewYButton

    private val _viewBButton: MutableLiveData<Int> = MutableLiveData()
    val viewBButton: LiveData<Int> get() = _viewBButton

    private val _viewXButton: MutableLiveData<Int> = MutableLiveData()
    val viewXButton: LiveData<Int> get() = _viewXButton

    private val _viewAButton: MutableLiveData<Int> = MutableLiveData()
    val viewAButton: LiveData<Int> get() = _viewAButton

    private val _isChanged: MutableLiveData<Boolean> = MutableLiveData(false)
    val isChanged: LiveData<Boolean> get() = _isChanged


    init {
        appLayout.observeForever { value ->
            runBlocking {
                layoutEntity.value = HomeController.layoutRepository.value?.getSetting(value)
            }
            name.value = layoutEntity.value?.name
            _viewLtButton.value = layoutEntity.value?.ltButton
            _viewLbButton.value = layoutEntity.value?.lbButton
            _viewRtButton.value = layoutEntity.value?.rtButton
            _viewRbButton.value = layoutEntity.value?.rbButton
            _viewLtsButton.value = layoutEntity.value?.ltsButton
            _viewRtsButton.value = layoutEntity.value?.rtsButton
            _viewDirectionButton.value = layoutEntity.value?.directionButton
            _viewYButton.value = layoutEntity.value?.yButton
            _viewBButton.value = layoutEntity.value?.bButton
            _viewXButton.value = layoutEntity.value?.xButton
            _viewAButton.value = layoutEntity.value?.aButton
        }


        viewLtButton.observeForever { value ->
            _isChanged.value = value != layoutEntity.value?.ltButton
        }

        viewLbButton.observeForever { value ->
            _isChanged.value = value != layoutEntity.value?.lbButton
        }

        viewRtButton.observeForever { value ->
            _isChanged.value = value != layoutEntity.value?.rtButton
        }

        viewRbButton.observeForever { value ->
            _isChanged.value = value != layoutEntity.value?.rbButton
        }

        viewLtsButton.observeForever { value ->
            _isChanged.value = value != layoutEntity.value?.ltsButton
        }

        viewRtsButton.observeForever { value ->
            _isChanged.value = value != layoutEntity.value?.rtsButton
        }

        viewDirectionButton.observeForever { value ->
            _isChanged.value = value != layoutEntity.value?.directionButton
        }

        viewYButton.observeForever { value ->
            _isChanged.value = value != layoutEntity.value?.yButton
        }

        viewBButton.observeForever { value ->
            _isChanged.value = value != layoutEntity.value?.bButton
        }

        viewXButton.observeForever { value ->
            _isChanged.value = value != layoutEntity.value?.xButton
        }

        viewAButton.observeForever { value ->
            _isChanged.value = value != layoutEntity.value?.aButton
        }
    }

    suspend fun update() {
        // 현재 페이지에 적용
        HomeController.setLtButton(viewLtButton.value ?: 0)
        HomeController.setLbButton(viewLbButton.value ?: 0)
        HomeController.setRtButton(viewRtButton.value ?: 0)
        HomeController.setRbButton(viewRbButton.value ?: 0)
        HomeController.setLtsButton(viewLtsButton.value ?: 0)
        HomeController.setRtsButton(viewRtsButton.value ?: 0)
        HomeController.setDirectionButton(viewDirectionButton.value ?: 0)
        HomeController.setYButton(viewYButton.value ?: 0)
        HomeController.setBButton(viewBButton.value ?: 0)
        HomeController.setXButton(viewXButton.value ?: 0)
        HomeController.setAButton(viewAButton.value ?: 0)

        HomeController.saveLayoutSetting(
            LayoutSettingEntity(
                id = appLayout.value ?: 0,
                name = name.value ?: "Game Controller",
                ltButton = viewLtButton.value ?: 0,
                lbButton = viewLbButton.value ?: 0,
                rtButton = viewRtButton.value ?: 0,
                rbButton = viewRbButton.value ?: 0,
                ltsButton = viewLtsButton.value ?: 0,
                rtsButton = viewRtsButton.value ?: 0,
                directionButton = viewDirectionButton.value ?: 0,
                yButton = viewYButton.value ?: 0,
                bButton = viewBButton.value ?: 0,
                xButton = viewXButton.value ?: 0,
                aButton = viewAButton.value ?: 0,
            )
        )
    }

    fun setLayout(value: Int) {
        _appLayout.postValue(value)
    }

    fun setLtButton(value: Int) {
        _viewLtButton.postValue(value)
    }

    fun setLbButton(value: Int) {
        _viewLbButton.postValue(value)
    }

    fun setRtButton(value: Int) {
        _viewRtButton.postValue(value)
    }

    fun setRbButton(value: Int) {
        _viewRbButton.postValue(value)
    }

    fun setLtsButton(value: Int) {
        _viewLtsButton.postValue(value)
    }

    fun setRtsButton(value: Int) {
        _viewRtsButton.postValue(value)
    }

    fun setDirectionButton(value: Int) {
        _viewDirectionButton.postValue(value)
    }

    fun setYButton(value: Int) {
        _viewYButton.postValue(value)
    }

    fun setBButton(value: Int) {
        _viewBButton.postValue(value)
    }


    fun setXButton(value: Int) {
        _viewXButton.postValue(value)
    }

    fun setAButton(value: Int) {
        _viewAButton.postValue(value)
    }


}