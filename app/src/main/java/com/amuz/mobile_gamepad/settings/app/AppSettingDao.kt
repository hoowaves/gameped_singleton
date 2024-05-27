package com.amuz.mobile_gamepad.settings.app

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AppSettingDao {
    @Query("SELECT * FROM app_settings WHERE id = 1")
    suspend fun getSettings(): AppSettingEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSettings(settings: AppSettingEntity)

    @Update
    suspend fun updateSettings(settings: AppSettingEntity)

    @Delete
    suspend fun deleteSettings(settings: AppSettingEntity)
}