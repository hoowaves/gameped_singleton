package com.amuz.mobile_gamepad.settings

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amuz.mobile_gamepad.settings.app.AppSettingDao
import com.amuz.mobile_gamepad.settings.app.AppSettingEntity
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingDao
import com.amuz.mobile_gamepad.settings.layout.LayoutSettingEntity

@Database(
    entities = [AppSettingEntity::class, LayoutSettingEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appSettingDao(): AppSettingDao
    abstract fun layoutSettingDao(): LayoutSettingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}