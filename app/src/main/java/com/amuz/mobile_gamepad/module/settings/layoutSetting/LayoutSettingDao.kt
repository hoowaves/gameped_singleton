package com.amuz.mobile_gamepad.module.settings.layoutSetting

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LayoutSettingDao {
    @Query("SELECT * FROM layout_settings WHERE id = :id")
    suspend fun getLayoutById(id: Int): LayoutSettingEntity?

    @Query("SELECT * FROM layout_settings")
    suspend fun getLayoutList(): List<LayoutSettingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(layoutSetting: LayoutSettingEntity)
}