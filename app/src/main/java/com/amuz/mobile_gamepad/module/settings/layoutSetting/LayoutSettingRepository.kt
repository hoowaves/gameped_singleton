package com.amuz.mobile_gamepad.module.settings.layoutSetting

class LayoutSettingRepository(private val layoutSettingDao: LayoutSettingDao) {

    suspend fun getSetting(id: Int): LayoutSettingEntity? {
        return layoutSettingDao.getLayoutById(id)
    }

    suspend fun getSettingList(): List<LayoutSettingEntity>{
        return layoutSettingDao.getLayoutList()
    }

    suspend fun saveSetting(setting: LayoutSettingEntity) {
        layoutSettingDao.insert(setting)
    }

    suspend fun createDefaultGameController() {
        layoutSettingDao.insert(
            LayoutSettingEntity(
                id = 0,
                name = "Game Controller",
                ltButton = 0,
                lbButton = 0,
                rtButton = 0,
                rbButton = 0,
                ltsButton = 0,
                rtsButton = 0,
                yButton = 0,
                bButton = 0,
                xButton = 0,
                aButton = 0,
                upButton = 0,
                rightButton = 0,
                downButton = 0,
                leftButton = 0
            )
        )
    }

    suspend fun createDefaultDriving1() {
        layoutSettingDao.insert(
            LayoutSettingEntity(
                id = 1,
                name = "Driving 1",
                ltButton = 0,
                lbButton = 0,
                rtButton = 0,
                rbButton = 0,
                ltsButton = 0,
                rtsButton = 0,
                yButton = 0,
                bButton = 0,
                xButton = 0,
                aButton = 0,
                upButton = 0,
                rightButton = 0,
                downButton = 0,
                leftButton = 0
            )
        )
    }

    suspend fun createDefaultDriving2() {
        layoutSettingDao.insert(
            LayoutSettingEntity(
                id = 2,
                name = "Driving 2",
                ltButton = 0,
                lbButton = 0,
                rtButton = 0,
                rbButton = 0,
                ltsButton = 0,
                rtsButton = 0,
                yButton = 0,
                bButton = 0,
                xButton = 0,
                aButton = 0,
                upButton = 0,
                rightButton = 0,
                downButton = 0,
                leftButton = 0
            )
        )
    }

    suspend fun createDefaultCasual() {
        layoutSettingDao.insert(
            LayoutSettingEntity(
                id = 3,
                name = "Casual",
                ltButton = 0,
                lbButton = 0,
                rtButton = 0,
                rbButton = 0,
                ltsButton = 0,
                rtsButton = 0,
                yButton = 0,
                bButton = 0,
                xButton = 0,
                aButton = 0,
                upButton = 0,
                rightButton = 0,
                downButton = 0,
                leftButton = 0
            )
        )
    }
}