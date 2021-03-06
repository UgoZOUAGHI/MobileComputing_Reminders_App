package com.example.mobilecomputing.Data.Entity

import androidx.room.*

@Entity(
    tableName = "Reminders"
)

data class Reminder(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var reminderId: Long = 0,

    @ColumnInfo(name = "message") val message: String,
    @ColumnInfo(name = "location_x") val location_x: Double,
    @ColumnInfo(name = "location_y") val location_y: Double,
    @ColumnInfo(name = "reminder_time") val reminder_time: String,
    @ColumnInfo(name = "creation_time") val creation_time: Long,
    @ColumnInfo(name = "creator_id") val creator_id: Long,
    @ColumnInfo(name = "reminder_seen") val reminder_seen: Long,
    @ColumnInfo(name = "reminder_hour") val reminder_hour: String,
    @ColumnInfo(name = "reminder_vibration") val reminder_vibration: Boolean,
    @ColumnInfo(name = "reminder_location") val reminder_location: Boolean

)

