package com.example.mobilecomputing

import android.content.Context
import androidx.room.Room
import com.example.mobilecomputing.Data.Repository.ReminderRepository
import com.example.mobilecomputing.Data.Room.MobileComputingDatabase

object Graph {

    lateinit var database: MobileComputingDatabase
        private set

    lateinit var appContext: Context

    val reminderRepository by lazy {
        ReminderRepository(
            reminderDao = database.reminderDao()
        )
    }

    fun provide(context: Context) {
        appContext = context
        database = Room.databaseBuilder(context, MobileComputingDatabase::class.java, "mcData.db")
            .fallbackToDestructiveMigration() // don't use this in production app
            .build()
    }
}