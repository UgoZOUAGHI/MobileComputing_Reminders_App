package com.example.mobilecomputing.Data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobilecomputing.Data.Dao.ReminderDao
import com.example.mobilecomputing.Data.Entity.Reminder


@Database(
    entities = [Reminder::class],
    version = 2,
    exportSchema = false
)

abstract class MobileComputingDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
}