package com.amuz.mobile_gamepad.modules.layoutCustom

import androidx.lifecycle.MutableLiveData
import com.amuz.mobile_gamepad.settings.AppDatabase
import com.amuz.mobile_gamepad.settings.app.AppSettingEntity
import com.amuz.mobile_gamepad.settings.app.AppSettingModel
import com.amuz.mobile_gamepad.settings.app.AppSettingRepository
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingEntity
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingModel
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingRepository

class LayoutCustomController(layoutSettingModel: LayoutSettingModel, database: AppDatabase) {
    private val layoutSettingRepository: LayoutSettingRepository
    private val appSettingRepository: AppSettingRepository

    private val id: MutableLiveData<Int> = MutableLiveData()
    private val name: MutableLiveData<String> = MutableLiveData()
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
    var isChanged: MutableLiveData<Boolean> = MutableLiveData(false)

    private var isDefaultLayoutModel: MutableLiveData<Boolean> = MutableLiveData(false)
    var isDefaultLayout: MutableLiveData<Boolean> = MutableLiveData(false)
    private var saveLayout: MutableLiveData<Int> = MutableLiveData()

    var isReset: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        this.layoutSettingRepository = LayoutSettingRepository(database.layoutSettingDao())
        this.appSettingRepository = AppSettingRepository(database.appSettingDao())

        this.id.value = layoutSettingModel.id.value
        this.name.value = layoutSettingModel.name.value
        this.ltButton.value = layoutSettingModel.ltButton.value
        this.lbButton.value = layoutSettingModel.lbButton.value
        this.rtButton.value = layoutSettingModel.rtButton.value
        this.rbButton.value = layoutSettingModel.rbButton.value
        this.ltsButton.value = layoutSettingModel.ltsButton.value
        this.rtsButton.value = layoutSettingModel.rtsButton.value
        this.directionButton.value = layoutSettingModel.directionButton.value
        this.yButton.value = layoutSettingModel.yButton.value
        this.bButton.value = layoutSettingModel.bButton.value
        this.xButton.value = layoutSettingModel.xButton.value
        this.aButton.value = layoutSettingModel.aButton.value

        this.saveLayout.value = layoutSettingModel.id.value

        this.isReset.value = listOf(
            this.ltButton,
            this.lbButton,
            this.rtButton,
            this.rbButton,
            this.ltsButton,
            this.rtsButton,
            this.directionButton,
            this.yButton,
            this.bButton,
            this.xButton,
            this.aButton,
        ).any { it.value != 0 }

        if (AppSettingModel.layout.value == layoutSettingModel.id.value) {
            this.isDefaultLayoutModel.value = true
            this.isDefaultLayout.value = true
        }

        this.ltButton.observeForever { value ->
            isChanged.value = value != layoutSettingModel.ltButton.value
        }

        this.lbButton.observeForever { value ->
            isChanged.value = value != layoutSettingModel.lbButton.value
        }

        this.rtButton.observeForever { value ->
            isChanged.value = value != layoutSettingModel.rtButton.value
        }

        this.rbButton.observeForever { value ->
            isChanged.value = value != layoutSettingModel.rbButton.value
        }

        this.ltsButton.observeForever { value ->
            isChanged.value = value != layoutSettingModel.ltsButton.value
        }

        this.rtsButton.observeForever { value ->
            isChanged.value = value != layoutSettingModel.rtsButton.value
        }

        this.directionButton.observeForever { value ->
            isChanged.value = value != layoutSettingModel.directionButton.value
        }

        this.yButton.observeForever { value ->
            isChanged.value = value != layoutSettingModel.yButton.value
        }

        this.bButton.observeForever { value ->
            isChanged.value = value != layoutSettingModel.bButton.value
        }

        this.xButton.observeForever { value ->
            isChanged.value = value != layoutSettingModel.xButton.value
        }

        this.aButton.observeForever { value ->
            isChanged.value = value != layoutSettingModel.aButton.value
        }

        this.isDefaultLayout.observeForever { value ->
            isChanged.value = value != isDefaultLayoutModel.value
            saveLayout.value = if (this.isDefaultLayout.value == true) {
                this.id.value
            } else {
                0
            }
        }

    }

    suspend fun layoutUpdate() {
        layoutSettingRepository.saveSetting(
            LayoutSettingEntity(
                id = this.id.value ?: 0,
                name = this.name.value ?: "Game Controller",
                ltButton = this.ltButton.value ?: 0,
                lbButton = this.lbButton.value ?: 0,
                rtButton = this.rtButton.value ?: 0,
                rbButton = this.rbButton.value ?: 0,
                ltsButton = this.ltsButton.value ?: 0,
                rtsButton = this.rtsButton.value ?: 0,
                directionButton = this.directionButton.value ?: 0,
                yButton = this.yButton.value ?: 0,
                bButton = this.bButton.value ?: 0,
                xButton = this.xButton.value ?: 0,
                aButton = this.aButton.value ?: 0,
            )
        )
    }

    suspend fun appUpdate() {
        // 현재 실행 중인 앱에 저장
        AppSettingModel.layout.value = saveLayout.value
        // storage 저장
        appSettingRepository.saveSetting(
            AppSettingEntity(
                id = 0,
                layout = saveLayout.value ?: 0,
                isDark = AppSettingModel.isDark.value ?: true,
                touchEffect = AppSettingModel.touchEffect.value ?: false,
                powerSaving = AppSettingModel.powerSaving.value ?: 0,
                isVibration = AppSettingModel.isVibration.value ?: false
            )
        )
    }

    suspend fun layoutReset() {
        // 현재 실행 중인 앱에 저장
        this.ltButton.value = 0
        this.lbButton.value = 0
        this.rtButton.value = 0
        this.rbButton.value = 0
        this.ltsButton.value = 0
        this.rtsButton.value = 0
        this.directionButton.value = 0
        this.yButton.value = 0
        this.bButton.value = 0
        this.xButton.value = 0
        this.aButton.value = 0

        // storage 저장
        layoutSettingRepository.saveSetting(
            LayoutSettingEntity(
                id = this.id.value ?: 0,
                name = this.name.value ?: "Game Controller",
                ltButton = 0,
                lbButton = 0,
                rtButton = 0,
                rbButton = 0,
                ltsButton = 0,
                rtsButton = 0,
                directionButton = 0,
                yButton = 0,
                bButton = 0,
                xButton = 0,
                aButton = 0,
            )
        )
    }

}