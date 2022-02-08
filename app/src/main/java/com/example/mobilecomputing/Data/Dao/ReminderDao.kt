package com.example.mobilecomputing.Data.Dao

import androidx.room.*
import com.example.mobilecomputing.Data.Entity.Reminder
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ReminderDao{

    @Query("""SELECT * FROM Reminders WHERE id = :reminderId""")
    abstract suspend fun reminder(reminderId: Long): Reminder?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: Reminder): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(entity: Reminder)

    @Delete
    abstract suspend fun delete(entity: Reminder): Int

    @Query("SELECT * FROM reminders LIMIT 15")
    abstract fun reminders(): Flow<List<Reminder>>

    @Query("DELETE FROM reminders WHERE id = :reminderId")
    abstract suspend fun deleteById(reminderId: Long)

}