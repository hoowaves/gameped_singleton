package com.amuz.mobile_gamepad.modules.layoutSetting

import com.amuz.mobile_gamepad.modules.home.HomeController
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingEntity
import kotlinx.coroutines.runBlocking

class LayoutSettingModel {
    private val id: Int = LayoutSettingController.appLayout.value ?: 0
    private lateinit var layoutEntity: LayoutSettingEntity
    private lateinit var name: String
    private var ltButton: Int = 0
    private var lbButton: Int = 0
    private var rtButton: Int = 0
    private var rbButton: Int = 0
    private var ltsButton: Int = 0
    private var rtsButton: Int = 0
    private var directionButton: Int = 0
    private var yButton: Int = 0
    private var bButton: Int = 0
    private var xButton: Int = 0
    private var aButton: Int = 0

    suspend fun init(id: Int){
        layoutEntity = HomeController.layoutRepository.value?.getSetting(id)!!
        name = layoutEntity.name
        ltButton = layoutEntity.ltButton
        lbButton = layoutEntity.lbButton
        rtButton = layoutEntity.rtButton
        rbButton = layoutEntity.rbButton
        ltsButton = layoutEntity.ltsButton
        rtsButton = layoutEntity.rtsButton
        directionButton = layoutEntity.directionButton
        yButton = layoutEntity.yButton
        bButton = layoutEntity.bButton
        xButton = layoutEntity.xButton
        aButton = layoutEntity.aButton
    }

    fun getName(): String {
        return this.name
    }

    fun getLtButton(): Int {
        return this.ltButton
    }

    fun getLbButton(): Int {
        return this.lbButton
    }

    fun getRtButton(): Int {
        return this.rtButton
    }

    fun getRbButton(): Int {
        return this.rbButton
    }

    fun getLtsButton(): Int {
        return this.ltsButton
    }

    fun getRtsButton(): Int {
        return this.rtsButton
    }

    fun getDirectionButton(): Int {
        return this.directionButton
    }

    fun getYButton(): Int {
        return this.yButton
    }

    fun getBButton(): Int {
        return this.bButton
    }

    fun getXButton(): Int {
        return this.xButton
    }

    fun getAButton(): Int {
        return this.aButton
    }


}