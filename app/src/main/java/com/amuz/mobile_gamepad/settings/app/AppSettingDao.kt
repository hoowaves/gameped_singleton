package com.amuz.mobile_gamepad.settings.app

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppSettingDao {
    @Query("SELECT * FROM app_settings WHERE id = 0")
    suspend fun getSetting(): AppSettingEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(setting: AppSettingEntity)
}