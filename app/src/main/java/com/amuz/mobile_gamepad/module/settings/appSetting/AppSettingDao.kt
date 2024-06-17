package com.amuz.mobile_gamepad.module.settings.appSetting

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppSettingDao {
    @Query("SELECT * FROM app_settings WHERE id = 0")
    suspend fun getSetting(): AppSettingEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(setting: AppSettingEntity)
}